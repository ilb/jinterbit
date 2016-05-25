/**
 * 
 */
package com.ipc.oce.v82;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;

import com.ipc.oce.VariantFactory;
import com.ipc.oce.varset.EAccountingRecordType;
import com.ipc.oce.varset.EComparisonType;
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
class VariantFactoryV82 extends VariantFactory{
	private static final transient Log LOG = LogFactory.getLog(VariantFactoryV82.class);
	
	public VariantFactoryV82(){

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
		
		//741ae838-6e42-4ac0-b6a4-17e5604b0669
		storeObject(EAccountingRecordType.CREDIT, new EAccountingRecordType("{\"#\",741ae838-6e42-4ac0-b6a4-17e5604b0669,1}"));
		storeObject(EAccountingRecordType.DEBIT, new EAccountingRecordType("{\"#\",741ae838-6e42-4ac0-b6a4-17e5604b0669,0}"));
		
		//b1b064f3-ae38-49bf-8c6d-390c65fd94af
		storeObject(EComparisonType.CONTAINS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,10}")); // OK
		storeObject(EComparisonType.EQUALS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,0}")); // OK
		storeObject(EComparisonType.GREATER, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,4}")); // OK
		storeObject(EComparisonType.GREATER_OR_EQUALS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,5}")); // OK
		storeObject(EComparisonType.IN_HIERARCHY, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,15}")); // OK
		storeObject(EComparisonType.IN_LIST, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,11}")); // OK
		storeObject(EComparisonType.IN_LIST_BY_HIERARCHY, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,12}")); // OK
		storeObject(EComparisonType.INTERVAL, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,6}")); // OK
		storeObject(EComparisonType.INTERVAL_INCL_BOUNDS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,7}")); //OK
		storeObject(EComparisonType.INTERVAL_INCL_LOWER_BOUND, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,8}"));
		storeObject(EComparisonType.INTERVAL_INCL_UPPER_BOUND, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,9}"));
		storeObject(EComparisonType.LESS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,2}")); // OK
		storeObject(EComparisonType.LESS_OR_EQUALS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,3}")); //OK
		storeObject(EComparisonType.NOT_CONTAINS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,17}")); //OK
		storeObject(EComparisonType.NOT_EQUALS, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,1}")); // OK
		storeObject(EComparisonType.NOT_IN_HIERARCHY, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,16}")); //OK
		storeObject(EComparisonType.NOT_IN_LIST, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,13}")); // OK
		storeObject(EComparisonType.NOT_IN_LIST_BY_HIERARCHY, new EAccountingRecordType("{\"#\",b1b064f3-ae38-49bf-8c6d-390c65fd94af,14}"));
		
		LOG.info("Varset table preloaded in "
				+ (System.currentTimeMillis() - s) + "ms");
	}
	
	
	
}
