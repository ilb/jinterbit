package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCDateFractions extends OCObject implements IOCVariantSet {
	public static final String TIME = "Время";
	public static final String DATE = "Дата";
	public static final String DATE_TIME = "ДатаВремя";
	
	public OCDateFractions(OCObject object) {
		super(object);
	}

	public OCDateFractions(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDateFractions(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	public String getDateFractionsAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getDateFractionsAsString();
	}
}
