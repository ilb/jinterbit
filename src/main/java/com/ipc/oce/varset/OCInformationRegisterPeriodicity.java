package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCInformationRegisterPeriodicity extends OCObject implements IOCVariantSet {

	public static final String YEAR = "Год";
	public static final String DAY = "День";
	public static final String QUARTER = "Квартал";
	public static final String MONTH = "Месяц";
	public static final String NONPERIODICAL = "Непериодический";
	public static final String RECORDER_POSITION = "ПозицияРегистратора";
	public static final String SECOND = "Секунда";
	
	public OCInformationRegisterPeriodicity(OCObject object) {
		super(object);
	}

	public OCInformationRegisterPeriodicity(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCInformationRegisterPeriodicity(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
