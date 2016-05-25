/**
 * 
 */
package com.ipc.oce.v81;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.DynamicNamespaceContext;

/**
 * @author Konovalov
 *
 */
public class ApplicationDriverV81 extends ApplicationDriver {
	
	/**
	 * 
	 */
	public ApplicationDriverV81() {
		super();
		driverVersion = "8.1";
		cApplicationId = "48EE4DBA-DE11-4af2-83B9-1F7FD6B6B3E3";
		cApplIdIsClsId = true;
		// V81.Application=b3a7d9db-3cba-47f4-b80a-5dda79d8925a
		// V81.Connector=48EE4DBA-DE11-4af2-83B9-1F7FD6B6B3E3
		setTypeSynonymParser(new TypeSynonymParserV81());
		setVariantGUIDTable(new VariantFactoryV81());
		
		DynamicNamespaceContext dnc = new DynamicNamespaceContext();
		dnc.addNamespace("v8e", "http://v8.1c.ru/eventLog");
		setNamespaceContext(dnc);
		
		autoRegistration = false;
		
	}
	
}
