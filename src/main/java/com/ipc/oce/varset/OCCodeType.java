package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCCodeType extends OCObject implements IOCVariantSet{
	public static final String STRING = "Строка";
	public static final String NUMBER = "Число";
	
	public OCCodeType(OCObject object) {
		super(object);
	}

	public OCCodeType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCodeType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public String getCodeTypeAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getCodeTypeAsString();
	}

}
