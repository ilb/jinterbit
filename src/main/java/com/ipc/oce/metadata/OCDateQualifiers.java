package com.ipc.oce.metadata;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.varset.OCDateFractions;


public class OCDateQualifiers extends OCObject {

	public OCDateQualifiers(OCObject object) {
		super(object);
	}

	public OCDateQualifiers(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDateQualifiers(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Определяет допустимые части даты. 
	 * @return
	 * @throws JIException
	 */
	public OCDateFractions getDateFractions() throws JIException {
		return new OCDateFractions(get("DateFractions"));
	}
}
