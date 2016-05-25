package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCAccountType extends OCObject implements IOCVariantSet{
	public static final String ACTIVE_PASSIVE = "Активный/Пассивный";
	public static final String ACTIVE = "Активный";
	public static final String PASSIVE = "Пассивный";
	
	public OCAccountType(OCObject object) {
		super(object);
	}

	public OCAccountType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAccountType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	

	
	public String stringValue() {
		return toString();
	}

}
