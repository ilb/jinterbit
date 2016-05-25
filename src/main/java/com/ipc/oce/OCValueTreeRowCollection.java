/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Представляет собой коллекцию строк, подчиненных какой-либо строке дерева
 * значений.
 * 
 * @author Konovalov
 * 
 */
public class OCValueTreeRowCollection extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTreeRowCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTreeRowCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTreeRowCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Строка, которой принадлежит данная коллекция строк (родительская строка)
	 * Если данная коллекция строк является корневой коллекцией дерева, то имеет
	 * значение Неопределено.
	 * 
	 * @return OCValueTreeRow
	 * @throws JIException
	 */
	public OCValueTreeRow getParent() throws JIException {
		JIVariant var = get("Parent");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCValueTreeRow(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Вставляет строку с указанным индексом в коллекцию строк данного уровня дерева значений. 
	 * @param index - индекс
	 * @return OCValueTreeRow
	 * @throws JIException
	 */
	public OCValueTreeRow insert(int index) throws JIException {
		return new OCValueTreeRow(callMethodA("Insert", new JIVariant(index))[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения, содержащиеся в колонке
	 * коллекции строк дерева значений.
	 * 
	 * @param index
	 *            - Колонка, из которой нужно выгрузить значения. В качестве
	 *            значения параметра может быть передан индекс колонки, имя
	 *            колонки, либо колонка дерева значений.
	 * @return OCArray
	 * @throws JIException
	 */
	public OCArray unloadColumn(int index) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new JIVariant(index))[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения, содержащиеся в колонке
	 * коллекции строк дерева значений.
	 * 
	 * @param columnName
	 *            - Колонка, из которой нужно выгрузить значения. В качестве
	 *            значения параметра может быть передан индекс колонки, имя
	 *            колонки, либо колонка дерева значений.
	 * @return OCArray
	 * @throws JIException
	 */
	public OCArray unloadColumn(String columnName) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new JIVariant(columnName))[0]);
	}
	
	/**
	 * Создает массив и копирует в него значения, содержащиеся в колонке
	 * коллекции строк дерева значений.
	 * 
	 * @param column
	 *            - Колонка, из которой нужно выгрузить значения. В качестве
	 *            значения параметра может быть передан индекс колонки, имя
	 *            колонки, либо колонка дерева значений.
	 * @return OCArray
	 * @throws JIException
	 */
	public OCArray unloadColumn(OCValueTreeColumn column) throws JIException {
		return new OCArray(callMethodA("UnloadColumn", new JIVariant(ocObject2Dispatch(column)))[0]);
	}
	
	/**
	 * Добавляет строку в конец коллекции строк данного уровня дерева значений. 
	 * @return OCValueTreeRow
	 * @throws JIException
	 */
	public OCValueTreeRow add() throws JIException {
		return new OCValueTreeRow(callMethodA("Add"));
	}
	
	/**
	 * Загружает значения из массива в заданную колонку коллекции строк дерева
	 * значений.
	 * 
	 * @param index
	 *            - Колонка коллекции, в которую будут загружены значения из
	 *            массива. В качестве значения параметра может быть передан
	 *            индекс колонки, имя колонки, либо колонка дерева значений.
	 * @param array
	 *            - Массив значений для загрузки в колонку.
	 * @throws JIException
	 */
	public void loadColumn(int index, OCArray array) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(index),
				new JIVariant(ocObject2Dispatch(array))
		});
	}
	
	/**
	 * Загружает значения из массива в заданную колонку коллекции строк дерева
	 * значений.
	 * 
	 * @param column
	 *            - Колонка коллекции, в которую будут загружены значения из
	 *            массива. В качестве значения параметра может быть передан
	 *            индекс колонки, имя колонки, либо колонка дерева значений.
	 * @param array
	 *            - Массив значений для загрузки в колонку.
	 * @throws JIException
	 */
	public void loadColumn(String column, OCArray array) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(column),
				new JIVariant(ocObject2Dispatch(array))
		});
	}
	
	/**
	 * Загружает значения из массива в заданную колонку коллекции строк дерева
	 * значений.
	 * 
	 * @param column
	 *            - Колонка коллекции, в которую будут загружены значения из
	 *            массива. В качестве значения параметра может быть передан
	 *            индекс колонки, имя колонки, либо колонка дерева значений.
	 * @param array
	 *            - Массив значений для загрузки в колонку.
	 * @throws JIException
	 */
	public void loadColumn(OCValueTreeColumn column, OCArray array) throws JIException {
		callMethod("LoadColumn", new Object[]{
				new JIVariant(ocObject2Dispatch(column)),
				new JIVariant(ocObject2Dispatch(array))
		});
	}
	
	/**
	 * Получает индекс строки в коллекции строк дерева значений. 
	 * @param row - Строка, для которой необходимо получить индекс 
	 * @return - Индекс указанной строки в коллекции. Если не найдено, то возвращается -1. 
	 * @throws JIException
	 */
	public int indexOf(OCValueTreeRow row) throws JIException {
		return callMethodA("IndexOf", new JIVariant(ocObject2Dispatch(row)))[0].getObjectAsInt();
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
	 * @param index
	 *            - Колонка, по которой подсчитывается итог. В качестве значения
	 *            параметра может быть передан индекс колонки, имя колонки, либо
	 *            колонка дерева значений.
	 * @param includeSub
	 *            - Определяет, будут ли включены в сумму итоги по подчиненным
	 *            строкам (если таковые имеются). Истина - итоги будут включены.
	 * @return
	 * @throws JIException
	 */
	public int total(int index, boolean includeSub) throws JIException {
		return callMethodA("Total", new Object[]{
				new JIVariant(index),
				new JIVariant(includeSub)
		})[0].getObjectAsInt();
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
	 * @param index
	 *            - Колонка, по которой подсчитывается итог. В качестве значения
	 *            параметра может быть передан индекс колонки, имя колонки, либо
	 *            колонка дерева значений.
	 * @return
	 * @throws JIException
	 */
	public int total(int index) throws JIException {
		return total(index, false);
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
	 * @param column
	 *            - Колонка, по которой подсчитывается итог. В качестве значения
	 *            параметра может быть передан индекс колонки, имя колонки, либо
	 *            колонка дерева значений.
	 * @param includeSub
	 *            - Определяет, будут ли включены в сумму итоги по подчиненным
	 *            строкам (если таковые имеются). Истина - итоги будут включены.
	 * 
	 * @return
	 * @throws JIException
	 */
	public int total(String column, boolean includeSub) throws JIException {
		return callMethodA("Total", new Object[]{
				new JIVariant(column),
				new JIVariant(includeSub)
		})[0].getObjectAsInt();
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
	 * @param column
	 *            - Колонка, по которой подсчитывается итог. В качестве значения
	 *            параметра может быть передан индекс колонки, имя колонки, либо
	 *            колонка дерева значений.
	 * @return
	 * @throws JIException
	 */
	public int total(String column) throws JIException {
		return total(column, false);
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
	 * @param column
	 *            - Колонка, по которой подсчитывается итог. В качестве значения
	 *            параметра может быть передан индекс колонки, имя колонки, либо
	 *            колонка дерева значений.
	 * @param includeSub
	 *            - Определяет, будут ли включены в сумму итоги по подчиненным
	 *            строкам (если таковые имеются). Истина - итоги будут включены.
	 * 
	 * @return
	 * @throws JIException
	 */
	public int total(OCValueTreeColumn column, boolean includeSub) throws JIException {
		return callMethodA("Total", new Object[]{
				new JIVariant(ocObject2Dispatch(column)),
				new JIVariant(includeSub)
		})[0].getObjectAsInt();
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
	 * @param column
	 *            - Колонка, по которой подсчитывается итог. В качестве значения
	 *            параметра может быть передан индекс колонки, имя колонки, либо
	 *            колонка дерева значений.
	 * @return
	 * @throws JIException
	 */
	public int total(OCValueTreeColumn column) throws JIException {
		return total(column, false);
	}
	
	/**
	 * Получает количество строк данного уровня дерева значений. 
	 * @return int
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Осуществляет поиск значения в дереве в указанных колонках коллекции строк
	 * дерева значений. Метод эффективно использовать для поиска уникальных
	 * значений.
	 * 
	 * @param value
	 *            - Произвольный. Искомое значение.
	 * @param columnList
	 *            - Список имен колонок, в которых будет осуществляться поиск,
	 *            разделенных запятыми. Если параметр не указан, поиск
	 *            осуществляется по всем колонкам дерева.
	 * @param includeSunCollections
	 *            - Определяет, будут ли участвовать в поиске строки подчиненных
	 *            коллекций (если таковые имеются). Истина - строки подчиненных
	 *            коллекций участвуют в поиске.
	 * 
	 * @return Строка, которая содержит искомое значение. Если значение не
	 *         найдено, то возвращается значение Неопределено.
	 * @throws JIException
	 */
	public OCValueTreeRow find(OCVariant value, String columnList, boolean includeSunCollections) throws JIException {
		JIVariant var = callMethodA("Find", new Object[]{
				ocVariant2JI(value),
				columnList != null ? new JIVariant(columnList) : null,
				new JIVariant(includeSunCollections)
		})[0];
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCValueTreeRow(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Осуществляет поиск значения в дереве в указанных колонках коллекции строк
	 * дерева значений. Метод эффективно использовать для поиска уникальных
	 * значений.
	 * 
	 * @param value
	 *            - Произвольный. Искомое значение.
	 * @param columnList
	 *            - Список имен колонок, в которых будет осуществляться поиск,
	 *            разделенных запятыми. Если параметр не указан, поиск
	 *            осуществляется по всем колонкам дерева.
	 * 
	 * @return Строка, которая содержит искомое значение. Если значение не
	 *         найдено, то возвращается значение Неопределено.
	 * @throws JIException
	 */
	public OCValueTreeRow find(OCVariant value, String columnList) throws JIException {
		return find(value, columnList, false);
	}
	
	/**
	 * Осуществляет поиск значения в дереве в указанных колонках коллекции строк
	 * дерева значений. Метод эффективно использовать для поиска уникальных
	 * значений.
	 * 
	 * @param value
	 *            - Произвольный. Искомое значение.
	 * 
	 * @return Строка, которая содержит искомое значение. Если значение не
	 *         найдено, то возвращается значение Неопределено.
	 * @throws JIException
	 */
	public OCValueTreeRow find(OCVariant value) throws JIException {
		return find(value, null, false);
	}
	
	/**
	 * Осуществляет поиск строк из коллекции строк дерева значений,
	 * соответствующих заданному условию поиска.
	 * 
	 * @param structure
	 *            - Задает условия поиска: ключ структуры определяет имя
	 *            колонки, по которой будет осуществляться поиск, а значение
	 *            структуры - искомое значение.
	 * @param includeSubCollections
	 *            - Определяет, будут ли производиться поиск также в подчиненных
	 *            коллекциях (если таковые имеются). Истина - поиск будет
	 *            произведен.
	 * 
	 * @return Массив из строк дерева значений, соответствующих заданному
	 *         условию поиска. Замечание! Массив хранит ссылки на строки дерева
	 *         значений, то есть при изменении строки в дереве, значение в
	 *         массиве тоже будет изменено.
	 * @throws JIException
	 */
	public OCArray findRows(OCStructure structure, boolean includeSubCollections) throws JIException {
		return new OCArray(callMethodA("FindRows", new Object[]{
				new JIVariant(ocObject2Dispatch(structure)),
				new JIVariant(includeSubCollections)
		})[0]);
	}
	
	/**
	 * Осуществляет поиск строк из коллекции строк дерева значений,
	 * соответствующих заданному условию поиска.
	 * 
	 * @param structure
	 * @return Массив из строк дерева значений, соответствующих заданному
	 *         условию поиска. Замечание! Массив хранит ссылки на строки дерева
	 *         значений, то есть при изменении строки в дереве, значение в
	 *         массиве тоже будет изменено.
	 * @throws JIException 
	 */
	public OCArray findRows(OCStructure structure) throws JIException {
		return findRows(structure, true);
	}
	
	/**
	 * Удаляет все коллекции строки данного уровня дерева значений. Если у строк
	 * данного уровня были подчиненные строки, то они также будут удалены.
	 * Структура колонок остается неизменной.
	 * 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Получает значение по индексу. 
	 * @param index - Индекс строки
	 * @return OCValueTreeRow
	 * @throws JIException
	 */
	public OCValueTreeRow get(int index) throws JIException {
		return new OCValueTreeRow(callMethodA("Get", new JIVariant(index))[0]);
	}
	
	/**
	 * Сдвигает строку на указанное количество позиций в коллекции строк данного
	 * уровня дерева значений.
	 * 
	 * @param index
	 *            - Индекс строки, которую надо сдвинуть, либо сама сдвигаемая
	 *            строка.
	 * @param offset
	 *            - Количество строк, на которые необходимо сдвинуть строку.
	 *            Положительное смещение обозначает увеличение текущего индекса
	 *            (сдвиг к концу коллекции), отрицательное - уменьшение текущего
	 *            индекса (сдвиг к началу коллекции).
	 * @throws JIException
	 */
	public void move(int index, int offset) throws JIException {
		callMethod("Move", new Object[]{
				new JIVariant(index),
				new JIVariant(offset)
		});
	}
	
	/**
	 * Сдвигает строку на указанное количество позиций в коллекции строк данного
	 * уровня дерева значений.
	 * 
	 * @param row
	 *            - Индекс строки, которую надо сдвинуть, либо сама сдвигаемая
	 *            строка.
	 * @param offset
	 *            - Количество строк, на которые необходимо сдвинуть строку.
	 *            Положительное смещение обозначает увеличение текущего индекса
	 *            (сдвиг к концу коллекции), отрицательное - уменьшение текущего
	 *            индекса (сдвиг к началу коллекции).
	 * @throws JIException
	 */
	public void move(OCValueTreeRow row, int offset) throws JIException {
		callMethod("Move", new Object[]{
				new JIVariant(ocObject2Dispatch(row)),
				new JIVariant(offset)
		});
	}
	
	/**
	 * Сортирует строки коллекции в соответствии с указанными правилами
	 * сортировки.
	 * 
	 * @param columnsName
	 *            - Список имен колонок, разделенных запятыми, по которым
	 *            производится сортировка таблицы. После каждого имени колонки
	 *            через пробел может быть указано направление упорядочивания.
	 *            Направление определяется: "Убыв" ("Desc") - упорядочивать по
	 *            убыванию; "Возр" ("Asc") - упорядочивать по возрастанию. По
	 *            умолчанию сортировка таблица производится по возрастанию.
	 *            Порядок указания имен колонок таблицы определяет порядок
	 *            сортировки. Это означает, что сначала таблица сортируется по
	 *            колонке, указанной первой. Затем группы строк с одинаковым
	 *            значением в этой колонке сортируются по колонке, которая
	 *            указана второй, и так далее.
	 * @param inclSubCollection
	 *            - Определяет, будут ли отсортированы коллекции подчиненных
	 *            строк (если таковые имеются). Истина - коллекции будут
	 *            отсортированы
	 * @throws JIException
	 */
	public void sort(String columnsName, boolean inclSubCollection) throws JIException {
		callMethod("Sort", new Object[]{
				new JIVariant(columnsName),
				new JIVariant(inclSubCollection)
		});
	}
	
	/**
	 * Сортирует строки коллекции в соответствии с указанными правилами
	 * сортировки.
	 * 
	 * @param columnsName
	 *            - Список имен колонок, разделенных запятыми, по которым
	 *            производится сортировка таблицы. После каждого имени колонки
	 *            через пробел может быть указано направление упорядочивания.
	 *            Направление определяется: "Убыв" ("Desc") - упорядочивать по
	 *            убыванию; "Возр" ("Asc") - упорядочивать по возрастанию. По
	 *            умолчанию сортировка таблица производится по возрастанию.
	 *            Порядок указания имен колонок таблицы определяет порядок
	 *            сортировки. Это означает, что сначала таблица сортируется по
	 *            колонке, указанной первой. Затем группы строк с одинаковым
	 *            значением в этой колонке сортируются по колонке, которая
	 *            указана второй, и так далее.
	 * @throws JIException
	 */
	public void sort(String columnsName) throws JIException {
		sort(columnsName, false);
	}
	
	/**
	 * Удаляет строку из коллекции строк данного уровня дерева значений. Если у
	 * строки имеются подчиненные строки, они также будут удалены.
	 * 
	 * @param index
	 *            - Индекс удаляемой строки.
	 * @throws JIException
	 */
	public void delete(int index) throws JIException {
		callMethod("Delete", new JIVariant(index));
	}
	
	/**
	 * Удаляет строку из коллекции строк дерева значений по строке.
	 * 
	 * @param row
	 *            - Строка, которую необходимо удалить из данной коллекции строк
	 * @throws JIException
	 */
	public void delete(OCValueTreeRow row) throws JIException {
		callMethod("Delete", new JIVariant(ocObject2Dispatch(row)));
	}
}
