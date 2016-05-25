package com.ipc.oce.metadata;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.varset.OCAllowedLength;


public class OCStringQualifier extends OCObject {

	public OCStringQualifier(OCObject object) {
		super(object);
	}

	public OCStringQualifier(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCStringQualifier(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public int getLength() throws JIException {
		return get("Length").getObjectAsInt();
	}
	/**
	 * Содержит вариант ограничения длины строки. 
	 * @return
	 * @throws JIException
	 */
	public OCAllowedLength getAllowedLength() throws JIException {
		return new OCAllowedLength(get("AllowedLength"));
	}

}
