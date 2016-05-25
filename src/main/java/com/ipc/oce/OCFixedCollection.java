/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Системная коллекция значений. Используется в качестве значений свойств других объектов. Заполняется системой при инициализации объектов данного типа. 
 * Создать этот объект средствами встроенного языка нельзя. Набор свойств этого объекта определяется в зависимости от контекста его использования и описан подробнее в описании тех свойств, где он используется. 
 * @author Konovalov
 *
 */
public class OCFixedCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCFixedCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCFixedCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCFixedCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index индекс элемента
	 * @return
	 * @throws JIException
	 */
	public OCObject getElement(int index) throws JIException {
		return new OCObject(callMethodA("Get", new Object[] { new JIVariant(
				index) })[0]);
	}
	
	/**
	 * Осуществляет поиск значения элемента коллекции по его имени.
	 * 
	 * @param objectName
	 *            Наименование элемента коллекции.
	 * @return
	 * @throws JIException
	 */
	public OCObject find(String objectName) throws JIException {
		return new OCObject(callMethodA("Get", new Object[] { new JIVariant(
				objectName) })[0]);
	}
	
	/**
	 * Получает количество элементов в коллекции.
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает индекс элемента в системной коллекции значений.
	 * @param object Значение, по которому ищется элемент коллекции
	 * @return Индекс указанного элемента в коллекции. Если не найдено, то возвращается -1
	 * @throws JIException
	 */
	public int indexOf(OCObject object) throws JIException {
		return callMethodA("Get", new Object[]{new JIVariant(object.dispatch())})[0].getObjectAsInt();
	}
	
	/**
	 * Получает индекс элемента в системной коллекции значений.
	 * @param object Значение, по которому ищется элемент коллекции
	 * @return Индекс указанного элемента в коллекции. Если не найдено, то возвращается -1
	 * @throws JIException
	 */
	public int indexOf(OCVariant object) throws JIException {
		return callMethodA("Get", new Object[]{ocVariant2JI(object)})[0].getObjectAsInt();
	}
}
