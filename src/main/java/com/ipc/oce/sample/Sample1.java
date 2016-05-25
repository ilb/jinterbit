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

/**
 * Connecting with core
 */
public class Sample1 {

	public static void main(String[] args) throws IOException, ConfigurationException {
		// step 1 load properties via configuration file
		PropertiesReader pr = new PropertiesReader(new File("C:\\Developer\\Projects\\hel_default\\OCExportWeb\\WebContent\\WEB-INF\\oce.properties"));
		
		
		Properties configuration = pr.getPropertiesForInstance("inst01");
		
		// [step 2] load application driver (or use you can use default)
		ApplicationDriver driver = ApplicationDriver
				.loadDriver((String) configuration
						.get(PropertiesReader.OCE_CFG_DRIVER));
		
		// step 3 create OCApp instance and set driver
		OCApp app = OCApp.getNewInstance();
		app.setApplicationDriver(driver);
		
		try{
			app.connect(configuration);
			// do some useful things
			//...
			System.out.println("Computer name: "+app.getComputerName());
		}catch(Exception e){
			
		}finally{
			try {
				app.exit();
			} catch (JIException e) {
				
			}
		}

	}

}
