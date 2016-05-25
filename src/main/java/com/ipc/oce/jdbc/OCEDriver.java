/**
 * 
 */
package com.ipc.oce.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.DefaultApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;

/**
 * JDBC драйвер для обращения к 1С через DCOM. Type 4.
 * 
 * @author Konovalov
 * 
 */
public class OCEDriver implements Driver {

	/**
	 * Логгер.
	 */
	private static final transient Log LOG = LogFactory.getLog(OCEDriver.class);

	/**
	 * Наименование драйвера.
	 */
	public static final String DRIVER_NAME = "OCEDriver";

	/**
	 * Номер основной версии.
	 */
	private static final int MAJOR_VERSION = 0;

	/**
	 * Номер подвыпуска.
	 */
	private static final int MINOR_VERSION = 3;

	/**
	 * Префикс для URL драйвера.
	 */
	protected static final transient String URL_PREFIX = "jdbc:oce:dcom";

	static {
		try {
			java.sql.DriverManager.registerDriver(new OCEDriver());
			LOG.info("Driver registred: " + OCEDriver.class.getName());

		} catch (SQLException e) {
			LOG.error(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	/**
	 * Строка-шаблон URL драйвера.
	 */
	private static final String URL_EXAMPLE = "jdbc:oce:dcom://<host:user@password>;oce.1c.user=userName;oce.1c.password=password;oce.1c.dbpath=C:\\Temp";

	/**
	 * Преобразование параметров url в Properties. Значения в исходный
	 * Properties имеют высший приоритет чем в url
	 * 
	 * @param url
	 *            - jdbc:oce:dcom://<host:user@password>;oce.1c.user=userName;
	 *            oce.1c.password=password;oce.1c.dbpath=C:\Temp
	 * @param prp
	 *            - оригинальные Properties
	 * @return Properties дополненные из url
	 * @throws SQLException
	 */
	public static Properties parseURLandUpdateProperties(String url,
			Properties prp) throws SQLException {

		if (prp == null) {
			prp = new Properties();
		}
		// url pattern:
		// jdbc:oce:dcom://<host:user@password>;oce.1c.user=userName;oce.1c.password=password;oce.1c.dbpath=C:\Temp
		String original = (url.startsWith(URL_PREFIX + ":") ? url
				.substring(URL_PREFIX.length() + 1) : url.substring(URL_PREFIX
				.length()));
		StringTokenizer tokenizer = new StringTokenizer(original, ";", false);

		// DCOM parameters ----------------------------------
		int dSlashIndex = original.indexOf("//");
		if (dSlashIndex == -1) {
			// just nop now
		} else {
			String dcomParams = tokenizer.nextToken();
			dcomParams = dcomParams.substring(2); // +3 = '://'

			int del1 = dcomParams.indexOf(":"); // host:login
			int del2 = dcomParams.indexOf("@"); // user:password

			if (del1 == -1 || del2 == -1) {
				throw new SQLException("Malformed connection URL. Use pattern "
						+ URL_EXAMPLE);
			}

			String host = dcomParams.substring(0, del1);
			if (host == null || host.trim().length() == 0) {
				host = "localhost";
			}

			String hostUser = dcomParams.substring(del1 + 1, del2);
			if (hostUser == null || hostUser.trim().length() == 0) {
				throw new SQLException(
						"Host (dcom) user's name was not supplied. "
								+ "Set user via url or properties (oce.host.user)");
			}

			String hostPassword = dcomParams.substring(del2 + 1);
			if (hostPassword == null || hostPassword.trim().length() == 0) {
				throw new SQLException(
						"Host password was not supplied. Set host password "
								+ "via url or properties (oce.host.password");
			}

			// update original properties
			if (!(prp.containsKey(PropertiesReader.OCE_CFG_HOST))) {
				prp.put(PropertiesReader.OCE_CFG_HOST, host);
			}
			if (!(prp.containsKey(PropertiesReader.OCE_CFG_HOST_USER))) {
				prp.put(PropertiesReader.OCE_CFG_HOST_USER, hostUser);
			}
			if (!(prp.containsKey(PropertiesReader.OCE_CFG_HOST_PASSWORD))) {
				prp.put(PropertiesReader.OCE_CFG_HOST_PASSWORD, hostPassword);
			}
		}

		// parse other
		while (tokenizer.hasMoreElements()) {
			String token = tokenizer.nextToken();
			int equalsIndex = token.indexOf("=");
			String key = token.substring(0, equalsIndex);
			String value = token.substring(equalsIndex + 1);
			if (!(prp.containsKey(key))) {
				prp.put(key, value);
			}
		}
		return prp;
	}

	public boolean acceptsURL(String url) throws SQLException {
		return (url != null && url.startsWith(URL_PREFIX));
	}

	public Connection connect(String url, Properties params)
			throws SQLException {
		if (!acceptsURL(url)) {
			return null;
		}

		LOG.info("OCE connect");

		// parsing jdbc url
		parseURLandUpdateProperties(url, params);

		// handle url, user, password construction ============================
		// most preferable
		if (params.get("user") != null) {
			params.put(PropertiesReader.OCE_CFG_1CDB_USER, params.get("user"));
		}
		
		if (params.get("password") != null) {
			params.put(PropertiesReader.OCE_CFG_1CDB_PASSWORD,
					params.get("password"));
		}

		ApplicationDriver driver = null;

		if (params != null
				&& params.get(PropertiesReader.OCE_CFG_DRIVER) != null) {
			driver = ApplicationDriver.loadDriver((String) params
					.get(PropertiesReader.OCE_CFG_DRIVER));
			if (driver == null) {
				driver = new DefaultApplicationDriver();
			}
		}

		OCApp app = OCApp.getNewInstance();

		if (params.get("autoRegistration") != null) {
			Boolean autoReg = Boolean.valueOf(params
					.getProperty("autoRegistration"));
			driver.setAutoRegistration(autoReg);
		}

		app.setApplicationDriver(driver);

		try {
			int sessionNum = app.connect(params);
			LOG.info("Session number: " + sessionNum);
		} catch (SecurityException e) {
			throw new SQLException("Connection failed. Parameters " + params
					+ ". " + e);
		} catch (JIException e) {
			throw new SQLException("Connection failed. Parameters " + params
					+ ". " + e);
		} catch (IOException e) {
			throw new SQLException("Connection failed. Parameters " + params
					+ ". " + e);
		}
		OCEConnection connection = new OCEConnection(app, url, params);
		connection.setDriver(this);
		return connection;
	}

	public int getMajorVersion() {
		return MAJOR_VERSION;
	}

	public int getMinorVersion() {
		return MINOR_VERSION;
	}

	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1)
			throws SQLException {
		if (!acceptsURL(arg0)) {
			throw new SQLException("jdbc url is incorrect. use " + URL_PREFIX);
		}
		DriverPropertyInfo[] info = new DriverPropertyInfo[7];

		// 1C driver
		info[0] = new DriverPropertyInfo("oce.driver", "V81Driver");
		info[0].description = "OCE driver for concret version of 1C";
		info[0].required = false;
		info[0].choices = new String[] { "V81Driver" };

		info[1] = new DriverPropertyInfo(PropertiesReader.OCE_CFG_HOST,
				"localhost");
		info[1].required = true;

		info[2] = new DriverPropertyInfo(PropertiesReader.OCE_CFG_HOST_USER,
				"username");
		info[2].required = true;

		info[3] = new DriverPropertyInfo(
				PropertiesReader.OCE_CFG_HOST_PASSWORD, "password");
		info[3].required = true;

		info[4] = new DriverPropertyInfo(PropertiesReader.OCE_CFG_1CDB_PATH,
				"C:\\");
		info[4].required = true;

		info[5] = new DriverPropertyInfo(PropertiesReader.OCE_CFG_1CDB_USER,
				"user_1C");
		info[5].required = true;

		info[6] = new DriverPropertyInfo(
				PropertiesReader.OCE_CFG_1CDB_PASSWORD, "userpassword_1C");
		info[6].required = true;

		return info;
	}

	public boolean jdbcCompliant() {
		return false;
	}

}
