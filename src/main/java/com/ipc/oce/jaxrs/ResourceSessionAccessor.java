/**
 * 
 */
package com.ipc.oce.jaxrs;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.exceptions.ConfigurationException;

/**
 * Класс реализующий подключение к OCApp. Основная задача: создание подключения, получение активного подключения, ping и переподключение
 * @author Konovalov
 *
 */
public abstract class ResourceSessionAccessor {
	
	public static final String SYS_NS = "http://www.interprocom.ru/octitbit";
	
	public static final String CONFIG_FILE_PATH = "oce.cfg.file.path";
	
	public static final String CONFIG_INST_NAME = "oce.cfg.instance";
	
	@Context ServletConfig servletConfig;
	
	@Context SecurityContext securityContext;
	
	private int sessionID = -1;
	
	/**
	 * 
	 */
	public ResourceSessionAccessor() {
		super();
	}
	
	protected synchronized OCApp getApplication() throws IOException, ConfigurationException, JIException {
		OCApp app1 = null;
		
		if (sessionID != -1) { // получение инстанса из хранилища
			app1 = OCApp.getInstance(sessionID);
		}
		
		if (app1 == null) { // если sessionID == -1 или сессия удалена
			sessionID = createConnection();
			app1 = OCApp.getInstance(sessionID);
		} else if (!(app1.ping())) {
			try {
				app1.exit();
			} catch (Exception e) { // да и хрен с нимим с битыми ссылками
			}
			sessionID = createConnection();
			app1 = OCApp.getInstance(sessionID);
		}
		return app1;
	}
	
	private int createConnection() throws IOException, JIException, ConfigurationException {
		
		// check init parameters
		Properties configuration = null;
		String cfgPath = servletConfig.getInitParameter(CONFIG_FILE_PATH);
		String instanceName = servletConfig.getInitParameter(CONFIG_INST_NAME);
		if (cfgPath != null) {
			PropertiesReader pr = new PropertiesReader(new File(cfgPath));
			configuration = pr.getPropertiesForInstance(
					instanceName != null ? instanceName : PropertiesReader.OCE_DEFAULT_INSTANCE
					);
			
		} else {
			String driver = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_DRIVER);
			String host = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_HOST);
			String hostUser = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_HOST_USER);
			String hostPassword = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_HOST_PASSWORD);
			String ocPath = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_1CDB_PATH);
			String ocUser = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_1CDB_USER);
			String ocPassword = servletConfig.getInitParameter(PropertiesReader.OCE_CFG_1CDB_PASSWORD);
			
			if (configuration == null) {
				configuration = new Properties();
			}
			configuration.put(PropertiesReader.OCE_CFG_DRIVER, driver);
			configuration.put(PropertiesReader.OCE_CFG_HOST, host);
			configuration.put(PropertiesReader.OCE_CFG_HOST_USER, hostUser);
			configuration.put(PropertiesReader.OCE_CFG_HOST_PASSWORD, hostPassword);
			configuration.put(PropertiesReader.OCE_CFG_1CDB_PATH, ocPath);
			configuration.put(PropertiesReader.OCE_CFG_1CDB_USER, ocUser);
			configuration.put(PropertiesReader.OCE_CFG_1CDB_PASSWORD, ocPassword);
		}
		
		// else create new connection
		
		
		ApplicationDriver driver = ApplicationDriver.loadDriver(configuration
				.getProperty(PropertiesReader.OCE_CFG_DRIVER));

		OCApp app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		int sessionCode = app.connect(configuration);
		
		
		return sessionCode;
	}

	@Override
	protected void finalize() throws Throwable {
		if (sessionID != -1) {
			try {
				OCApp.getInstance(sessionID).exit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.finalize();
	}
	
	
}
