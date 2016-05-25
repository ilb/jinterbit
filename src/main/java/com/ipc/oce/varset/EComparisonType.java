/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

/**
 * Определяет набор видов сравнения, которые могут быть использованы в условиях
 * отбора. Используется для определения свойства ВидСравнения. Возможен обмен с
 * сервером. XML-сериализация. Поддержка отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data/enterprise}. Имя типа XDTO: ComparisonType.
 * 
 * @author Konovalov
 * 
 */
public class EComparisonType extends EActivator {
	
	/**
	 * Сравниваемое значение больше заданного в условии. Примечание: Не
	 * применяется для данных, имеющих ссылочные типы.
	 */
	public static final String GREATER = "EComparisonType.GREATER";
	
	/**
	 * Сравниваемое значение больше или равно заданному в условии. Примечание:
	 * Не применяется для данных, имеющих ссылочные типы.
	 */
	public static final String GREATER_OR_EQUALS = "EComparisonType.GREATER_OR_EQUALS";
	
	/**
	 * Проверка того, что сравниваемое значение принадлежит иерархии заданного
	 * значения. Имеет смысл при сравнении для иерархических объектов
	 * (справочников, планов видов характеристик, планов счетов). Примечание:
	 * Данный вид сравненя не поддерживается в отборах динамических списков.
	 */
	public static final String IN_HIERARCHY = "EComparisonType.IN_HIERARCHY";
	
	/**
	 * Проверка того, что сравниваемое значение входит в список Примечание: Для
	 * элемента отбора в качестве значения принимается список значений.
	 */
	public static final String IN_LIST = "EComparisonType.IN_LIST";
	
	/**
	 * Проверка того, что сравниваемое значение входит в список с учетом
	 * иерархии. Примечание: Имеет смысл при сравнении для иерархических
	 * справочников, планов видов характеристик, планов счетов. Данный вид
	 * сравненя не поддерживается в отборах динамических списков. Для элемента
	 * отбора в качестве значения принимается список значений.
	 */
	public static final String IN_LIST_BY_HIERARCHY = "EComparisonType.IN_LIST_BY_HIERARCHY";
	
	/**
	 * Сравниваемое значение лежит в заданном интервале, не включая границы
	 * интервала. Примечание: Не применяется для данных, имеющих ссылочные типы.
	 */
	public static final String INTERVAL = "EComparisonType.INTERVAL";
	
	/**
	 * Сравниваемое значение лежит в заданном интервале, включая границы
	 * интервала. Примечание: Не применяется для данных, имеющих ссылочные типы.
	 */
	public static final String INTERVAL_INCL_BOUNDS = "EComparisonType.INTERVAL_INCL_BOUNDS";
	
	/**
	 * Сравниваемое значение лежит в заданном интервале, включая начальную и не
	 * включая конечную границу интервала. Примечание: Не применяется для
	 * данных, имеющих ссылочные типы.
	 */
	public static final String INTERVAL_INCL_LOWER_BOUND = "EComparisonType.INTERVAL_INCL_LOWER_BOUND";
	
	/**
	 * Сравниваемое значение лежит в заданном интервале, не включая начальную и
	 * включая конечную границу интервала. Примечание: Не применяется для
	 * данных, имеющих ссылочные типы.
	 */
	public static final String INTERVAL_INCL_UPPER_BOUND = "EComparisonType.INTERVAL_INCL_UPPER_BOUND";
	
	/**
	 * Сравниваемое значение меньше заданного в условии. Примечание: Не
	 * применяется для данных, имеющих ссылочные типы.
	 */
	public static final String LESS = "EComparisonType.LESS";
	
	/**
	 * Сравниваемое значение меньше или равно заданному в условии. Примечание:
	 * Не применяется для данных, имеющих ссылочные типы.
	 */
	public static final String LESS_OR_EQUALS = "EComparisonType.LESS_OR_EQUALS";
	
	/**
	 * Проверка того, что сравниваемое значение не принадлежит иерархии
	 * заданного значения. Имеет смысл при сравнении для иерархических объектов
	 * (справочников, планов видов характеристик, планов счетов). Примечание:
	 * Данный вид сравненя не поддерживается в отборах динамических списков.
	 */
	public static final String NOT_IN_HIERARCHY = "EComparisonType.NOT_IN_HIERARCHY";
	
	/**
	 * Проверка того, что сравниваемое значение не входит в список. Примечание:
	 * Для элемента отбора в качестве значения принимается список значений.
	 */
	public static final String NOT_IN_LIST = "EComparisonType.NOT_IN_LIST";
	
	/**
	 * Проверка того, что сравниваемое значение не входит в список с учетом
	 * иерархии. Примечание: Имеет смысл при сравнении для иерархических
	 * справочников, планов видов характеристик, планов счетов. Данный вид
	 * сравненя не поддерживается в отборах динамических списков. Для элемента
	 * отбора в качестве значения принимается список значений.
	 */
	public static final String NOT_IN_LIST_BY_HIERARCHY = "EComparisonType.NOT_IN_LIST_BY_HIERARCHY";
	
	/**
	 * Проверка на неравенство. Для строковых значений регистр символов не
	 * учитывается
	 */
	public static final String NOT_EQUALS = "EComparisonType.NOT_EQUALS";
	
	/**
	 * Сравниваемое значение не содержит подстроку, заданную в условии
	 * сравнения.
	 */
	public static final String NOT_CONTAINS = "EComparisonType.NOT_CONTAINS";
	
	/**
	 * Проверка на равенство. Для строковых значений регистр символов не
	 * учитывается.
	 */
	public static final String EQUALS = "EComparisonType.EQUALS";
	
	/**
	 * Сравниваемое значение содержит подстроку, заданную в условии сравнения.
	 * Примечание: Применяется для строк.
	 */
	public static final String CONTAINS = "EComparisonType.CONTAINS";
	
	/**
	 * @param innerString
	 */
	public EComparisonType(String innerString) {
		super(innerString);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public EComparisonType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

}
