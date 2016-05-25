package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCAttributeUse extends OCObject implements IOCVariantSet{

	public static final String FOR_FOLDER = "ДляГруппы";
	public static final String FOR_FOLDER_AND_ITEM = "ДляГруппыИЭлемента";
	public static final String FOR_ITEM = "ДляЭлемента";
	
	public OCAttributeUse(OCObject object) {
		super(object);
	}

	public OCAttributeUse(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAttributeUse(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public String getAttributeUseAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getAttributeUseAsString();
	}
}
