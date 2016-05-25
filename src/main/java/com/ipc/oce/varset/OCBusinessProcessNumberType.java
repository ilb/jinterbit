package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет варианты типов номеров бизнес-процессов.
 * @author Konovalov
 *
 */
public class OCBusinessProcessNumberType extends OCObject implements IOCVariantSet {
	public static final String STRING = "Строка";
	public static final String NUMBER = "Число";
	
	public OCBusinessProcessNumberType(OCObject object) {
		super(object);
	}

	public OCBusinessProcessNumberType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCBusinessProcessNumberType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
