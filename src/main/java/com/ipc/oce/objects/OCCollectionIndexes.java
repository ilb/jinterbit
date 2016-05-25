/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Список индексов коллекции. 
 * @author Konovalov
 *
 */
public class OCCollectionIndexes extends OCObject {

	/**
	 * @param object
	 */
	public OCCollectionIndexes(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCCollectionIndexes(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCCollectionIndexes(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Добавляет индекс в список индексов коллекции 
	 * @param column Строковое описание колонок индекса в виде: "Колонка1, Колонка2...".
	 * @throws JIException
	 */
	public OCCollectionIndex add(String column) throws JIException{
		return new OCCollectionIndex(callMethodA("Add",
				new Object[] { new JIVariant(column) })[0]);
	}
	
	/**
	 * Получает количество индексов в коллекции
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Удаляет все индексы коллекции
	 * @throws JIException
	 */
	public void clear() throws JIException{
		callMethod("Clear");
	}
	
	/**
	 * Удаляет индекс из коллекции
	 * @param index Порядковый индекс объекта в коллекции
	 * @throws JIException
	 */
	public void delete(Integer index) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(index)});
	}
	
	/**
	 * Удаляет индекс из коллекции
	 * @param index Удаляемый индекс (объект) коллекции. 
	 * @throws JIException
	 */
	public void delete(OCCollectionIndex index) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(index.dispatch())});
	}

}
