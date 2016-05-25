/**
 * 
 */
package com.ipc.oce.sample;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.OCApp;
import com.ipc.oce.PropertiesReader;
import com.ipc.oce.exceptions.ConfigurationException;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCCatalogRef;
import com.ipc.oce.objects.OCCatalogSelection;

/**
 * Connecting with core
 */
public class Sample2 {
	
	private Sample2() {
		super();
	}
	
	private static OCApp getConnection() throws JIException, IOException, ConfigurationException{
		PropertiesReader pr = new PropertiesReader(new File("C:\\Developer\\Projects\\hel_default\\OCExportWeb\\WebContent\\WEB-INF\\oce.properties"));
		Properties configuration = pr.getPropertiesForInstance("inst01");
		ApplicationDriver driver = ApplicationDriver
				.loadDriver((String) configuration
						.get(PropertiesReader.OCE_CFG_DRIVER));
		OCApp app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		app.connect(configuration);
		return app;
	}

	public static void main(String[] args) throws IOException, ConfigurationException, JIException {
		OCApp app = getConnection();
		OCCatalogManager manager = app.getCatalogManager("Банки");
		OCCatalogRef ref = manager.findByDescription("Г МОСКВА");
		OCCatalogSelection selection = manager.selectHierarchically(ref);
		while (selection.next()) {
			int level = selection.getLevelInSelection();
			if (level==0) {
				System.out.println(selection.getCode()+" "+selection.getDescription());
			}
		}
	}
		
}
