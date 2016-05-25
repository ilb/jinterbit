package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCValueTable;


/**
 * Используется для доступа к методам табличной части прикладного объекта в целом.
 * Максимальное число строк в табличной части 99999.
 * Имя табличной части объекта формируется следующим образом:
 * <Префикс полного имени объекта>ТабличнаяЧасть.<Имя прикладного объекта>.<Имя табличной части>.
 * Например: СправочникТабличнаяЧасть.Номенклатура.Состав, где "Номенклатура" - имя справочника, как оно задано в конфигураторе, "Состав" - имя табличной части справочника "Номенклатура". 
 * @author Konovalov
 *
 */

public class OCTabularSectionManager extends OCObject {
	
	public static final int READ_WRITE = 89;
	
	public static final int READ_ONLY = 90;
	
	private int accessMode = READ_WRITE;
	
	private String tabSectionName = null;
	
	public OCTabularSectionManager(OCObject object) {
		super(object);
	}

	public OCTabularSectionManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}
	
	public OCTabularSectionManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCTabularSectionManager(JIVariant aDispatch, String tabSectionName) throws JIException {
		super(aDispatch);
		this.tabSectionName = tabSectionName;
	}
	
	/**
	 * Добавляет строку в конец табличной части. 
	 * @return OCTabularSectionRow
	 * @throws JIException
	 */
	public OCTabularSectionRow add() throws JIException{
		return new OCTabularSectionRow(callMethodA("Add"));
	}
	
	/**
	 * Вставляет в табличную часть строку с указанным индексом. 
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения. 
	 * @param rowIndex
	 * @return OCTabularSectionRow
	 * @throws JIException
	 */
	public OCTabularSectionRow insert(Integer rowIndex) throws JIException{
		return new OCTabularSectionRow(callMethodA("Insert",
				new Object[] { JIVariant.makeVariant(rowIndex) })[0]);
	}
	
	/**
	 * Выгружает табличную часть в таблицу значений. 
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable upload() throws JIException{
		return new OCValueTable(callMethodA("Unload"));
	}
	
	/**
	 * Выгружает табличную часть в таблицу значений. Если указаны строки и колонки, то только они будут скопированы. Если не указаны - то табличная часть будет выгружена полностью. 
	 * @param columns  Список колонок для копирования в формате: "Колонка1, Колонка2...". Если список не задан, то будут скопированы все колонки. 
	 * @return OCValueTable
	 * @throws JIException
	 */
	public OCValueTable upload(String columns) throws JIException{
		return new OCValueTable(callMethodA("Unload", new Object[]{null, new JIVariant(columns)})[0]);
	}
	
	/**
	 * Загружает табличную часть из таблицы значений. При этом все прежние строки табличной части удаляются. При загрузке значения в колонках табличной части заполняются значениями из колонок таблицы значений с совпадающими именами. 
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения. 
	 * @param table Таблица значений, откуда загружается табличная часть. Колонки таблиц совмещаются по именам. 
	 * @throws JIException
	 */
	public void load(OCValueTable table) throws JIException{
		callMethod("Load", new Object[]{new JIVariant(ocObject2Dispatch(table))});
	}
	
	/**
	 * Индекс указанной строки табличной части. Если не найдено, то возвращается -1. 
	 * @param row строка табличной части.
	 * @return Integer индекс row
	 * @throws JIException
	 */
	public Integer indexOf(OCTabularSectionRow row) throws JIException{
		return callMethodA("IndexOf", new Object[]{row.dispatch()})[0].getObjectAsInt();
	}
	
	/**
	 * Суммирует значения всех строк в указанной колонке. Если в колонке
	 * установлен тип и он единственный, то при суммировании будет
	 * предприниматься попытка преобразования значения к типу Число. Если
	 * колонке не присвоены типы, то в процессе суммирования будут принимать
	 * участие только значения, имеющие тип Число, значения других типов будут
	 * игнорироваться. Если в колонке несколько типов и среди них есть тип
	 * Число, то в процессе суммирования будут принимать участие только
	 * значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них нет типа Число, то результатом
	 * будет значение Неопределено.
	 * 
	 * @param columnName
	 *            Имя колонки, по которой подсчитывается итог.
	 * @return Integer
	 * @throws JIException
	 */
	public Integer total(String columnName) throws JIException{
		return callMethodA("Total", new Object[]{JIVariant.makeVariant(columnName)})[0].getObjectAsInt();
	}
	
	/**
	 * Суммирует значения всех строк в указанной колонке.
	 * Если в колонке установлен тип и он единственный, то при суммировании будет предприниматься попытка преобразования значения к типу Число.
	 * Если колонке не присвоены типы, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них есть тип Число, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них нет типа Число, то результатом будет значение Неопределено. 
	 * @param columnIndex Индекс по которому подсчитывается итог. 
	 * @return Integer
	 * @throws JIException
	 */
	public Integer total(Integer columnIndex) throws JIException{
		return callMethodA("Total", new Object[]{new JIVariant(columnIndex)})[0].getObjectAsInt();
	}
	
	/**
	 * Удаляет все строки табличной части. 
	 * Примечание:
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения.
	 * @throws JIException
	 */
	public void clear() throws JIException{
		callMethod("Clear");
	}
	
	/**
	 * Получает значение по индексу. Применяется в случаях, когда использование оператора [] невозможно.  (PS В java этих случаев чуть больше чем всегда)
	 * @param index
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionRow get(Integer index) throws JIException{
		return new OCTabularSectionRow(callMethodA("Get",
				new Object[] { JIVariant.makeVariant(index) })[0]);
	}
	
	/**
	 * Сдвигает указанную строку табличной части на указанное смещение. 
	 * Примечание:
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения. 
	 * @param rowIndex  Число, Строка табличной части. Индекс строки, которую нужно сдвинуть
	 * @param offset Количество строк, на которое необходимо переместить строку. Положительное значение означает, что строка будет передвинута ближе к концу табличной части (вниз), отрицательное - ближе к началу (вверх). 
	 * @throws JIException
	 */
	public void move(Integer rowIndex, Integer offset) throws JIException{
		callMethod("Move", new Object[]{JIVariant.makeVariant(rowIndex), JIVariant.makeVariant(offset)});
	}
	
	/**
	 * Сдвигает указанную строку табличной части на указанное смещение. 
	 * Примечание:
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения. 
	 * @param row  Строка табличной части (OCTabularSectionRow). Сама сдвигаемая строка.
	 * @param offset Количество строк, на которое необходимо переместить строку. Положительное значение означает, что строка будет передвинута ближе к концу табличной части (вниз), отрицательное - ближе к началу (вверх). 
	 * @throws JIException
	 */
	public void move(OCTabularSectionRow row, Integer offset) throws JIException{
		callMethod("Move", new Object[]{new JIVariant(row.dispatch()), JIVariant.makeVariant(offset)});
	}
	
	/**
	 * Осуществляет свертку табличной части по указанным колонкам группировки. Строки, у которых совпадают значения в колонках, указанных в первом параметре, сворачиваются в одну строку. Значения этих строк, хранящиеся в колонках, указанных во втором параметре, накапливаются. 
	 * Важно! Оба списка колонок должны покрывать всю табличную часть. Списки колонок не должны пересекаться.
	 * Примечание:
	 * Если в колонке установлен тип и он единственный, то при суммировании будет предприниматься попытка преобразования значения к типу Число.
	 * Если колонке не присвоены типы, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них есть тип Число то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них нет типа Число, то результат суммирования будет 0, который будет присвоен в соответствующую колонку, где будет преобразован к значению по умолчанию для типа, установленного в колонке.
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения.  
	 * @param columnNamesGroup  Имена колонок, разделенные запятыми, по которым необходимо группировать строки табличного поля. 
	 * @param columnNamesSum Имена колонок, разделенные запятыми, по которым необходимо суммировать значения в строках табличного поля. 
	 * @throws JIException
	 */
	public void groupBy(String columnNamesGroup, String columnNamesSum) throws JIException{
		callMethod("GroupBy",
				new Object[] {
						new JIVariant(columnNamesGroup),
						(columnNamesSum != null ? new JIVariant(columnNamesSum)
								: null) });
	}
	
	/**
	 * Сортирует табличную часть в соответствии с указанными правилами сортировки. 
	 * Примечание:
	 * Использование метода допустимо только в том случае, если табличная часть получена из свойства объекта. Если табличная часть получена из свойства ссылки (или выборки), то использование этого метода будет вызывать ошибку выполнения. 
	 * Пример:
	 * ТаблицаЦен.Сортировать("Цена Убыв, Товар Возр");
	 * @param columnName
	 * @throws JIException
	 */
	public void sort(String columnName) throws JIException{
		callMethod("Sort", new Object[]{JIVariant.makeVariant(columnName)});
	}
	
	/**
	 * Удаляет строку с указанным индексом из табличной части. 
	 * @param rowIndex Индекс удаляемой строки в табличной части
	 * @throws JIException
	 */
	public void delete(Integer rowIndex) throws JIException{
		callMethod("Delete", new Object[]{JIVariant.makeVariant(rowIndex)});
	}
	
	/**
	 * Удаляет строку с указанным индексом из табличной части. 
	 * @param row удаляемая строка
	 * @throws JIException
	 */
	public void delete(OCTabularSectionRow row) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(row.dispatch())});
	}
	
	/**
	 * Получает количество строк табличной части
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
}
