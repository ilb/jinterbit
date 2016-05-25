/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Доступ к объекту осуществляется через свойство глобального контекста
 * РегистрыБухгалтерии. Используется для доступа к объектам, управляющим
 * регистрами бухгалтерии
 * 
 * @author Konovalov
 * 
 */
public class OCAccountingRegisterCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCAccountingRegisterCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccountingRegisterCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccountingRegisterCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит менеджеры регистров бухгалтерии. Доступ к менеджеру осуществляется по имени. Имена свойств совпадают с именами регистров бухгалтерии, как они заданы в конфигураторе. 
	 * @param registerName - имя регистра
	 * @return OCAccountingRegisterManager
	 * @throws JIException
	 */
	public OCAccountingRegisterManager getAccountingRegister(String registerName) throws JIException {
		OCAccountingRegisterManager manager = new OCAccountingRegisterManager(get(registerName));
		manager.setManagerName(registerName);
		return manager;
	}

}
