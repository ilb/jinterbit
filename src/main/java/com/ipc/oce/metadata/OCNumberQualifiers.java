package com.ipc.oce.metadata;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.varset.OCAllowedSign;


public class OCNumberQualifiers extends OCObject {

	public OCNumberQualifiers(OCObject object) {
		super(object);
	}

	public OCNumberQualifiers(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCNumberQualifiers(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит общее количество разрядов числа (количество разрядов целой части плюс количество разрядов дробной части). 
	 * @return
	 * @throws JIException
	 */
	public int getDigits() throws JIException {
		return get("Digits").getObjectAsInt();
	}
	/**
	 * Содержит число разрядов дробной части. 
	 * @return
	 * @throws JIException
	 */
	public int getFractionDigits() throws JIException {
		return get("FractionDigits").getObjectAsInt();
	}
	
	/**
	 * Определяет допустимый знак числа.
	 * 
	 * @return OCAllowedSign
	 * @throws JIException
	 */
	public OCAllowedSign getAllowedSign() throws JIException {
		return new OCAllowedSign(get("AllowedSign"));
	}

}
