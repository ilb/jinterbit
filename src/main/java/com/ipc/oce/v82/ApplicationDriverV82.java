/**
 * 
 */
package com.ipc.oce.v82;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.DynamicNamespaceContext;

/**
 * @author Konovalov
 *
 */
public class ApplicationDriverV82 extends ApplicationDriver {
	
	/**
	 * @throws  
	 * 
	 */
	public ApplicationDriverV82() {
		super();
		//{2B0C1632-A199-4350-AA2D-2AEE3D2D573A}
		driverVersion = "8.2";
		cApplicationId = "2B0C1632-A199-4350-AA2D-2AEE3D2D573A";
		cApplIdIsClsId = true;

		setTypeSynonymParser(new TypeSynonymParserV82());
		setVariantGUIDTable(new VariantFactoryV82());
		
		DynamicNamespaceContext dnc = new DynamicNamespaceContext();
		dnc.addNamespace("v8e", "http://v8.1c.ru/eventLog");
		setNamespaceContext(dnc);
		
		autoRegistration = false;
		
	}
	
}
