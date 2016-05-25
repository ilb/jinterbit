/**
 * 
 */
package com.ipc.oce.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * @author Konovalov
 * 
 */
public class OCEBasicDataSource implements DataSource {

	private static final transient Log LOG = LogFactory.getLog(OCEBasicDataSource.class);
	
	private PrintWriter writer = null;
	
	private int timeout = 0;
	
	private String url = null;
	
	// a description of this data source
	private String description = null;

	private String host = null;

	private String applicationDriver = null;

	private String hostUser = null;

	private String hostPassword = null;

	private String dbPath = null;

	private String dbUser = null;

	private String dbPassword = null;

	/**
	 * 
	 */
	public OCEBasicDataSource() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Datasource instance created");
		}
	}
	
	

	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApplicationDriver() {
		return applicationDriver;
	}

	public void setApplicationDriver(String applicationDriver) {
		this.applicationDriver = applicationDriver;
	}

	public String getHostUser() {
		return hostUser;
	}

	public void setHostUser(String hostUser) {
		this.hostUser = hostUser;
	}

	public String getHostPassword() {
		return hostPassword;
	}

	public void setHostPassword(String hostPassword) {
		this.hostPassword = hostPassword;
	}

	public String getDbPath() {
		return dbPath;
	}

	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	
	public PrintWriter getLogWriter() throws SQLException {
		return writer;
	}

	
	public void setLogWriter(PrintWriter paramPrintWriter) throws SQLException {
		writer = paramPrintWriter;
	}

	
	public void setLoginTimeout(int paramInt) throws SQLException {
		timeout = paramInt;
	}

	
	public int getLoginTimeout() throws SQLException {
		return timeout;
	}

	
	public <T> T unwrap(Class<T> paramClass) throws SQLException {
		
		return null;
	}

	
	public boolean isWrapperFor(Class<?> paramClass) throws SQLException {
		return false;
	}

	
	public Connection getConnection() throws SQLException {
		return getConnection(getDbUser(), getDbPassword());
	}

	
	public Connection getConnection(String paramString1, String paramString2) throws SQLException {
		
		Properties conProperties = new Properties();
		
		conProperties.put("oce.host", getHost());
		conProperties.put("oce.driver", getApplicationDriver());
		conProperties.put("oce.host.user", getHostUser());
		conProperties.put("oce.host.password", getHostPassword());
		conProperties.put("oce.1c.dbpath", getDbPath());
		conProperties.put("oce.1c.user", paramString1);
		conProperties.put("oce.1c.password", paramString2);
		
		ApplicationDriver driver = null;
		if (conProperties != null
				&& conProperties.get(PropertiesReader.OCE_CFG_DRIVER) != null) {
			driver = ApplicationDriver.loadDriver((String) conProperties
					.get(PropertiesReader.OCE_CFG_DRIVER));
		}
		
		// magic is here
		OCApp app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		
		try {
			app.connect(conProperties);
		} catch (SecurityException e) {
			throw new SQLException(e);
		} catch (JIException e) {
			throw new SQLException(e);
		} catch (IOException e) {
			throw new SQLException(e);
		}
		
		OCEConnection connection = new OCEConnection(app, getUrl(), conProperties);
		connection.setDriver(new OCEDriver());
		
		return connection;
	}

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
