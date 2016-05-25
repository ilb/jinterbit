package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCResourceMetadataObject extends _OCCommonMetadataObject {

	public OCResourceMetadataObject(OCObject object) {
		super(object);
	}

	public OCResourceMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCResourceMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public Boolean getBalance() throws JIException {
		return get("Balance").getObjectAsBoolean();
	}

	public OCAccountingFlagMetadataObject getAccountingFlag()
			throws JIException {
		return new OCAccountingFlagMetadataObject(get("AccountingFlag"));
	}

	public OCExtDimensionAccountingFlagMetadataObject getExtDimensionAccountingFlag()
			throws JIException {
		return new OCExtDimensionAccountingFlagMetadataObject(
				get("ExtDimensionAccountingFlag"));
	}
}
