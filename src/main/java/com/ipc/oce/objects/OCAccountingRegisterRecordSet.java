/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.metadata.objects.OCAccountingRegisterMetadataObject;

/**
 * Представляет собой коллекцию записей регистра бухгалтерии. Позволяет
 * выполнять считывание записей из базы данных по определенному регистратору,
 * добавлять, удалять и модифицировать записи в наборе. Также может быть
 * выполнена запись в базу данных по определенному условию. При записи обычно
 * выполняется замещение всех имеющихся в базе данных записей по данному условию
 * на записи, содержащиеся в наборе. Если записи не замещаются, то после
 * выполнения записи набор очищается. Максимальное число записей в наборе
 * 999999999. XML-сериализация. Поддержка отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data/enterprise/current-config}. Имя типа XDTO:
 * AccountingRegisterRecordSet.<Имя регистра бухгалтерии>.
 * 
 * @author Konovalov
 * 
 */
public class OCAccountingRegisterRecordSet extends OCObject implements MetadataHolder {

	/**
	 * @param object
	 */
	public OCAccountingRegisterRecordSet(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccountingRegisterRecordSet(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccountingRegisterRecordSet(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые значения, связанные с объектом, на время выполнения некоторых операций, без изменения объекта. Например, при обработке событий в подписке на события. XML-сериализация. 
	 * @return
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException{
		return new OCStructure(get("AdditionalProperties"));
	}

	/**
	 * Используется для управления обменом данных. С помощью данного свойства
	 * настраивается состав узлов-получателей, для которых будут
	 * регистрироваться изменения данных, узел-отправитель, из которого получена
	 * записываемая информация, а также устанавливается режим Загрузка,
	 * указывающий, что выполняется перенос информации.
	 * 
	 * @return OCDataExchangeParameters
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
	public OCFilter getFilter() throws JIException{
		return new OCFilter(get("Filter"));
	}
	
	/**
	 * Ссылка на сам набор записей. Предназначено для получения набора в модуле
	 * набора записей
	 * 
	 * @return OCAccountingRegisterRecordSet
	 */
	public OCAccountingRegisterRecordSet thisObject() {
		return this;
	}
	
	/**
	 * Вставляет новую запись в набор на указанную позицию.
	 * 
	 * @param index
	 *            - Индекс позиции, на которую надо вставить новую запись
	 * @return Созданная запись регистра
	 * @throws JIException
	 */
	public OCAccountingRegisterRecord insert(int index) throws JIException {
		return new OCAccountingRegisterRecord(callMethodA("Insert",
				new Object[] { new JIVariant(index) })[0]);
	}
	
	/**
	 * При изменении отбора считается, что набор перестает быть считанным.
	 * 
	 * @return Истина - набор не изменялся; Ложь - изменялся, в частности, если
	 *         набор не считывался и не записывался, а также, если записывался с
	 *         добавлением записей.
	 * @throws JIException
	 */
	public Boolean isSelected() throws JIException{
		return callMethodA("Selected").getObjectAsBoolean();
	}
	
	/**
	 * Создает таблицу значений и копирует в нее записи набора. Структура
	 * полученной таблицы совпадает со структурой набора записей. Значения
	 * субконто выгружаются вместе с видами субконто. Для каждого субконто
	 * создается пара колонок с идентификаторами вида ВидСубконто<Номер>,
	 * Субконто<Номер>, для регистра не поддерживающего корреспонденцию, и
	 * ВидСубконтоДт<Номер>, СубконтоДт<Номер>, ВидСубконтоКт<Номер>,
	 * СубконтоКт<Номер>, для регистра поддерживающего корреспонденцию. При этом
	 * номера <Номер> могут не совпадать с номерами видов субконто на
	 * соответствующем счете.
	 * 
	 * @param rows
	 *            - Массив строк для выгрузки. Если не указан, выгружаются все
	 *            строки набора записей.
	 * @param columns
	 *            - Список колонок для выгрузки в формате:
	 *            "Колонка1, Колонка2...". Если список не задан, то будут
	 *            выгружены все колонки.
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable unload(OCArray rows, String columns) throws JIException {
		return new OCValueTable(callMethodA("Unload", new Object[]{
				rows != null ? ocObject2Dispatch(rows) : null,
				new JIVariant(columns)
		})[0]);
	}
	
	/**
	 * Создает таблицу значений с заданным списком колонок. XML-сериализация.
	 * Примечание: Выгружается только структура набора записей, данные не
	 * выгружаются.
	 * 
	 * @param columns
	 *            - Список колонок для выгрузки в формате:
	 *            "Колонка1, Колонка2...". Если список не задан, то будут
	 *            выгружены все колонки.
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable unloadColumns(String columns) throws JIException {
		return new OCValueTable(callMethodA("UnloadColumns", new Object[]{new JIVariant(columns)})[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения заданного поля записей набора.
	 * Примечание: Значения записей выгружаются в порядке индексов.
	 * 
	 * @param columnName
	 *            - Имя поля записей набора, значения из которого будут
	 *            выгружены в массив
	 * @return OCArray
	 * @throws JIException
	 */
	public OCArray unloadColumn(String columnName) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new Object[]{new JIVariant(columnName)})[0]);
	}
	
	/**
	 * Добавляет новую запись регистра в набор.
	 * @return OCAccountingRegisterRecord
	 * @throws JIException
	 */
	public OCAccountingRegisterRecord add() throws JIException {
		return new OCAccountingRegisterRecord(callMethodA("Add"));
	}
	
	/**
	 * Добавляет запись с установленным видом движения "Дебет". Примечание:
	 * Имеет смысл только для регистров без корреспонденции.
	 * 
	 * @throws JIException
	 */
	public void addDebit() throws JIException {
		// странно что по документации ничего не возвращает
		callMethod("AddDebit");
	}
	
	/**
	 * Добавляет запись с установленным видом движения "Кредит". Примечание:
	 * Имеет смысл только для регистров без корреспонденции.
	 * 
	 * @throws JIException
	 */
	public void addCredit() throws JIException {
		callMethod("AddCredit");
	}
	
	/**
	 * Загружает набор записей значениями из переданной таблицы значений. При
	 * этом все прежние записи набора удаляются. Заполняются значения доступных
	 * для записи свойств записей регистра бухгалтерии, имена которых совпали c
	 * именами колонок таблицы значений. Для каждого субконто создается пара
	 * колонок с идентификаторами вида ВидСубконто<Номер>, Субконто<Номер>, для
	 * регистра не поддерживающего корреспонденцию, и ВидСубконтоДт<Номер>,
	 * СубконтоДт<Номер>, ВидСубконтоКт<Номер>, СубконтоКт<Номер>, для регистра
	 * поддерживающего корреспонденцию. При этом номера <Номер> могут не
	 * совпадать с номерами видов субконто на соответствующем счете.
	 * 
	 * @param table
	 *            - Таблица значений, содержащая данные для заполнения набора
	 *            записей.
	 * @throws JIException
	 */
	public void load(OCValueTable table) throws JIException {
		callMethod("Load" , new Object[]{
				ocObject2Dispatch(table)
		});
	}
	
	/**
	 * Загружает значения из массива в заданное поле набора записей. Примечание:
	 * Значения из массива загружаются в поле в порядке индексов.
	 * 
	 * @param array
	 *            - Массив, содержащий значения для заполнения колонки набора.
	 * @param fieldName
	 *            - Имя поля записей набора, в которое будут загружаться
	 *            значения из массива.
	 * @throws JIException
	 */
	public void loadColumn(OCArray array, String fieldName) throws JIException {
		callMethod("LoadColumn", new Object[] { ocObject2Dispatch(array),
				new JIVariant(fieldName) });
	}
	
	/**
	 * Записывает в базу данных набор записей регистра бухгалтерии. В
	 * зависимости от переданного параметра может быть выполнено добавление
	 * записей или их замещение. Примечание: При вызове с параметром <Замещать>
	 * равным Ложь после записи в информационную базу набор записей очищается
	 * (удаляются записи из набора).
	 * 
	 * @param mode
	 *            - Определяет режим замещения существующих записей в
	 *            соответствии с текущими установками отбора. Истина - перед
	 *            записью существующие записи будут удалены. Ложь - записи будут
	 *            дописаны к уже существующим в информационной базе записям.
	 *            Значение по умолчанию: Истина
	 * @throws JIException
	 */
	public void write(boolean mode) throws JIException {
		callMethod("Write", new Object[]{new JIVariant(mode)});
	}
	
	/**
	 * Записывает в базу данных набор записей регистра бухгалтерии. В
	 * зависимости от переданного параметра может быть выполнено добавление
	 * записей или их замещение. Примечание: При вызове с параметром <Замещать>
	 * равным Ложь после записи в информационную базу набор записей очищается
	 * (удаляются записи из набора).
	 * 
	 * @throws JIException
	 */
	public void write() throws JIException{
		callMethod("Write");
	}
	
	/**
	 * Получает индекс записи в наборе.
	 * 
	 * @param record
	 *            - Запись набора, для которой необходимо получить индекс.
	 * @return Индекс указанной записи в наборе. Если не найдено, то
	 *         возвращается -1.
	 * @throws JIException
	 */
	public Integer indexOf(OCAccountingRegisterRecord record) throws JIException {
		return callMethodA("IndexOf", new Object[]{ocObject2Dispatch(record)})[0].getObjectAsInt();
	}
	
	/**
	 * Суммирует значения всех строк в указанном поле набора. Если для поля
	 * набора записей установлен тип и он единственный, то при суммировании
	 * будет предприниматься попытка преобразования значения к типу Число. Если
	 * полю не присвоены типы, то в процессе суммирования будут принимать
	 * участие только значения, имеющие тип Число, значения других типов будут
	 * игнорироваться. Если для поля несколько типов и среди них есть тип Число,
	 * то в процессе суммирования будут принимать участие только значения,
	 * имеющие тип Число, значения других типов будут игнорироваться. Если для
	 * поля несколько типов и среди них нет типа Число, то результатом будет
	 * значение Неопределено.
	 * 
	 * @param fieldName
	 *            - Имя поля записей набора, по которому необходимо подсчитать
	 *            итог.
	 * @return Числовое значения результата вычисления.
	 * @throws JIException
	 */
	public Integer getTotal(String fieldName) throws JIException {
		return callMethodA("Total", new Object[]{new JIVariant(fieldName)})[0].getObjectAsInt();
	}
	
	/**
	 * Получает количество записей в наборе.
	 * @return количество записей
	 * @throws JIException
	 */
	public Integer size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных регистра
	 * бухгалтерского учета. Другой путь получения того же значения - через
	 * свойство глобального контекста Метаданные. Например:
	 * Метаданные.РегистрыБухгалтерии.Хозрасчетный.
	 */
	public OCAccountingRegisterMetadataObject getMetadata() throws JIException {
		return new OCAccountingRegisterMetadataObject(callMethodA("Metadata"));
	}
	
	/**
	 * Определяет, были ли изменены записи в наборе после последнего считывания
	 * или записи. Примечание: Метод не позволяет определить, были ли изменены
	 * соответствующие записи другими пользователями.
	 * 
	 * @return Истина - записи изменены; Ложь - в противном случае.
	 * @throws JIException
	 */
	public Boolean isModified() throws JIException{
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Очищает набор записей регистра бухгалтерии, удаляя из него все записи. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Получает значение по индексу.
	 * 
	 * @param index
	 *            - Индекс записи.
	 * @return OCAccountingRegisterRecord
	 * @throws JIException
	 */
	public OCAccountingRegisterRecord get(int index) throws JIException {
		return new OCAccountingRegisterRecord(callMethodA("Get",
				new Object[] { new JIVariant(index) })[0]);
	}
	
	/**
	 * Считывает записи из базы данных по установленному отбору.
	 * @throws JIException
	 */
	public void read() throws JIException {
		callMethod("Read");
	}

	/**
	 * Сдвигает запись в наборе на указанное число позиций по записи.
	 * 
	 * @param record
	 *            - Перемещаемая запись набора.
	 * @param offset
	 *            - Количество позиций, на которое следует сдвинуть запись.
	 *            Положительное значение соответствует смещению записи в сторону
	 *            конца списка записей набора (увеличение индекса),
	 *            отрицательное - к началу.
	 * @throws JIException
	 */
	public void move(OCAccountingRegisterRecord record, int offset)
			throws JIException {
		callMethod("Move", new Object[] { ocObject2Dispatch(record),
				new JIVariant(offset) });
	}
	
	/**
	 * Сдвигает запись в наборе на указанное число позиций по индексу.
	 * 
	 * @param recordIndex
	 *            - Позиция сдвигаемой записи в наборе.
	 * @param offset
	 *            - Количество позиций, на которое следует сдвинуть запись.
	 *            Положительное значение соответствует смещению записи в сторону
	 *            конца списка записей набора (увеличение индекса),
	 *            отрицательное - к началу.
	 * @throws JIException
	 */
	public void move(int recordIndex, int offset) throws JIException {
		callMethod("Move", new Object[] { new JIVariant(recordIndex),
				new JIVariant(offset) });
	}
	
	/**
	 * Удаляет запись из набора записей по объекту записи регистра бухгалтерии.
	 * 
	 * @param record
	 *            - Удаляемая запись.
	 * @throws JIException
	 */
	public void delete(OCAccountingRegisterRecord record) throws JIException {
		callMethod("Delete", new Object[] { ocObject2Dispatch(record) });
	}

	/**
	 * Удаляет запись из набора записей по индексу записи регистра бухгалтерии.
	 * 
	 * @param index
	 *            - Позиция записи в наборе.
	 * @throws JIException
	 */
	public void delete(int index) throws JIException {
		callMethod("Delete", new Object[] { new JIVariant(index) });
	}
	
	/**
	 * Устанавливает значение свойства Активность у всех записей, входящих в
	 * набор.
	 * 
	 * @param status
	 *            - Признак активности записи. Истина - записи учитываются в
	 *            итогах регистра. Ложь - записи не учитываются в итогах
	 *            регистра.
	 * @throws JIException
	 */
	public void setActive(boolean status) throws JIException {
		callMethod("SetActive", new Object[]{new JIVariant(status)});
	}
}
