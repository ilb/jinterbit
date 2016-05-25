/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Предназначен для управления регистрами сведений и предоставляет доступ к
 * значениям типа РегистрСведенийМенеджер.<Имя регистра сведений>. Доступ к
 * объекту осуществляется через свойство глобального контекста РегистрыСведений.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterCollection extends OCObject implements Iterable<OCInformationRegisterManager>{

	/**
	 * @param object
	 */
	public OCInformationRegisterCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCInformationRegisterCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInformationRegisterCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Доступ к менеджеру осуществляется по имени. Имена свойств совпадают с именами регистров сведений, как они заданы в конфигураторе. 
	 * @param registerName - имя регистра сведений.
	 * @return OCInformationRegisterManager
	 * @throws JIException
	 */
	public OCInformationRegisterManager getInformationRegister(String registerName) throws JIException {
		OCInformationRegisterManager manager = new OCInformationRegisterManager(get(registerName));
		manager.setManagerName(registerName);
		return manager;
	}

	/**
	 * Итератор коллекции с элементами OCInformationRegisterManager
	 */
	public Iterator<OCInformationRegisterManager> iterator() {
		EnumVARIANT<OCInformationRegisterManager> enumV = new EnumVARIANT<OCInformationRegisterManager>(this) {
			
			@Override
			protected OCInformationRegisterManager castToGeneric(JIVariant variant) {
				try {
					OCInformationRegisterManager manager = new OCInformationRegisterManager(variant);
					manager.setManagerName(manager.toString().split("\\.")[1]);
					return manager;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

}
