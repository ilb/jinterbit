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
import com.ipc.oce.metadata.OCConfigurationMetadataObject;
import com.ipc.oce.metadata.collection.OCMetadataDocumentCollection;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCCatalogRef;
import com.ipc.oce.objects.OCCatalogSelection;

/**
 * Connecting with core
 */
public class Sample3 {
	
	private Sample3() {
		super();
	}
	
	private static OCApp getConnection() throws JIException, IOException, ConfigurationException{
		PropertiesReader pr = new PropertiesReader(new File("C:\\Developer\\Projects\\hel_default\\OCExportWeb\\WebContent\\WEB-INF\\oce.properties"));
		Properties configuration = pr.getPropertiesForInstance("buh");
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
		OCConfigurationMetadataObject configurationMetadata = app.getMetadata();
		OCMetadataDocumentCollection documentCollection = configurationMetadata.getDocuments();
		System.out.println(
				configurationMetadata.getName() 
				+ " " 
				+ configurationMetadata.getVersion());
		int sz = documentCollection.size();
		for (int z = 0; z < sz; z++) {
			OCDocumentMetadataObject metadataObject = documentCollection.get(z);
			System.out.println(metadataObject.getFullName());
		}
	}
		
}
