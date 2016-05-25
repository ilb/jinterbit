package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCAllowedSign extends OCObject implements IOCVariantSet{
	public static final String ANY = "Любой";
	public static final String NON_NEGATIVE = "Неотрицательный";
	public OCAllowedSign(OCObject object) {
		super(object);
	}

	public OCAllowedSign(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAllowedSign(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	/**
	 * Строковое представление константы
	 * @return
	 */
	public String getAllowedSignAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getAllowedSignAsString();
	}
}
