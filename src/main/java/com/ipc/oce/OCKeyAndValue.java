/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Элемент коллекции Соответствие. Представляет собой пару из ключа и
 * соответствующего ключу значения.
 * 
 * @author Konovalov
 * 
 */
public class OCKeyAndValue extends OCObject {
	
	private OCVariant key = null;
	
	private OCVariant value = null;

	/**
	 * @param object
	 */
	public OCKeyAndValue(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCKeyAndValue(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCKeyAndValue(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит ключ элемента соответствия. Рекомендуется, чтобы в качестве
	 * ключа выступало значение примитивного типа или другого типа, значение
	 * которого может только присваиваться, но не может менять свое содержимое.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCVariant getKey() throws JIException {
		if (key == null) {
			key = new OCVariant(get("Key"));
		}
		return key;
	}
	
	/**
	 * Содержит значение элемента соответствия. 
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException {
		if (value == null) {
			value = new OCVariant(get("Value"));
		}
		return value;
	}

	@Override
	public boolean equals(Object paramObject) {
		if (paramObject == null) {
			return false;
		}
		if (!(paramObject instanceof OCKeyAndValue)) {
			return false;
		}
		OCKeyAndValue param = (OCKeyAndValue) paramObject;
		try {
			return (param.getKey().equals(getKey()) && param.getValue().equals(getValue()));
		} catch (JIException e) {
			return false;
		}
	}
	
	
	
	

}
