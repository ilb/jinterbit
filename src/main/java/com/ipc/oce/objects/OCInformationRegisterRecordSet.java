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
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCInformationRegisterMetadataObject;

/**
 * Предназначен для операций с наборами записей регистра сведений в памяти.
 * Представляет собой коллекцию записей регистра сведений. Позволяет выполнять
 * считывание записей по определенному условию отбора из базы данных, добавлять,
 * удалять и модифицировать записи в наборе. Также может быть выполнена запись в
 * базе данных по определенному условию. При записи может выполняться замещение
 * всех имеющихся в базе данных записей по данному условию на записи,
 * содержащиеся в наборе. Максимальное число записей в наборе 999999999.
 * XML-сериализация. Поддержка отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data/enterprise/current-config}. Имя типа XDTO:
 * InformationRegisterRecordSet.<Имя регистра сведений>.
 * 
 * @author Konovalov
 * 
 */
public class OCInformationRegisterRecordSet extends OCObject implements Iterable<OCInformationRegisterRecord>{

	/**
	 * @param object
	 */
	public OCInformationRegisterRecordSet(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCInformationRegisterRecordSet(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCInformationRegisterRecordSet(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	/**
	 * Итератор коллекции по OCInformationRegisterRecord.
	 */
	public Iterator<OCInformationRegisterRecord> iterator() {
		EnumVARIANT<OCInformationRegisterRecord> enumV = new EnumVARIANT<OCInformationRegisterRecord>(this) {
			
			@Override
			protected OCInformationRegisterRecord castToGeneric(JIVariant variant) {
				try {
					return new OCInformationRegisterRecord(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	/**
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые
	 * значения, связанные с объектом, на время выполнения некоторых операций,
	 * без изменения объекта. Например, при обработке событий в подписке на
	 * события. XML-сериализация.
	 * 
	 * @return OCStructure
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException {
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
	public OCDataExchangeParameters getDataExchange() throws JIException {
		return new OCDataExchangeParameters(get("DataExchange"));
	}

	/**
	 * Содержит объект Отбор, по которому осуществляется текущая фильтрация
	 * записей при считывании или записи набора. Список свойств объекта зависит
	 * от свойств регистра: для регистров сведений, для которых в конфигураторе
	 * установлен режим записи "Подчинение регистратору", отбор возможен только
	 * по регистратору, для периодических регистров сведений отбор возможен по
	 * периоду и значениям измерений, для непериодических - только по значению
	 * измерений. Важно! Отбор может устанавливаться только на равенство.
	 * 
	 * @return OCFilter
	 * @throws JIException
	 */
	public OCFilter getFilter() throws JIException {
		return new OCFilter(get("Filter"));
	}
	
	/**
	 * Ссылка на сам набор записей. Предназначено для получения набора в модуле
	 * набора записей.
	 * 
	 * @return OCInformationRegisterRecordSet
	 * @throws JIException
	 */
	public OCInformationRegisterRecordSet thisObject() throws JIException {
		return new OCInformationRegisterRecordSet(get("ThisObject"));
	}
	
	/**
	 * Вставляет новую запись в набор на указанную позицию. Доступен только для
	 * набора записей регистра сведений, в котором записи подчинены
	 * регистратору.
	 * 
	 * @param index
	 *            Индекс позиции, на которую надо вставить новую запись.
	 * @return OCInformationRegisterRecord
	 * @throws JIException
	 */
	public OCInformationRegisterRecord insert(int index) throws JIException {
		return new OCInformationRegisterRecord(callMethodA("Insert", new JIVariant(index))[0]);
	}
	
	/**
	 * Определяет, считан ли набор записей.
	 * 
	 * @return Истина - набор не изменялся; Ложь - изменялся, в частности, если
	 *         набор не считывался и не записывался, а также, если записывался с
	 *         добавлением записей.
	 * @throws JIException
	 */
	public boolean isSelected() throws JIException {
		return callMethodA("Selected").getObjectAsBoolean();
	}
	
	/**
	 * Создает таблицу значений и копирует в нее записи набора. Структура
	 * полученной таблицы совпадает со структурой набора записей.
	 * 
	 * @param rows
	 *            Массив строк для выгрузки. Если не указан, выгружаются все
	 *            строки набора записей.
	 * @param columns
	 *            Список колонок для выгрузки в формате:
	 *            "Колонка1, Колонка2...". Если список не задан, то будут
	 *            выгружены все колонки.
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable unload(OCArray rows, String columns) throws JIException {
		JIVariant var = callMethodA("Unload", new Object[]{
				rows != null ? new JIVariant(ocObject2Dispatch(rows)) : null,
				columns != null ? new JIVariant(columns) : null
		})[0];
		
		return var.getType() != JIVariant.VT_EMPTY ? new OCValueTable(var) : null;
	}
	
	/**
	 * Создает таблицу значений с заданным списком колонок набора записей.
	 * XML-сериализация. Примечание: Выгружается только структура набора
	 * записей, данные не выгружаются.
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
	 * Создает массив и копирует в него значения заданного поля записей набора.
	 * Значения записей выгружаются в порядке индексов.
	 * 
	 * @param column
	 *            Имя поля записей набора, значения из которого будут выгружены
	 *            в массив.
	 * @return OCArray
	 * @throws JIException
	 */
	public OCArray unloadColumn(String column) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new Object[]{
				column != null ? new JIVariant(column) : null
		})[0]);
	}
	
	/**
	 * Добавляет новую запись в набор. 
	 * @return OCInformationRegisterRecord
	 * @throws JIException
	 */
	public OCInformationRegisterRecord add() throws JIException {
		return new OCInformationRegisterRecord(callMethodA("Add"));
	}
	
	/**
	 * Загружает набор записей значениями из переданной таблицы значений. При
	 * этом все прежние записи набора удаляются. Заполняются значения доступных
	 * для записи свойств записей регистра сведений, имена которых совпали c
	 * именами колонок таблицы значений.
	 * 
	 * @param table
	 *            Таблица значений, по которой производится заполнение набора
	 *            записей.
	 * @throws JIException
	 */
	public void load(OCValueTable table) throws JIException {
		callMethod("Load", new JIVariant(ocObject2Dispatch(table)));
	}
	
	/**
	 * Загружает значения из массива в заданное поле набора записей. Примечание:
	 * Значения из массива загружаются в колонку в порядке индексов.
	 * 
	 * @param array
	 *            Массив, содержащий значения для заполнения колонки набора.
	 * @param fieldName
	 *            Имя поля записей набора, в которое будут загружаться значения
	 *            из массива.
	 * @throws JIException
	 */
	public void loadColumn(OCArray array, String fieldName) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(ocObject2Dispatch(array)),
				new JIVariant(fieldName)
		});
	}
	
	/**
	 * Записывает набор записей в базу данных. В зависимости от переданного
	 * параметра, может быть выполнено добавление записей или их замещение.
	 * Примечание: Для регистров сведений, подчиненных регистратору, при вызове
	 * с параметром <Замещать> равным Ложь после записи в информационную базу
	 * набор записей очищается (удаляются записи из набора).
	 * 
	 * @param replace
	 *            Определяет режим замещения существующей записи в соответствии
	 *            с текущими установками отбора. Истина - перед записью
	 *            существующие записи будут удалены. Ложь - записи будут
	 *            дописаны к уже существующим в информационной базе записям
	 *            Значение по умолчанию: Истина
	 * @throws JIException
	 */
	public void write(boolean replace) throws JIException {
		callMethod("Write", new JIVariant(replace));
	}
	
	/**
	 * @see #write(boolean) with true
	 * @throws JIException
	 */
	public void write() throws JIException {
		write(true);
	}
	
	/**
	 * Получает индекс записи в наборе.
	 * 
	 * @param record
	 *            Запись набора, для которой необходимо получить индекс.
	 * @return Индекс указанной записи в наборе. Если не найдено, то
	 *         возвращается -1.
	 * @throws JIException
	 */
	public int indexOf(OCInformationRegisterRecord record) throws JIException {
		return callMethodA("IndexOf", new JIVariant(ocObject2Dispatch(record)))[0].getObjectAsInt();
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
	 *            Имя поля записей набора, по которому необходимо подсчитать
	 *            итог.
	 * @return Integer, null. Числовое значения результата вычисления.
	 * @throws JIException
	 */
	public Number total(String fieldName) throws JIException {
		JIVariant var = callMethodA("Total", new JIVariant(fieldName))[0];
		return var.getFlag() != JIVariant.VT_EMPTY ? (Number)(new OCVariant(var)).value() : null;
	}
	
	/**
	 * Получает количество записей в наборе.
	 * @return количество записей в наборе
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных регистра сведений.
	 * Другой путь получения того же значения - через свойство глобального
	 * контекста Метаданные. Например: Метаданные.РегистрыСведений.КурсыВалют.
	 * 
	 * @return OCInformationRegisterMetadataObject
	 * @throws JIException
	 */
	public OCInformationRegisterMetadataObject getMetadata() throws JIException {
		return new OCInformationRegisterMetadataObject(callMethodA("Metadata"));
	}
	
	/**
	 * Определяет, были ли изменены записи в наборе после последнего считывания
	 * или записи. Примечание: Метод не позволяет определить, были ли изменены
	 * соответствующие записи другими пользователями.
	 * 
	 * @return Истина - записи изменены; Ложь - в противном случае.
	 * @throws JIException
	 */
	public boolean isModified() throws JIException {
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Удаляет все записи из набора. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index Индекс записи. 
	 * @return OCInformationRegisterRecord
	 * @throws JIException
	 */
	public OCInformationRegisterRecord getRecord(int index) throws JIException {
		return new OCInformationRegisterRecord(callMethodA("Get", new JIVariant(index))[0]);
	}
	
	/**
	 * Считывает записи из базы данных по установленному отбору. 
	 * @throws JIException
	 */
	public void read() throws JIException {
		callMethod("Read");
	}
	
	/**
	 * Сдвигает запись в наборе на указанное число позиций. Примечание: Доступен
	 * только для набора записей регистра сведений, в котором записи подчинены
	 * регистратору.
	 * 
	 * @param record
	 *            сама сдвигаемая запись
	 * @param offset
	 *            Количество позиций, на которое следует сдвинуть запись.
	 *            Положительное значение соответствует смещению записи в сторону
	 *            конца списка записей набора (увеличение индекса),
	 *            отрицательное - к началу.
	 * @throws JIException
	 */
	public void move(OCInformationRegisterRecord record, int offset) throws JIException {
		callMethod("Move", new Object[]{
				new JIVariant(ocObject2Dispatch(record)),
				new JIVariant(offset)
		});
	}
	
	/**
	 * Сдвигает запись в наборе на указанное число позиций. Примечание: Доступен
	 * только для набора записей регистра сведений, в котором записи подчинены
	 * регистратору.
	 * 
	 * @param recordIndex
	 *            Индекс сдвигаемой записи
	 * @param offset
	 *            Количество позиций, на которое следует сдвинуть запись.
	 *            Положительное значение соответствует смещению записи в сторону
	 *            конца списка записей набора (увеличение индекса),
	 *            отрицательное - к началу.
	 * @throws JIException
	 */
	public void move(int recordIndex, int offset) throws JIException {
		callMethod("Move", new Object[]{
				new JIVariant(recordIndex),
				new JIVariant(offset)
		});
	}
	
	/**
	 * Удаляет запись из набора записей регистра сведений. 
	 * @param record сама удаляемая запись
	 * @throws JIException
	 */
	public void delete(OCInformationRegisterRecord record) throws JIException {
		callMethod("Delete", new Object[]{
				new JIVariant(ocObject2Dispatch(record))
		});
	}
	
	/**
	 * Удаляет запись из набора записей регистра сведений. 
	 * @param recordIndex  Индекс удаляемой записи 
	 * @throws JIException
	 */
	public void delete(int recordIndex) throws JIException {
		callMethod("Delete", new Object[]{
				new JIVariant(recordIndex)
		});
	}
	
	/**
	 * Устанавливает значение свойства Активность для всех записей набора.
	 * 
	 * @param active
	 *            Признак активности записей. Истина - записи активны. Ложь -
	 *            записи не активны.
	 * @throws JIException
	 */
	public void setActive(boolean active) throws JIException {
		callMethod("SetActive", new JIVariant(active));
	}
}
