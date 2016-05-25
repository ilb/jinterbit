/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Используется для доступа к свойствам и методам дерева значений в целом.
 * Объект, представляющий собой древовидную структуру, обладает сходной
 * функциональностью с таблицей значений, а также обладает возможностью
 * добавлять подчиненные строки к какой-либо строке дерева.
 * 
 * @author Konovalov
 * 
 */
public class OCValueTree extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTree(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTree(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTree(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит коллекцию колонок дерева значений.
	 * @return OCValueTreeColumnCollection
	 * @throws JIException
	 */
	public OCValueTreeColumnCollection getColumns() throws JIException {
		return new OCValueTreeColumnCollection(get("Columns"));
	}
	
	/**
	 * Содержит коллекцию строк 1-го уровня дерева значений. Примечание:
	 * Коллекции строк следующих уровней можно получить, используя свойство
	 * Строки, имеющееся у каждой строки дерева значений.
	 * 
	 * @return OCValueTreeRowCollection
	 * @throws JIException
	 */
	public OCValueTreeRowCollection getRows() throws JIException {
		return new OCValueTreeRowCollection(get("Rows"));
	}
	
	/**
	 * Создает полную копию исходного дерева значений. 
	 * @return
	 * @throws JIException
	 */
	public OCValueTree copy() throws JIException {
		return new OCValueTree(callMethodA("Copy"));
	}

}
