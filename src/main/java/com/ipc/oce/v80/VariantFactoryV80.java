/**
 * 
 */
package com.ipc.oce.v80;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.VariantFactory;
import com.ipc.oce.varset.EDocumentPostingMode;
import com.ipc.oce.varset.EDocumentWriteMode;
import com.ipc.oce.varset.EEventLogLevel;
import com.ipc.oce.varset.EQueryResultIteration;
import com.ipc.oce.varset.EXMLForm;
import com.ipc.oce.varset.EXMLTypeAssignment;

/**
 * @author Konovalov
 *
 */
public class VariantFactoryV80 extends VariantFactory{
	private static final transient Log LOG = LogFactory.getLog(VariantFactoryV80.class);
	
	public VariantFactoryV80(){

	}

	@Override
	protected void activate() throws JIException {
		long s = System.currentTimeMillis();
		
		//76665f04-21b6-4b9c-abce-ed319bde7c6e
		storeObject(EDocumentPostingMode.REALTIME, new EDocumentPostingMode(("{\"#\",76665f04-21b6-4b9c-abce-ed319bde7c6e,1}")) );
		storeObject(EDocumentPostingMode.REGULAR, new EDocumentPostingMode(("{\"#\",76665f04-21b6-4b9c-abce-ed319bde7c6e,0}")) );
	
		
		//27c7756c-e1cc-435e-962f-22df9eeee925
		storeObject(EDocumentWriteMode.WRITE, new EDocumentWriteMode(("{\"#\",27c7756c-e1cc-435e-962f-22df9eeee925,0}") ));
		storeObject(EDocumentWriteMode.UNDO_POSTING, new EDocumentWriteMode(("{\"#\",27c7756c-e1cc-435e-962f-22df9eeee925,2}") ) );
		storeObject(EDocumentWriteMode.POSTING, new EDocumentWriteMode(("{\"#\",27c7756c-e1cc-435e-962f-22df9eeee925,1}") ));
		
		//fe5dad88-7ace-4c95-a422-5bc557f0280f
		storeObject(EQueryResultIteration.BYGROUPS, new EQueryResultIteration(("{\"#\",fe5dad88-7ace-4c95-a422-5bc557f0280f,1}")) );
		storeObject(EQueryResultIteration.BYGROUPS_WITH_HIERARCHY, new EQueryResultIteration(("{\"#\",fe5dad88-7ace-4c95-a422-5bc557f0280f,2}")) );
		storeObject(EQueryResultIteration.LINEAR, new EQueryResultIteration(("{\"#\",fe5dad88-7ace-4c95-a422-5bc557f0280f,0}")) );
		
		//32acb50a-6fad-4e27-8ea4-3477d9864119
		storeObject(EXMLTypeAssignment.IMPLICIT, new EXMLTypeAssignment("{\"#\",32acb50a-6fad-4e27-8ea4-3477d9864119,1}") );
		storeObject(EXMLTypeAssignment.EXPLICIT, new EXMLTypeAssignment("{\"#\",32acb50a-6fad-4e27-8ea4-3477d9864119,0}") );
		
		//a0f695f7-9c59-4192-8b3a-ae8c8fac1530
		storeObject(EXMLForm.ATTRIBUTE, new EXMLForm("{\"#\",a0f695f7-9c59-4192-8b3a-ae8c8fac1530,0}") );
		storeObject(EXMLForm.TEXT, new EXMLForm("{\"#\",a0f695f7-9c59-4192-8b3a-ae8c8fac1530,2}") );
		storeObject(EXMLForm.ELEMENT, new EXMLForm("{\"#\",a0f695f7-9c59-4192-8b3a-ae8c8fac1530,1}") );
		
		//87345ce9-f140-4d75-92e1-4edcc38237cb
		storeObject(EEventLogLevel.ERROR, new EEventLogLevel("{\"#\",87345ce9-f140-4d75-92e1-4edcc38237cb,3}"));
		storeObject(EEventLogLevel.INFORMATION, new EEventLogLevel("{\"#\",87345ce9-f140-4d75-92e1-4edcc38237cb,1}"));
		storeObject(EEventLogLevel.WARNING, new EEventLogLevel("{\"#\",87345ce9-f140-4d75-92e1-4edcc38237cb,2}"));
		storeObject(EEventLogLevel.NOTE, new EEventLogLevel("{\"#\",87345ce9-f140-4d75-92e1-4edcc38237cb,0}"));
		
		LOG.info("Varset table preloaded in "
				+ (System.currentTimeMillis() - s) + "ms");
	}
	
	
	
}
