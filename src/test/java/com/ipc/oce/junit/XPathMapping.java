/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.*;
import java.io.File;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.DynamicNamespaceContext;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;

/**
 * @author Konovalov
 *
 */
public class XPathMapping {
	private static Properties configuration = null;
	private static OCApp app = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropertiesReader pr = new PropertiesReader(
				new File("C:\\Developer\\Projects\\hel_default\\OCExportWeb\\WebContent\\WEB-INF\\oce.properties"));

		configuration = pr.getPropertiesForInstance("buh");

		// [step 2] load application driver (or use you can use default)
		ApplicationDriver driver = ApplicationDriver
				.loadDriver((String) configuration
						.get(PropertiesReader.OCE_CFG_DRIVER));
		driver.setAutoRegistration(true);
		// step 3 create OCApp instance and set driver
		app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		app.connect(configuration);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		app.exit();
	}
	
	@Test
	public void testCopyNamespace() {
		DynamicNamespaceContext dnc1 = (DynamicNamespaceContext) app.getNamespaceContext();
		String s1 = dnc1.toString();
		String s2 = dnc1.copy().toString();
		System.out.println(s1);
		System.out.println(s2);
		assertNotSame(s1, s2);
		assertNotSame(dnc1, dnc1.copy());
	}
}
