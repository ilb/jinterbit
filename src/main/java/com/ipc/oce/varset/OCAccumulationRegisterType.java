package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет виды регистров накопления
 * @author Konovalov
 *
 */
public class OCAccumulationRegisterType extends OCObject implements IOCVariantSet{
	public static final String TURNOVERS = "Обороты";
	public static final String BALANCE = "Остатки";
	public OCAccumulationRegisterType(OCObject object) {
		super(object);
	}

	public OCAccumulationRegisterType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAccumulationRegisterType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
