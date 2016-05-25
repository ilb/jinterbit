/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Используется для доступа к свойствам и методам строки дерева значений.
 * Позволяет читать и записывать данные в конкретных колонках строки, также
 * обладает коллекцией подчиненных строк (может быть пустой).
 * 
 * @author Konovalov
 * 
 */
public class OCValueTreeRow extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTreeRow(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTreeRow(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTreeRow(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения в колонках данной строки дерева значений.
	 * Доступ к значению осуществляется по имени колонки. Имена свойств
	 * совпадают с именами колонок дерева значений.
	 * 
	 * @param columnName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(String columnName) throws JIException {
		return new OCVariant(get(columnName));
	}
	
	/**
	 * Набор свойств содержит значения в колонках данной строки дерева значений.
	 * Доступ к значению осуществляется по имени колонки. Имена свойств
	 * совпадают с именами колонок дерева значений.
	 * 
	 * @param columnName
	 * @param value
	 * @throws JIException
	 */
	public void setValue(String columnName, OCVariant value) throws JIException {
		put(columnName, ocVariant2JI(value));
	}
	
	/**
	 * СтрокаДереваЗначений. Содержит строку-родителя для данной строки дерева
	 * значений. Если строка находится в корне дерева (на верхнем уровне
	 * иерархии), то свойство имеет значение Неопределено.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCValueTreeRow getParent() throws JIException {
		JIVariant var = get("Parent");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCValueTreeRow(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Содержит коллекцию строк, подчиненных данной строке дерева значений.
	 * 
	 * @return OCValueTreeRowCollection
	 * @throws JIException
	 */
	public OCValueTreeRowCollection getRows() throws JIException {
		return new OCValueTreeRowCollection(get("Rows"));
	}
	
	/**
	 * Получает владельца данной строки.
	 * @return
	 * @throws JIException
	 */
	public OCValueTree getOwner() throws JIException {
		return new OCValueTree(callMethodA("Owner"));
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(int index) throws JIException {
		return new OCVariant(callMethodA("Get", new JIVariant(index))[0]);
	}
	
	/**
	 * Получает уровень строки дерева значений. Нумерация уровней дерева
	 * значений начинается с 0.
	 * 
	 * @return уровень строки дерева значений
	 * @throws JIException
	 */
	public int getLevel() throws JIException {
		return callMethodA("Level").getObjectAsInt();
	}
	
	/**
	 * Устанавливает значение по индексу.
	 * @param index - Индекс строки. 
	 * @param value - Устанавливаемое значение.
	 * @throws JIException
	 */
	public void setValue(int index, OCVariant value) throws JIException {
		callMethod("Set", new Object[]{
				new JIVariant(index),
				ocVariant2JI(value)
		});
	}

}
