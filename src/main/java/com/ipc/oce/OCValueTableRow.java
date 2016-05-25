/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * Предоставляет собой отдельную строку таблицы значений.
 * @author Konovalov
 *
 */
public class OCValueTableRow extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTableRow(final OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTableRow(final IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTableRow(final JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	protected IJIDispatch dispatch() {
		return super.dispatch();
	}
	
	/**
	 * Чтение. Набор свойств содержит значения в колонках таблицы значений по конкретной строке. Доступ к значению осуществляется по имени колонки. 
	 * @param columnName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(String columnName) throws JIException {
		return new OCVariant(get(columnName));
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index  Индекс колонки
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(Integer index) throws JIException {
		return new OCVariant(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Запись. Набор свойств содержит значения в колонках таблицы значений по конкретной строке. Доступ к значению осуществляется по имени колонки. 
	 * @param columnName
	 * @param variant
	 * @throws JIException
	 */
	public void setValue(String columnName, OCVariant variant) throws JIException {
		put(columnName, variant);
	}
	
	public void setValue(int index, OCVariant variant) throws JIException {
		callMethod("Set", new Object[] { new JIVariant(index), ocVariant2JI(variant)});
	}
	
	/**
	 * Получает владельца данной строки.
	 * @return
	 * @throws JIException
	 */
	public OCValueTable getOwner() throws JIException {
		return new OCValueTable(callMethodA("Owner"));
	}
	

}
