/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.metadata.OCTypeDescription;

/**
 * @author Konovalov
 *
 */
public class OCValueTreeColumnCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTreeColumnCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTreeColumnCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTreeColumnCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения колонок дерева значений. Доступ к
	 * значению осуществляется по имени колонки. Имена свойств совпадают с
	 * именами колонок дерева значений.
	 * 
	 * @param name
	 *            - имя колонки
	 * @return OCValueTreeColumn
	 * @throws JIException
	 */
	public OCValueTreeColumn getColumn(String name) throws JIException {
		return new OCValueTreeColumn(get(name));
	}

	/**
	 * Вставляет колонку в заданную позицию коллекции колонок дерева значений.
	 * 
	 * @param index
	 *            - Индекс в коллекции колонок, с которым необходимо вставить
	 *            колонку.
	 * @param name
	 *            - Имя колонки.
	 * @param type
	 *            - Объект, описывающий допустимые типы значений для колонки.
	 *            Если параметр не указан, в колонке можно будет хранить
	 *            значение любого типа.
	 * @param title
	 *            - Заголовок колонки. Используется при визуальном отображении
	 *            дерева значений. Значение по умолчанию: Пустая строка
	 * @param width
	 *            - Ширина колонки в символах. Используется при визуальном
	 *            отображении дерева значений. Значение по умолчанию: 0
	 * @return OCValueTreeColumn
	 * @throws JIException
	 */
	public OCValueTreeColumn insert(int index, String name, OCTypeDescription type, String title, int width) throws JIException {
		return new OCValueTreeColumn(callMethodA("Insert", new Object[]{
				new JIVariant(index),
				name != null ? new JIVariant(name) : null,
				type != null ? new JIVariant(ocObject2Dispatch(type)) : null,
				title != null ? new JIVariant(title) : null,
				width > 0 ? new JIVariant (width) : null
		})[0]);
	}

	/**
	 * Вставляет колонку в заданную позицию коллекции колонок дерева значений.
	 * 
	 * @param index
	 *            - Индекс в коллекции колонок, с которым необходимо вставить
	 *            колонку.
	 * @return OCValueTreeColumn
	 * @throws JIException
	 */
	public OCValueTreeColumn insert(int index) throws JIException {
		return insert(index, null, null, null, 0);
	}
	
	/**
	 * Добавляет колонку в конец коллекции колонок дерева значений. 
	 * @param name
	 *            - Имя колонки.
	 * @param type
	 *            - Объект, описывающий допустимые типы значений для колонки.
	 *            Если параметр не указан, в колонке можно будет хранить
	 *            значение любого типа.
	 * @param title
	 *            - Заголовок колонки. Используется при визуальном отображении
	 *            дерева значений. Значение по умолчанию: Пустая строка
	 * @param width
	 *            - Ширина колонки в символах. Используется при визуальном
	 *            отображении дерева значений. Значение по умолчанию: 0
	 * @return OCValueTreeColumn
	 * @throws JIException
	 */
	public OCValueTreeColumn add(String name, OCTypeDescription type, String title, int width) throws JIException {
		return new OCValueTreeColumn(callMethodA("Add", new Object[]{
				name != null ? new JIVariant(name) : null,
				type != null ? new JIVariant(ocObject2Dispatch(type)) : null,
				title != null ? new JIVariant(title) : null,
				width > 0 ? new JIVariant (width) : null
		})[0]);
	}
	
	/**
	 * Добавляет колонку в конец коллекции колонок дерева значений. 
	 * @return OCValueTreeColumn
	 * @throws JIException
	 */
	public OCValueTreeColumn add() throws JIException {
		return add(null, null, null, 0);
	}
	
	/**
	 * Получает индекс указанной колонки в коллекции колонок.
	 * @param column - Колонка, для которой необходимо получить индекс. 
	 * @return Индекс указанной колонки в коллекции. Если не найдено, то возвращается -1. 
	 * @throws JIException
	 */
	public int indexOf(OCValueTreeColumn column) throws JIException {
		return callMethodA("IndexOf", new JIVariant(ocObject2Dispatch(column)))[0].getObjectAsInt();
	}
	
	/**
	 * Получает количество колонок дерева значений. 
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Осуществляет поиск колонки дерева значений по имени.
	 * 
	 * @param name
	 *            - Наименование колонки дерева значений.
	 * @return КолонкаДереваЗначений; Неопределено. Если указанная колонка
	 *         отсутствует, то возвращается значение Неопределено.
	 * @throws JIException
	 */
	public OCValueTreeColumn find(String name) throws JIException {
		JIVariant var = callMethodA("Find", new JIVariant(name))[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCValueTreeColumn(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Удаляет все колонки дерева значений. При этом также удаляются все его строки. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index - Индекс колонки. 
	 * @return OCValueTreeColumn
	 * @throws JIException
	 */
	public OCValueTreeColumn getColumn(int index) throws JIException {
		return new OCValueTreeColumn(callMethodA("Get", new JIVariant(index))[0]);
	}
	
	/**
	 * Сдвигает колонку с заданным индексом на указанное смещение. 
	 * @param index - Индекс сдвигаемой колонки в коллекции или сама колонка. 
	 * @param offset - Количество колонок, на которое необходимо сдвинуть колонку. Положительное смещение обозначает сдвиг в конец, отрицательное - в начало коллекции. 
	 * @throws JIException
	 */
	public void move(int index, int offset) throws JIException {
		callMethod("Move", new Object[]{new JIVariant(index), new JIVariant(offset)});
	}
	
	/**
	 * Сдвигает колонку с заданным индексом на указанное смещение. 
	 * @param column - Индекс сдвигаемой колонки в коллекции или сама колонка. 
	 * @param offset - Количество колонок, на которое необходимо сдвинуть колонку. Положительное смещение обозначает сдвиг в конец, отрицательное - в начало коллекции. 
	 * @throws JIException
	 */
	public void move(OCValueTreeColumn column, int offset) throws JIException {
		callMethod("Move", new Object[]{new JIVariant(ocObject2Dispatch(column)), new JIVariant(offset)});
	}
	
	/**
	 * Удаляет колонку в заданной позиции коллекции колонок дерева значений. 
	 * @param index - индекс колонки.
	 * @throws JIException
	 */
	public void delete(int index) throws JIException {
		callMethod("Delete", new JIVariant(index));
	}
	
	/**
	 * Удаляет колонку в заданной позиции коллекции колонок дерева значений. 
	 * @param column - OCValueTreeColumn
	 * @throws JIException
	 */
	public void delete(OCValueTreeColumn column) throws JIException {
		callMethod("Delete", new JIVariant(ocObject2Dispatch(column)));
	}

}
