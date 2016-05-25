/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCAccumulationRegisterCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCAccumulationRegisterCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccumulationRegisterCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccumulationRegisterCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	
	/**
	 * Набор свойств содержит менеджеры регистров накопления. Доступ к менеджеру осуществляется по имени. Имена свойств совпадают с именами регистров накопления, как они заданы в конфигураторе
	 * @param accumulationRegisterName
	 * @return
	 * @throws JIException
	 */
	public OCAccumulationRegisterManager getAccumulationRegister(String accumulationRegisterName) throws JIException{
		OCAccumulationRegisterManager arm =  new OCAccumulationRegisterManager(get(accumulationRegisterName));
		arm.setManagerName(accumulationRegisterName);
		return arm;
	}
}
