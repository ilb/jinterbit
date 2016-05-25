/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCValueList;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Коллекция допустимых полей для порядка, отбора, коллекций полей построителя
 * отчета.
 * 
 * @author Konovalov
 * 
 */
public class OCCustomFields extends OCObject {

	/**
	 * @param object
	 */
	public OCCustomFields(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCCustomFields(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCCustomFields(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Вставляет поле в указанную позицию коллекции. 
	 * @param name - Имя поля. 
	 * @param presentation - Представление поля.
	 * @param [typeDescription] - Тип поля.
	 * @param [valueList] - Список допустимых значений для поля. 
	 * @param position - Позиция, в которую необходимо вставить поле. 
	 * @return OCCustomField
	 * @throws JIException
	 */
	public OCCustomField insert(String name, String presentation, OCTypeDescription typeDescription, OCValueList valueList, int position) throws JIException {
		return new OCCustomField(
				callMethodA("Insert", new Object[]{
						new JIVariant(name),
						new JIVariant(presentation),
						typeDescription != null ? ocObject2Dispatch(typeDescription) : null,
						valueList != null ? ocObject2Dispatch(valueList) : null,
						new JIVariant(position)
				})[0]
				);
	}
	
	/**
	 * Добавляет поле в коллекцию. 
	 * @param name - Имя поля. 
	 * @param presentation - Представление поля.
	 * @param [typeDescription] - Тип поля.
	 * @param [valueList] - Список допустимых значений для поля. 
	 * @return OCCustomField
	 * @throws JIException
	 */
	public OCCustomField add(String name, String presentation, OCTypeDescription typeDescription, OCValueList valueList) throws JIException {
		return new OCCustomField(
				callMethodA("Add", new Object[]{
						new JIVariant(name),
						new JIVariant(presentation),
						typeDescription != null ? ocObject2Dispatch(typeDescription) : null,
						valueList != null ? ocObject2Dispatch(valueList) : null
				})[0]
				);
	}
	
	/**
	 * Получает индекс элемента коллекции.
	 * @param field - Искомое поле настройки. 
	 * @return Индекс указанного элемента коллекции. Если такого элемента нет, возвращается -1. 
	 * @throws JIException
	 */
	public int indexOf(OCCustomField field) throws JIException {
		return callMethodA("IndexOf", new Object[]{new JIVariant(ocObject2Dispatch(field))})[0].getObjectAsInt();
	}
	
	/**
	 * Получает количество полей в коллекции полей настройки.
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Осуществляет поиск поля по имени. 
	 * @param name - Имя искомого поля. 
	 * @return ПолеНастройки; Неопределено. Если поле не найдено, то возвращается значение Неопределено. z
	 * @throws JIException
	 */
	public OCCustomField find(String name) throws JIException {
		JIVariant var = callMethodA("Find", new JIVariant(name))[0];
		if (var.getType() == JIVariant.VT_EMPTY) {
			return null;
		} else {
			return new OCCustomField(var);
		}
	}
	
	/**
	 * Удаляет все поля из коллекции. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Получает поле по индексу.
	 * @param index - Индекс поля.
	 * @return OCCustomField
	 * @throws JIException
	 */
	public OCCustomField get(int index) throws JIException {
		return new OCCustomField(callMethodA("Get", new JIVariant(index))[0]);
	}

	/**
	 * Сдвигает заданный элемент коллекции.
	 * 
	 * @param field
	 *            - Перемещаемое поле
	 * @param offset
	 *            - Указывается, на сколько позиций осуществить сдвиг.
	 * @throws JIException
	 */
	public void move(OCCustomField field, int offset) throws JIException {
		callMethod("Move", new Object[]{new JIVariant(ocObject2Dispatch(field)), new JIVariant(offset)});
	}
	
	/**
	 * Удаляет поле из коллекции. 
	 * @param field - Удаляемое поле. 
	 * @throws JIException
	 */
	public void delete(OCCustomField field) throws JIException {
		callMethod("Delete", new Object[]{new JIVariant(ocObject2Dispatch(field))});
	}
	
}
