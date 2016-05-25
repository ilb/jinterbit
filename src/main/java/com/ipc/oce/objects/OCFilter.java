/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Предназначен для хранения условий отбора. Представляет собой коллекцию
 * элементов отбора. Используется для установки фильтрации данных в различных
 * выборках, наборах записей, визуальных списках. Каждый из элементов может
 * устанавливать одно условие. Весь объект представляет собой составное условие,
 * состоящее из отдельных условий, описываемых элементами, соединяющимися по
 * "И".
 * 
 * @author Konovalov
 * 
 */
public class OCFilter extends OCObject {

	/**
	 * @param object
	 */
	public OCFilter(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCFilter(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCFilter(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Добавляет элемент отбора по переданному описанию поля отбора в виде
	 * "пути". Например, если среди доступных полей есть поле "Контрагент", то
	 * возможно добавление элемента отбора "Контрагент",
	 * "Контрагент.ТипКонтрагента", "Контрагент.ТипКонтрагента.Наименование" и
	 * т.д. Внимание! Метод не доступен для отбора динамических списков
	 * (свойство Отбор объектов СправочникСписок.<Имя справочника>,
	 * ДокументСписок.<Имя документа> и т.д.) Примечание: Добавление возможно
	 * только из коллекции доступных полей.
	 * 
	 * @param desc
	 *            - Описание элемента отбора в виде наименования доступного поля
	 *            или в виде развернутого пути Например, "Контрагент.Код", где
	 *            "Контрагент" - одно из полей доступных для отбора.
	 * @param name
	 *            - Имя элемента отбора. Если не задано, то имя задается
	 *            автоматически по переданному в качестве первого параметра
	 *            описанию.
	 * @param representation
	 *            - Пользовательское представление добавляемого элемента отбора.
	 * @throws JIException
	 */
	public void add(String desc, String name, String representation) throws JIException {
		callMethod("Add", new Object[]{new JIVariant(desc), new JIVariant(name), new JIVariant(representation)});
	}
	
	/**
	 * Добавляет элемент отбора по переданному описанию поля отбора в виде
	 * "пути". Например, если среди доступных полей есть поле "Контрагент", то
	 * возможно добавление элемента отбора "Контрагент",
	 * "Контрагент.ТипКонтрагента", "Контрагент.ТипКонтрагента.Наименование" и
	 * т.д. Внимание! Метод не доступен для отбора динамических списков
	 * (свойство Отбор объектов СправочникСписок.<Имя справочника>,
	 * ДокументСписок.<Имя документа> и т.д.) Примечание: Добавление возможно
	 * только из коллекции доступных полей.
	 * 
	 * @param desc
	 *            - Описание элемента отбора в виде наименования доступного поля
	 *            или в виде развернутого пути Например, "Контрагент.Код", где
	 *            "Контрагент" - одно из полей доступных для отбора.
	 * @param name
	 *            - Имя элемента отбора. Если не задано, то имя задается
	 *            автоматически по переданному в качестве первого параметра
	 *            описанию.
	 * @throws JIException
	 */
	public void add(String desc, String name) throws JIException {
		callMethod("Add", new Object[]{new JIVariant(desc), new JIVariant(name)});
	}
	
	/**
	 * Добавляет элемент отбора по переданному описанию поля отбора в виде
	 * "пути". Например, если среди доступных полей есть поле "Контрагент", то
	 * возможно добавление элемента отбора "Контрагент",
	 * "Контрагент.ТипКонтрагента", "Контрагент.ТипКонтрагента.Наименование" и
	 * т.д. Внимание! Метод не доступен для отбора динамических списков
	 * (свойство Отбор объектов СправочникСписок.<Имя справочника>,
	 * ДокументСписок.<Имя документа> и т.д.) Примечание: Добавление возможно
	 * только из коллекции доступных полей.
	 * 
	 * @param desc
	 *            - Описание элемента отбора в виде наименования доступного поля
	 *            или в виде развернутого пути Например, "Контрагент.Код", где
	 *            "Контрагент" - одно из полей доступных для отбора.
	 * @throws JIException
	 */
	public void add(String desc) throws JIException {
		callMethod("Add", new Object[]{new JIVariant(desc)});
	}
	
	/**
	 * Получает индекс элемента отбора в коллекции отборов.
	 * 
	 * @param item
	 *            - Элемент отбора, индекс которого требуется определить.
	 * @return Индекс указанного элемента в коллекции. Если не найдено, то
	 *         возвращается -1.
	 * @throws JIException
	 */
	public Integer indexOf(OCFilterItem item) throws JIException {
		return callMethodA("IndexOf", new Object[]{ocObject2Dispatch(item)})[0].getObjectAsInt();
	}
	
	/**
	 * Получает количество элементов отбора, входящих в коллекцию.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Осуществляет поиск элемента отбора, входящего в коллекцию.
	 * 
	 * @param itemName
	 *            - Имя элемента отбора.
	 * @return Если элемента с таким именем нет, то возвращается значение
	 *         Неопределено.
	 * @throws JIException
	 */
	public OCFilterItem find(String itemName) throws JIException {
		return new OCFilterItem(callMethodA("Find", new Object[]{new JIVariant(itemName)})[0]);
	}
	
	/**
	 * Получает значение по индексу.
	 * 
	 * @param index
	 *            - Индекс элемента.
	 * @return OCFilterItem
	 * @throws JIException
	 */
	public OCFilterItem getItem(int index) throws JIException {
		return new OCFilterItem(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	/**
	 * Получает значение по имени.
	 * 
	 * @param elementName - имя элемента
	 * @return OCFilterItem
	 * @throws JIException
	 */
	public OCFilterItem getItem(String elementName) throws JIException {
		return new OCFilterItem(get(elementName));
	}
	
	/**
	 * Получает коллекцию доступных для отбора полей. Внимание! Метод не
	 * доступен для отбора динамических списков (свойство Отбор объектов
	 * СправочникСписок.<Имя справочника>, ДокументСписок.<Имя документа> и
	 * т.д.)
	 * 
	 * @return OCCustomFields
	 * @throws JIException
	 */
	public OCCustomFields getAvailableFields() throws JIException {
		return new OCCustomFields(callMethodA("GetAvailableFields"));
	}
	
	/**
	 * Отключает признак использования отбора у всех элементов отбора.
	 * @throws JIException
	 */
	public void reset() throws JIException {
		callMethod("Reset");
	}
	
	/**
	 * Сдвигает элемент отбора.
	 * 
	 * @param index
	 *            - Индекс сдвигаемого элемента отбора.
	 * @param offset
	 *            - Смещение сдвига. Если положительное, то смещение
	 *            производится вперед, если отрицательное - назад.
	 * @throws JIException
	 */
	public void move(int index, int offset) throws JIException {
		callMethod("Move", new Object[]{new JIVariant(index), new JIVariant(offset)});
	}
	
	/**
	 * Удаляет элемент отбора по индексу. Внимание! Метод не доступен для отбора
	 * динамических списков (свойство Отбор объектов СправочникСписок.<Имя
	 * справочника>, ДокументСписок.<Имя документа> и т.д.).
	 * 
	 * @param index
	 *            - индекс элемента
	 * @throws JIException
	 */
	public void delete(int index) throws JIException {
		callMethod("Delete", new Object[]{new JIVariant(index)});
	}
	
	/**
	 * Устанавливает коллекцию полей, доступных для отбора. Внимание! Метод не
	 * доступен для отбора динамических списков (свойство Отбор объектов
	 * СправочникСписок.<Имя справочника>, ДокументСписок.<Имя документа> и
	 * т.д.).
	 * 
	 * @param fields
	 * @throws JIException
	 */
	public void setAvailableFields(OCCustomFields fields) throws JIException {
		callMethod("SetAvailableFields", new Object[]{new JIVariant(ocObject2Dispatch(fields))});
	}
}
