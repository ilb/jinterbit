/**
 * 
 */
package com.ipc.oce.junit;

import java.io.IOException;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;
import org.junit.Test;

import com.ipc.oce.ApplicationDriver;
import static com.ipc.oce.ConfigurationConstants.*;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;

/**
 * @author Konovalov 
 */
public class AltConnect {

	@Test
	public void connectWithoutPropertiesReader() throws JIException, IOException {
		Properties configuration = new Properties();
		
		configuration.setProperty(OCE_CFG_DRIVER, "V81Driver");
		configuration.setProperty(OCE_CFG_HOST, "192.168.10.142");
		configuration.setProperty(OCE_CFG_HOST_USER, "Konovalov");
		configuration.setProperty(OCE_CFG_HOST_PASSWORD, "dlheu0");
		configuration.setProperty(OCE_CFG_1CDB_PATH, "C:\\Developer\\Temp\\Empty");
		configuration.setProperty(OCE_CFG_1CDB_USER, "one");
		configuration.setProperty(OCE_CFG_1CDB_PASSWORD, "one");

		ApplicationDriver driver = ApplicationDriver
				.loadDriver((String) configuration
						.get(PropertiesReader.OCE_CFG_DRIVER));
		driver.setAutoRegistration(true);

		OCApp app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		try {
			app.connect(configuration);
			System.out.println("Connected to " + app.getComputerName());
		} finally {
			app.exit();
		}
	}
}
