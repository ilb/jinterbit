package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Содержит варианты режимов управления блокировкой данных, устанавливаемых по умолчанию. 
 * @author Konovalov
 *
 */
public class OCDefaultDataLockControlMode extends OCObject implements IOCVariantSet{
	public static final String AUTOMATIC = "Автоматический";
	public static final String MANAGED = "Управляемый";
	public static final String AUTOMATIC_AND_MANAGED = "АвтоматическийИУправляемый";
	
	public OCDefaultDataLockControlMode(OCObject object) {
		super(object);
	}

	public OCDefaultDataLockControlMode(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDefaultDataLockControlMode(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public String getControlModeAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getControlModeAsString();
	}
}
