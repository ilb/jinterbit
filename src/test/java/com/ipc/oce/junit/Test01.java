/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.metadata.objects.OCAccountingRegisterMetadataObject;
import com.ipc.oce.objects.OCAccountingRegisterManager;
import com.ipc.oce.objects.OCAccountingRegisterSelection;
import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCDocumentObject;
import com.ipc.oce.objects.OCDocumentRef;
import com.ipc.oce.objects.OCDocumentSelection;
import com.ipc.oce.varset.EAccountingRecordType;
import com.ipc.oce.varset.EComparisonType;
import com.ipc.oce.varset.EDocumentPostingMode;
import com.ipc.oce.varset.EEventLogLevel;

/**
 * @author Konovalov
 * 
 */
public class Test01 {

	private static Properties configuration = null;
	private static OCApp app = null;
	private int sessionNum = -1;

	
	@Test
	public void checkReconnect() throws JIException, IOException {
		int newSessNum = app.reconnect();
		assertTrue(sessionNum != newSessNum);
		sessionNum = newSessNum;
		assertTrue(app.ping());
	}

	@Test
	public void staticVSvarset() throws JIException {
		EDocumentPostingMode eDWM = app
				.findVarset(EDocumentPostingMode.REGULAR);
		OCObject o1 = app.getStaticFields("DocumentPostingMode.Regular");
		System.out.println("eDWM: " + eDWM.toString());
		System.out.println("Stat: " + o1.toString());
		assertTrue(eDWM.toString().equals(o1.toString()));
	}

	@Test
	public void typeComparision() throws JIException {
		OCVariant variant = null;
		Object o = null;
		/*
		 * OCArray vList = app.newArray(); variant = new OCVariant(vList); o =
		 * variant.value(); assertTrue("OCArray instance error", o instanceof
		 * OCArray);
		 * 
		 * OCStructure structure = app.newStructure(); variant = new
		 * OCVariant(structure); o = variant.value(); assertTrue(o instanceof
		 * OCStructure);
		 */

		OCDocumentManager manager1 = app
				.getDocumentManager("СчетНаОплатуПокупателю");
		OCDocumentSelection selection1 = manager1.select();
		selection1.next();
		OCDocumentRef ref1 = selection1.getRef();
		OCDocumentObject obj1 = ref1.getObject();
		variant = new OCVariant(obj1);
		o = variant.value();

		assertTrue("Not " + OCDocumentObject.class.getName() + " it is "
				+ o.getClass().getName(), o instanceof OCDocumentObject);
	}

	@Test
	public void checkEEventLogVarset() throws JIException {

		assertNotNull(app.findVarset(EEventLogLevel.INFORMATION));
		assertNotNull("ERROR " + app.findVarset(EEventLogLevel.ERROR));
		assertNotNull("WARNING " + app.findVarset(EEventLogLevel.WARNING));
		assertNotNull("NOTE " + app.findVarset(EEventLogLevel.NOTE));
	}

	@Test
	public void checkEAccountingRecordTypeVarset() throws JIException {
		assertNotNull(app.findVarset(EAccountingRecordType.CREDIT));
		assertNotNull(app.findVarset(EAccountingRecordType.DEBIT));
	}

	@Test
	public void checkEComparisonTypeVarset() throws JIException {
		assertNotNull(app.findVarset(EComparisonType.CONTAINS));
		assertNotNull(app.findVarset(EComparisonType.EQUALS));
		assertNotNull(app.findVarset(EComparisonType.GREATER));
		assertNotNull(app.findVarset(EComparisonType.GREATER_OR_EQUALS));
		assertNotNull(app.findVarset(EComparisonType.IN_HIERARCHY));
		assertNotNull(app.findVarset(EComparisonType.IN_LIST));
		assertNotNull(app.findVarset(EComparisonType.IN_LIST_BY_HIERARCHY));
		assertNotNull(app.findVarset(EComparisonType.INTERVAL));
		assertNotNull(app.findVarset(EComparisonType.INTERVAL_INCL_BOUNDS));
		assertNotNull(app.findVarset(EComparisonType.INTERVAL_INCL_LOWER_BOUND));
		assertNotNull(app.findVarset(EComparisonType.INTERVAL_INCL_UPPER_BOUND));
		assertNotNull(app.findVarset(EComparisonType.LESS));
		assertNotNull(app.findVarset(EComparisonType.LESS_OR_EQUALS));
		assertNotNull(app.findVarset(EComparisonType.NOT_CONTAINS));
		assertNotNull(app.findVarset(EComparisonType.NOT_EQUALS));
		assertNotNull(app.findVarset(EComparisonType.NOT_IN_HIERARCHY));
		assertNotNull(app.findVarset(EComparisonType.NOT_IN_LIST));
		assertNotNull(app.findVarset(EComparisonType.NOT_IN_LIST_BY_HIERARCHY));
	}

	@Test
	public void getAccountingRegister() throws JIException {
		OCAccountingRegisterManager manager = app
				.getAccountingRegisterManager("Налоговый");

		OCAccountingRegisterMetadataObject metadata = manager.getMetadata();

		assertNotNull(metadata.getFullName());

		OCAccountingRegisterSelection selection = manager.select(null, null);
		assertTrue(selection.next());
		System.out.println("isActive " + selection.isActive());
		System.out.println("getLineNumber: " + selection.getLineNumber());
		System.out.println("getPeriod: " + selection.getPeriod());
		System.out.println("getNumberAsString: "
				+ selection.getRecorder().getNumberAsString());

	}

	/**
	 * @throws java.lang.Exception
	 */
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		app.exit();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

}
