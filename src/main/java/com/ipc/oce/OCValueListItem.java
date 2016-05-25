/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Используется для доступа к свойствам и методам элемента списка значений.
 * 
 * @author Konovalov
 * 
 */
public class OCValueListItem extends OCObject {

	/**
	 * @param object
	 */
	public OCValueListItem(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueListItem(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueListItem(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит хранимое элементом списка значение. 
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException {
		JIVariant var = get("Value");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCVariant(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Установка значения элемента.
	 * @param variant значение элемента.
	 * @throws JIException
	 */
	public void setValue(OCVariant variant) throws JIException {
		put("Value", variant);
	}

}
