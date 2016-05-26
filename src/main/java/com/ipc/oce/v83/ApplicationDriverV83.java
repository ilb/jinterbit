/**
 * 
 */
package com.ipc.oce.v83;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.DynamicNamespaceContext;

/**
 * @author Konovalov
 *
 */
public class ApplicationDriverV83 extends ApplicationDriver {
	
	/**
	 * @throws  
	 * 
	 */
	public ApplicationDriverV83() {
		super();
		//{2B0C1632-A199-4350-AA2D-2AEE3D2D573A}
		driverVersion = "8.3";
		cApplicationId = "181E893D-73A4-4722-B61D-D604B3D67D47";
                //cApplicationId = "E92B75E3-2EA1-4FEC-B493-CEF3EC59FCA6";
		cApplIdIsClsId = true;

		setTypeSynonymParser(new TypeSynonymParserV83());
		setVariantGUIDTable(new VariantFactoryV83());
		
		DynamicNamespaceContext dnc = new DynamicNamespaceContext();
		dnc.addNamespace("v8e", "http://v8.1c.ru/eventLog");
		setNamespaceContext(dnc);
		
		autoRegistration = false;
		
	}
	
}
