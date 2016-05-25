/**
 * 
 */
package com.ipc.oce.junit;

import static org.junit.Assert.*;
import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.OCValueTableColumnCollection;
import com.ipc.oce.OCValueTableRow;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.query.OCQuery;
import com.ipc.oce.query.OCQueryResult;
import com.ipc.oce.query.OCQueryResultSelection;
import com.ipc.oce.query.QueryTemplate;

/**
 * @author Konovalov
 *
 */
public class UnloadResultSetToLocal {
	private static Properties configuration = null;
	private static OCApp app = null;
	private int sessionNum = -1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PropertiesReader pr = new PropertiesReader(
				new File(
						"C:\\Developer\\Projects\\hel_default\\OCExportWeb\\WebContent\\WEB-INF\\oce.properties"));

		configuration = pr.getPropertiesForInstance("inst03");

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
	public void getResultAndUnload() throws JIException {
		QueryTemplate qt = new QueryTemplate(app);
		OCQueryResultSelection selection = qt.queryForSelection("SELECT * FROM Catalog.Валюты");
		OCQueryResult result = selection.getOwner();
		OCValueTable vt = result.unload();
		assertTrue(vt.size() > 0);
		OCValueTableColumnCollection vtCC = vt.getColumns();
		int vtCCSZ = vtCC.size();
		assertTrue(vtCCSZ > 0);
		System.out.println(vt.size());
		for (int i = 1; i < vtCCSZ; i++) {
			System.out.print(vtCC.getTableColumn(i).getName() + "\t");
		}
		System.out.println();
		for (OCValueTableRow element : vt) {
			System.out.println(element.getValue(1));
		}
	}

}
