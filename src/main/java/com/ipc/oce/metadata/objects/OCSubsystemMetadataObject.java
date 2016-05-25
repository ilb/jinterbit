package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection._OCMetadataObjectPropertyValueCollection;

public class OCSubsystemMetadataObject extends _OCCommonMetadataObject {

	public OCSubsystemMetadataObject(OCObject object) {
		super(object);
	}

	public OCSubsystemMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCSubsystemMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public _OCMetadataObjectPropertyValueCollection getSubsystems()
			throws JIException {
		return new _OCMetadataObjectPropertyValueCollection(get("Subsystems"));
	}
}
