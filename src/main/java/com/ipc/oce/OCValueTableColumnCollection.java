/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * Представляет собой коллекцию колонок таблицы значений. Доступ к объекту осуществляется через свойство Колонки таблицы значений. 
 * @author Konovalov
 *
 */
public class OCValueTableColumnCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTableColumnCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTableColumnCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTableColumnCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество колонок таблицы значений.
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index
	 * @return
	 * @throws JIException
	 */
	public OCValueTableColumn getTableColumn(int index) throws JIException {
		return new OCValueTableColumn(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Набор свойств содержит значения колонок таблицы значений. Доступ к значению осуществляется по имени колонки. Имена свойств совпадают с именами колонок
	 * @param columnName
	 * @return
	 * @throws JIException
	 */
	public OCValueTableColumn getTabelColumn(String columnName) throws JIException {
		return new OCValueTableColumn(get(columnName));
	}
	
	/**
	 * Осуществляет поиск колонки таблицы значений по имени.
	 * @param columnName Наименование колонки таблицы значений
	 * @return  Если указанная колонка отсутствует, то возвращается значение Неопределено. 
	 * @throws JIException
	 */
	public OCValueTableColumn find(String columnName) throws JIException {
		return new OCValueTableColumn(callMethodA("Find", new Object[]{new JIVariant(columnName)})[0]);
	}
}
