package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Задает набор вариантов периодичности регистра расчета. 
 * @author Konovalov
 *
 */
public class OCCalculationRegisterPeriodicity extends OCObject {

	public static final String YEAR = "Год";
	public static final String DAY = "День";
	public static final String QUARTER = "Квартал";
	public static final String MONTH = "Месяц";
	
	public OCCalculationRegisterPeriodicity(OCObject object) {
		super(object);
	}

	public OCCalculationRegisterPeriodicity(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCalculationRegisterPeriodicity(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	public String getCalculationRegisterPeriodicityAsString(){
		return toString();
	}

}
