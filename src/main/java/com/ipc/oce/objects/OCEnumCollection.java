package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCEnumCollection extends OCObject {

	public OCEnumCollection(OCObject object) {
		super(object);
	}

	public OCEnumCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCEnumCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 *  Набор свойств содержит менеджеры отдельных перечислений. Доступ к менеджеру осуществляется по имени, как они заданы в конфигураторе. 
	 * @param enumName
	 * @return
	 * @throws JIException
	 */
	public OCEnumManager getEnum(String enumName) throws JIException{
		OCEnumManager manager =  new OCEnumManager(get(enumName));
		manager.setManagerName(enumName);
		return manager;
	}

}
