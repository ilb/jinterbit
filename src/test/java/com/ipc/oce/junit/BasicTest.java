/**
 * 
 */
package com.ipc.oce.junit;

import java.io.File;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCDocumentObject;
import com.ipc.oce.objects.OCDocumentSelection;

/**
 * @author Konovalov
 *
 */
public abstract class BasicTest {
	protected static Properties configuration = null;
	protected static OCApp app = null;
	protected int sessionNum = -1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropertiesReader pr = new PropertiesReader(
				new File(
						"C:\\Developer\\Projects\\hel_default\\OCExportWeb\\WebContent\\WEB-INF\\oce.properties"));

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
		System.out.println("InfoBaseConnectionNumber: " + app.getInfoBaseConnectionNumber());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		app.exit();
	}
	
	public OCDocumentObject getRandomDocument(String docName) throws JIException {
		OCDocumentManager manager = app.getDocumentManager(docName);
		OCDocumentSelection selection = manager.select();
		selection.next();
		return selection.getObject();
	}
}
