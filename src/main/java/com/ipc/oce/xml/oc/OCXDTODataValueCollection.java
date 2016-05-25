/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Коллекция значений простого типа XDTO
 * @author Konovalov
 *
 */
public class OCXDTODataValueCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTODataValueCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTODataValueCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTODataValueCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов коллекции
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает элемент коллекции по индексу
	 * @param Индекс элемента коллекции. Индекс не должен выходить за пределы размеров коллекции.
	 * @return OCXDTODataValue
	 * @throws JIException
	 */
	public OCXDTODataValue getDataValue(int index) throws JIException{
		return new OCXDTODataValue(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}

}
