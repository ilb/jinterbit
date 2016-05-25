/**
 * 
 */
package com.ipc.oce.v80;

import com.ipc.oce.ApplicationDriver;
import com.ipc.oce.events.v81.OCEventNamespaceContext;

/**
 * @author Konovalov
 *
 */
public class ApplicationDriverV80 extends ApplicationDriver {

    /**
     * 
     */
    public ApplicationDriverV80() {
	//{E84B3F7A-7A29-44A2-AC61-F56F1752452C}
	super();
	driverVersion = "8.0";
	cApplicationId = "E84B3F7A-7A29-44A2-AC61-F56F1752452C"; // V8.COMConnector CLSID
	cApplIdIsClsId = true;
	setTypeSynonymParser(new TypeSynonymParserV80());
	setVariantGUIDTable(new VariantFactoryV80());
	setNamespaceContext(new OCEventNamespaceContext());
	autoRegistration = false;
    }

}
