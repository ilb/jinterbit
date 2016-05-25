/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.metadata.objects.OCSequenceMetadataObject;

/**
 * Представляет собой набор записей регистрации документа в последовательности. Этот набор формируется автоматически при записи документа по заданным соответствиям реквизитов документа и измерениям последовательности. Объект позволяет считывать набор записей регистрации из базы данных, добавлять, удалять и модифицировать записи в наборе, записывать набор.
 * @author Konovalov
 *
 */
public class OCSequenceRecordSet extends OCObject implements Iterable<OCSequenceRecord>{

	/**
	 * @param object
	 */
	public OCSequenceRecordSet(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCSequenceRecordSet(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCSequenceRecordSet(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые значения, связанные с объектом, на время выполнения некоторых операций, без изменения объекта. Например, при обработке событий в подписке на события.
	 * @return
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException{
		return new OCStructure(get("AdditionalProperties"));
	}
	
	/**
	 *  Используется для управления обменом данных. С помощью данного свойства настраивается состав узлов-получателей, для которых будут регистрироваться изменения данных, узел-отправитель, из которого получена записываемая информация, а также устанавливается режим Загрузка, указывающий, что выполняется перенос информации
	 * @return
	 * @throws JIException
	 */
	public OCDataExchangeParameters getDataExchange() throws JIException{
		return new OCDataExchangeParameters(get("DataExchange"));
	}
	
	/**
	 * Содержит объект Отбор, по которому осуществляется текущая фильтрация
	 * записей при считывании или записи набора. Позволяет отобрать записи с
	 * определенным регистратором при считывании или записи набора. Важно! Отбор
	 * может устанавливаться только на равенство. Примечание: Для установки
	 * фильтрации используется стандартный объект, через который устанавливается
	 * предопределенный элемент отбора.
	 * 
	 * @return OCFilter
	 * @throws JIException
	 */
	public OCFilter getFilter() throws JIException {
		return new OCFilter(get("Filter"));
	}
	
	/**
	 * Ссылка на сам набор записей. Предназначено для получения набора в модуле
	 * набора записей
	 * 
	 * @return OCSequenceRecordSet
	 */
	public OCSequenceRecordSet thisObject(){
		return this;
	}
	
	/**
	 * Создает таблицу значений и копирует в нее записи набора. Структура
	 * полученной таблицы совпадает со структурой набора записей.
	 * XML-сериализация.
	 * 
	 * @param array
	 *            Массив строк для выгрузки. Если не указан, выгружаются все
	 *            строки набора записей.
	 * @param columns
	 *            Список колонок для выгрузки в формате:
	 *            "Колонка1, Колонка2...". Если список не задан, то будут
	 *            выгружены все колонки.
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable unload(OCArray array, String columns) throws JIException {
		return new OCValueTable(callMethodA("Unload", new Object[]{
				array != null ? ocObject2Dispatch(array) : null,
				columns != null ? new JIVariant(columns) : null
		})[0]);
	}
	
	/**
	 * Создает таблицу значений с заданным списком колонок. XML-сериализация.
	 * Примечание: Выгружается только структура набора записей, данные не
	 * выгружаются.
	 * 
	 * @param columns
	 *            Список колонок для выгрузки в формате:
	 *            "Колонка1, Колонка2...". Если список не задан, то будут
	 *            выгружены все колонки.
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable unloadColumns(String columns) throws JIException {
		return new OCValueTable(callMethodA("UnloadColumns", new Object[]{
				columns != null ? new JIVariant(columns) : null
		})[0]);
	}
	
	/**
	 * Добавляет новую запись регистрации документа в набор
	 * 
	 * @return OCSequenceRecord
	 * @throws JIException
	 */
	public OCSequenceRecord add() throws JIException{
		return new OCSequenceRecord(callMethodA("Add"));
	}
	
	/**
	 * Записывает набор записей регистрации документа в базу данных. В зависимости от переданного параметра, может быть выполнено добавление записей или их замещение
	 * @param overwrite Определяет режим замещения существующих записей в соответствии с текущими установками отбора. Истина - перед записью существующие записи будут удалены. Ложь - записи будут дописаны к уже существующим в информационной базе записям.
	 * @throws JIException
	 */
	public void write(Boolean overwrite) throws JIException{
		callMethod("Write", new Object[]{new JIVariant(overwrite)});
	}
	
	/**
	 * Записывает набор записей регистрации документа в базу данных. В зависимости от переданного параметра, может быть выполнено добавление записей или их замещение/
	 * Запись в режиме write(false);
	 * @throws JIException
	 */
	public void write() throws JIException{
		write(false);
	}
	
	/**
	 * Очищает набор записей и заполняет его значениями из указанной таблицы значений. Заполняются значения доступных для записи свойств записей последовательности, имена которых совпали c именами колонок таблицы значений.
	 * @param table Источник для заполнения набора записей
	 * @throws JIException
	 */
	public void load(OCValueTable table) throws JIException{
		callMethod("Load", new Object[]{
			new JIVariant(ocObject2Dispatch(table))	
		});
	}
	
	/**
	 * Заполняет заданное поле у записей набора значениями, взятыми из массива. Значения записей заполняются из элементов массива, имеющих тот же индекс.
	 * @param string  Идентификатор поля записи, в которую будут загружены значения из массива
	 * @param array  Массив, содержащий значения для заполнения колонки набора. 
	 * @throws JIException
	 */
	public void loadColumn(String string, OCArray array) throws JIException{
		callMethod("LoadColumn", new Object[]{new JIVariant(string), new JIVariant(ocObject2Dispatch(array))});
	}
	
	/**
	 * Получает индекс указанной записи в наборе
	 * @param record Запись, индекс которой необходимо получить
	 * @return Индекс указанной записи в наборе. Если не найдено, то возвращается -1. 
	 * @throws JIException
	 */
	public Integer indexOf(OCSequenceRecord record) throws JIException{
		return  callMethodA("IndexOf", new Object[] {new JIVariant(ocObject2Dispatch(record))} )[0].getObjectAsInt();
	}
	
	/**
	 * Получает количество записей регистрации в наборе
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных последовательности. Другой путь получения того же значения - через свойство глобального контекста Метаданные.
	 * @return OCSequenceMetadataObject
	 * @throws JIException
	 */
	public OCSequenceMetadataObject getMetadata() throws JIException{
		return new OCSequenceMetadataObject(callMethodA("Metadata"));
	}
	
	/**
	 * Определяет, были ли изменены записи в наборе после последнего считывания или записи. Метод не позволяет определить, были ли изменены соответствующие записи другими пользователями. 
	 * @return Истина - записи изменены; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isModified() throws JIException{
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Удаляет все записи регистрации из набора
	 * @throws JIException
	 */
	public void clear() throws JIException{
		callMethod("Clear");
	}
	
	/**
	 * Получает значение по индексу
	 * @param index Индекс элемента
	 * @return OCSequenceRecord
	 * @throws JIException
	 */
	public OCSequenceRecord getRecord(Integer index) throws JIException{
		return new OCSequenceRecord(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Считывает набор записей регистрации документа из базы данных.
	 * @throws JIException
	 */
	public void read() throws JIException{
		callMethod("Read");
	}
	
	/**
	 * Удаляет указанную запись регистрации документа из набора. 
	 * @param index  Удаляемая из набора запись. Может быть указан индекс записи в наборе или сама запись
	 * @throws JIException
	 */
	public void delete(Integer index) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(index)});
	}
	
	/**
	 * Удаляет указанную запись регистрации документа из набора. 
	 * @param record  Удаляемая из набора запись. Может быть указан индекс записи в наборе или сама запись
	 * @throws JIException
	 */
	public void delete(OCSequenceRecord record) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(ocObject2Dispatch(record))});
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCSequenceRecord> iterator() {
		EnumVARIANT<OCSequenceRecord> enumV = new EnumVARIANT<OCSequenceRecord>(this) {
			
			@Override
			protected OCSequenceRecord castToGeneric(JIVariant variant) {
				try {
					return new OCSequenceRecord(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
}
