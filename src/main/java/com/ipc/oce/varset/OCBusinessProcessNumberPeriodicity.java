package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет набор вариантов периодичности номеров бизнес-процессов. 
 * @author Konovalov
 *
 */
public class OCBusinessProcessNumberPeriodicity extends OCObject implements IOCVariantSet {

	public static final String YEAR = "Год";
	public static final String DAY = "День";
	public static final String QUARTER = "Квартал";
	public static final String MONTH = "Месяц";
	public static final String NONPERIODICAL = "Непериодический";
	
	public OCBusinessProcessNumberPeriodicity(OCObject object) {
		super(object);
	}

	public OCBusinessProcessNumberPeriodicity(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCBusinessProcessNumberPeriodicity(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
