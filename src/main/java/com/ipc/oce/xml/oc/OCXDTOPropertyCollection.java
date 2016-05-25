/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Для объекта доступен обход коллекции посредством оператора Для каждого … Из … Цикл. При обходе выбираются свойства объекта XDTO.
 * Возможно обращение к свойству объекта XDTO посредством оператора [...]. В качестве аргумента передается индекс (нумерация с 0).
 * @author Konovalov
 *
 */
public class OCXDTOPropertyCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTOPropertyCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOPropertyCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOPropertyCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество элементов коллекции
	 * @return Integer
	 * @throws JIException
	 */
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает свойство по индексу
	 * @param index
	 * @return OCXDTOProperty
	 * @throws JIException
	 */
	public OCXDTOProperty getProperty(Integer index) throws JIException{
		return new OCXDTOProperty(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Получает свойство по имени
	 * @param name
	 * @return OCXDTOProperty
	 * @throws JIException
	 */
	public OCXDTOProperty getProperty(String name) throws JIException{
		return new OCXDTOProperty(callMethodA("Get", new Object[]{new JIVariant(name)})[0]);
	}
}
