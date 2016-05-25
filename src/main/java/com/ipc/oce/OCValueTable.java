/**
 * 
 */
package com.ipc.oce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.objects.OCCollectionIndexes;

/**
 * Таблица значений предназначена для хранения значений в табличном виде. Все
 * основные операции с таблицей производятся именно через этот объект. Он
 * позволяет манипулировать строками таблицы значений и предоставляет доступ к
 * коллекции колонок. Колонки могут быть различных типов (в том числе
 * множественных).
 * 
 * @author Konovalov
 * 
 */
public class OCValueTable extends OCObject implements Iterable<OCValueTableRow>{

	private int size = -1;
	/**
	 * @param object
	 */
	public OCValueTable(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTable(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTable(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит коллекцию колонок таблицы значений.
	 * @return
	 * @throws JIException 
	 */
	public OCValueTableColumnCollection getColumns() throws JIException {
		return new OCValueTableColumnCollection(get("Columns"));
	}
	
	/**
	 * Получить список полей талицы.
	 * @return
	 * @throws JIException
	 */
	public List<String> listColumns() throws JIException {
		List<String> res = new ArrayList<String>();
		OCValueTableColumnCollection cols = getColumns();
		int sz = cols.size();
		for (int z = 0; z < sz; z++) {
			res.add(cols.getTableColumn(z).getName());
		}
		return res;
	}
	
	/**
	 * Содержит коллекцию индексов таблицы значений.
	 * @return OCCollectionIndexes
	 * @throws JIException
	 */
	public OCCollectionIndexes getIndexes() throws JIException {
		return new OCCollectionIndexes(get("Indexes"));
	}
	
	/**
	 * Вставляет строку на позицию в таблице значений, соответствующую указанному индексу.
	 * @param index Индекс вставляемой строки
	 * @return Вставленная строка
	 * @throws JIException
	 */
	public OCValueTableRow insert(int index) throws JIException {
		return new OCValueTableRow(callMethodA("Insert", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения, содержащиеся в колонке таблицы значений. 
	 * @param index - Колонка, значения которой необходимо выгрузить. В качестве значения параметра выступает индекс колонки 
	 * @return
	 * @throws JIException
	 */
	public OCArray unloadColumn(int index) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new JIVariant(index))[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения, содержащиеся в колонке таблицы значений. 
	 * @param columnName - имя колонки.
	 * @return
	 * @throws JIException
	 */
	public OCArray unloadColumn(String columnName) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new JIVariant(columnName))[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения, содержащиеся в колонке таблицы значений. 
	 * @param column - объект представляющий колонку
	 * @return
	 * @throws JIException
	 */
	public OCArray unloadColumn(OCValueTableColumn column) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new JIVariant(ocObject2Dispatch(column)))[0]);
	}
	
	/**
	 * Добавляет строку в конец таблицы значений.
	 * @return
	 * @throws JIException
	 */
	public OCValueTableRow add() throws JIException {
		return new OCValueTableRow(callMethodA("Add"));
	}
	
	/**
	 * Загружает колонку таблицы значений из массива значений. 
	 * @param index -  Колонка, в которую будут загружены значения из массива. Индекс.
	 * @param array - Массив значений, который выступает в качестве источника данных для колонки. 
	 * @throws JIException
	 */
	public void loadColumn(int index, OCArray array) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(index),
				new JIVariant(ocObject2Dispatch(array))
		});
	}
	
	/**
	 * Загружает колонку таблицы значений из массива значений. 
	 * @param columnName - имя колонки
	 * @param array - Массив значений, который выступает в качестве источника данных для колонки. 
	 * @throws JIException
	 */
	public void loadColumn(String columnName, OCArray array) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(columnName),
				new JIVariant(ocObject2Dispatch(array))
		});
	}
	
	/**
	 * Загружает колонку таблицы значений из массива значений. 
	 * @param column - объект колонки
	 * @param array - Массив значений, который выступает в качестве источника данных для колонки. 
	 * @throws JIException
	 */
	public void loadColumn(OCValueTableColumn column, OCArray array) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(ocObject2Dispatch(column)),
				new JIVariant(ocObject2Dispatch(array))
		});
	}
	
	/**
	 * Получает индекс строки в коллекции строк таблицы значений.
	 * @param row Строка таблицы значений, для которой нужно определить индекс 
	 * @return Индекс указанной строки в коллекции. Если не найдено, то возвращается -1.
	 * @throws JIException
	 */
	public Integer indexOf(OCValueTableRow row) throws JIException {
		return callMethodA("IndexOf", new Object[]{new JIVariant(row.dispatch())})[0].getObjectAsInt();
	}
	
	/**
	 * Суммирует значения всех строк в указанной колонке.
	 * Если в колонке установлен тип и он единственный, то при суммировании будет предприниматься попытка преобразования значения к типу Число.
	 * Если колонке не присвоены типы, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них есть тип Число, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них нет типа Число, то результатом будет значение Неопределено.
	 * @param columnName  Имя колонки, по которой необходимо посчитать итог
	 * @return Сумма значений по всем строкам указанной колонки
	 * @throws JIException - ошибка DCOM
	 */
	public Number total(String columnName) throws JIException {
		OCVariant var = new OCVariant(callMethodA("Total", new Object[]{new JIVariant(columnName)})[0]);
		return (Number) var.value();
	}
	
	/**
	 * Получает количество строк таблицы значений.
	 * @return Integer
	 * @throws JIException - ошибка DCOM
	 */
	public int size() throws JIException {
		if (size == -1) {
			size = callMethodA("Count").getObjectAsInt();
		}
		return size;
	}
	
	/**
	 * Удаляет все строки таблицы значений. Структура колонок остается
	 * неизменной
	 * 
	 * @throws JIException
	 *             - ошибка DCOM
	 * 
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}

	/**
	 * Получает значение по индексу.
	 * 
	 * @param index
	 *            - Индекс строки
	 * @return Применяется в случаях, когда использование оператора []
	 *         невозможно
	 * @throws JIException
	 */
	public OCValueTableRow get(int index) throws JIException {
		return new OCValueTableRow(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	public Iterator<OCValueTableRow> iterator() {
		Iterator<OCValueTableRow> iter = new Iterator<OCValueTableRow>() {
			int currentRow = 0;
			public void remove() {
				try {
					delete(currentRow);
				} catch (JIException e) {
					e.printStackTrace();
				}
			}
			
			public OCValueTableRow next() {
				try {
					return get(currentRow++);
				} catch (JIException e) {
					e.printStackTrace();
					return null;
				}
			}
			
			public boolean hasNext() {
				try {
					return (currentRow < size());
				} catch (JIException e) {
					e.printStackTrace();
					return false;
				}
			}
		};
		return iter;
	}
	
	/**
	 * Осуществляет поиск значения в указанных колонках таблицы значений.
	 * 
	 * @param value
	 *            - Искомое значение.
	 * @param columnFilter
	 *            - Список имен колонок, разделенных запятыми, по которым
	 *            производится поиск. Если параметр не указан, поиск
	 *            осуществляется по всей таблице значений. Значение по
	 *            умолчанию: Пустая строка
	 * @return СтрокаТаблицыЗначений; Неопределено. Строка, в которой содержится
	 *         искомое значение. Если значение не найдено, то возвращается
	 *         значение Неопределено.
	 * @throws JIException
	 */
	public OCValueTableRow find(OCVariant value, String columnFilter) throws JIException {
		JIVariant var = callMethodA("Find", new Object[]{
				ocVariant2JI(value),
				columnFilter != null ? new JIVariant(columnFilter) : null
		})[0];
		
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCValueTableRow(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Осуществляет свертку таблицы значений по указанным колонкам группировки.
	 * Строки, у которых совпадают значения в колонках, указанных в первом
	 * параметре, сворачиваются в одну строку. Значения этих строк, хранящиеся в
	 * колонках, указанных во втором параметре, накапливаются. Важно! Списки
	 * колонок не должны пересекаться. Колонки, не вошедшие ни в один из списков
	 * колонок, после выполнения метода удаляются из таблицы значений.
	 * Примечание: Если в колонке установлен тип и он единственный, то при
	 * суммировании будет предприниматься попытка преобразования значения к типу
	 * Число. Если колонке не присвоены типы, то в процессе суммирования будут
	 * принимать участие только значения, имеющие тип Число, значения других
	 * типов будут игнорироваться. Если в колонке несколько типов и среди них
	 * есть тип Число, то в процессе суммирования будут принимать участие только
	 * значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них нет типа Число, то результат
	 * суммирования будет 0, который будет присвоен в соответствующую колонку,
	 * где будет преобразован к значению по умолчанию для типа, установленного в
	 * колонке.
	 * 
	 * @param groupingColumns
	 *            Имена колонок, разделенные запятыми, по которым необходимо
	 *            группировать строки таблицы значений.
	 * @throws JIException - ошибка DCOM
	 */
	public void groupBy(String groupingColumns) throws JIException {
		callMethod("GroupBy", new Object[]{new JIVariant(groupingColumns)});
	}
	
	/**
	 * Осуществляет свертку таблицы значений по указанным колонкам группировки. Строки, у которых совпадают значения в колонках, указанных в первом параметре, сворачиваются в одну строку. Значения этих строк, хранящиеся в колонках, указанных во втором параметре, накапливаются.
	 * Важно! Списки колонок не должны пересекаться. Колонки, не вошедшие ни в один из списков колонок, после выполнения метода удаляются из таблицы значений. 
	 * Примечание:
	 * Если в колонке установлен тип и он единственный, то при суммировании будет предприниматься попытка преобразования значения к типу Число.
	 * Если колонке не присвоены типы, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них есть тип Число, то в процессе суммирования будут принимать участие только значения, имеющие тип Число, значения других типов будут игнорироваться.
	 * Если в колонке несколько типов и среди них нет типа Число, то результат суммирования будет 0, который будет присвоен в соответствующую колонку, где будет преобразован к значению по умолчанию для типа, установленного в колонке. 
	 * @param groupingColumns  Имена колонок, разделенные запятыми, по которым необходимо группировать строки таблицы значений. 
	 * @param totalColumns Имена колонок, разделенные запятыми, по которым необходимо суммировать значения в строках таблицы значений. 
	 * @throws JIException
	 */
	public void groupBy(String groupingColumns, String totalColumns) throws JIException {
		callMethod("GroupBy", new Object[]{new JIVariant(groupingColumns), new JIVariant(totalColumns)});
	}
	
	/**
	 * Сдвигает строку на указанное количество позиций.
	 * @param index Индекс строки, которую нужно переместить, или сама строка
	 * @param offset Количество строк, на которое необходимо переместить строку. Положительное значение означает, что строка будет передвинута ближе к концу таблицы значений (вниз), отрицательное - ближе к началу (вверх)
	 * @throws JIException
	 */
	public void move(int index, int offset) throws JIException {
		callMethod("Move", new Object[]{new JIVariant(index), new JIVariant(offset)});
	}
	
	/**
	 * Создает копию исходной таблицы значений. 
	 * @return
	 * @throws JIException
	 */
	public OCValueTable copy() throws JIException {
		return new OCValueTable(callMethodA("Copy"));
	}
	
	/**
	 * Создает таблицу значений с заданным списком колонок.
	 * @param columnsForCopy Список колонок для копирования в формате: "Колонка1, Колонка2...". Если список не задан, то будут скопированы все колонки
	 * @return
	 * @throws JIException
	 */
	public OCValueTable copyColumns(String columnsForCopy) throws JIException {
		return new OCValueTable(callMethodA("CopyColumns", new Object[]{new JIVariant(columnsForCopy)})[0]);
	}
	
	/**
	 * Сортирует таблицу значений в соответствии с указанными правилами сортировки. 
	 * @param sortString Список имен колонок, разделенных запятыми, по которым производится сортировка таблицы. После каждого имени колонки через пробел может быть указано направление сортировки. Направление определяется: "Убыв" ("Desc") - упорядочивать по убыванию; "Возр" ("Asc") - упорядочивать по возрастанию. По умолчанию сортировка производится по возрастанию. Порядок указания имен колонок таблицы определяет порядок сортировки. Это означает, что сначала таблица сортируется по колонке, указанной первой. Затем группы строк с одинаковым значением в этой колонке сортируются по колонке, которая указана второй, и так далее. 
	 * @throws JIException
	 */
	public void sort(String sortString) throws JIException {
		callMethod("Sort", new Object[]{new JIVariant(sortString)});
	}
	
	/**
	 * Удаляет строку таблицы значений по индексу.
	 * @param index Индекс строки, которую необходимо удалить
	 * @throws JIException
	 */
	public void delete(int index) throws JIException {
		callMethod("Delete", new Object[]{new JIVariant(index)});
	}
	
	/**
	 * Удаляет строку таблицы значений по объекту.
	 * @param row Удаляемая строка таблицы значений
	 * @throws JIException
	 */
	public void delete(OCValueTableRow row) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(row.dispatch())});
	}
}
