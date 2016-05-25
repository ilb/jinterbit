package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет варианты режима записи регистра сведений. 
 * @author Konovalov
 *
 */
public class OCRegisterWriteMode extends OCObject implements IOCVariantSet {

	public static final String INDEPENDENT = "Независимый";
	public static final String RECORDER_SUBORDINATE = "ПодчинениеРегистратору";
	
	public OCRegisterWriteMode(OCObject object) {
		super(object);
	}

	public OCRegisterWriteMode(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCRegisterWriteMode(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
