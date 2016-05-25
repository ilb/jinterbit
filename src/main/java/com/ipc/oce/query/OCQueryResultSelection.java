package com.ipc.oce.query;

import java.util.HashMap;
import java.util.Map;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.varset.EQueryResultIteration;
import com.ipc.oce.varset.OCQueryRecordType;

/**
 * Объект этого типа возвращается методом Выбрать у объекта типа РезультатЗапроса и представляет собой специализированный способ перебора записей запроса. 
 * @author Konovalov
 *
 */
public class OCQueryResultSelection extends OCObject {
	
	private OCQueryResult queryResult = null;
	
	private Map<Integer, OCTypeDescription> intTypeCache = new HashMap<Integer, OCTypeDescription>();
	private Map<String, OCTypeDescription> stringTypeCache = new HashMap<String, OCTypeDescription>();
	
	public OCQueryResultSelection(IJIDispatch aDispatch, OCQueryResult queryResult) {
		super(aDispatch);
		this.queryResult = queryResult;
	}
	
	public OCQueryResultSelection(JIVariant aDispatch, OCQueryResult queryResult) throws JIException {
		super(aDispatch);
		this.queryResult = queryResult;
	}
	
	/**
	 * Дополнительный метод для получения метаданных о полях из selection, 
	 * а не только из result.
	 * @return OCQueryResultColumnsCollection
	 * @throws JIException
	 */
	public OCQueryResultColumnsCollection getColumns() throws JIException {
		return queryResult.getColumns();
	}
	
	/**
	 * Набор свойств содержит значения полей записи результата запроса. 
	 * Доступ к значению осуществляется по имени поля
	 * @param fieldName именя поля
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(String fieldName) throws JIException {
		OCTypeDescription description = null;
		if (stringTypeCache.containsKey(fieldName)) {
			description = stringTypeCache.get(fieldName);
		} else {
			description = getColumns().getColumn(fieldName).getValueType();
			stringTypeCache.put(fieldName, description);
		}
		return new OCVariant(get(fieldName), description);
	}
	
	/**
	 * Используется, например, для получения объектов типа. Ссылка
	 * 
	 * @param fieldName
	 *            имя поля
	 * @return
	 * @throws JIException
	 */
	public OCObject getObject(String fieldName) throws JIException {
		return new OCObject(get(fieldName));
	}
	
	/**
	 * Получает значение по индексу. 
	 * @param aIndex Индекс поля выборки
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(int aIndex) throws JIException {
		OCTypeDescription description = null;
		if (intTypeCache.containsKey(aIndex)) {
			description = intTypeCache.get(aIndex);
		} else {
			description = getColumns().getColumn(aIndex).getValueType();
			intTypeCache.put(aIndex, description);
		}
		OCVariant variant = new OCVariant(callMethodA("Get",
				new Object[] { new JIVariant(aIndex) })[0], description);
		return variant;
	}
	
	/**
	 * Используется, например, для получения объектов типа .Ссылка
	 * @param aIndex индекс поля
	 * @return
	 * @throws JIException
	 */
	public OCObject getObject(int aIndex) throws JIException {
		return new OCObject(callMethodA("Get", new Object[] { new JIVariant(
				aIndex) })[0]);
	}
	
	//=====================================================
	// Short path methods =================================
	// Используются для полей тип которого известен заранее. 
	// Иначе используйте getValue -> OCVariant -> value
	public String getString(String aFieldName) throws JIException {
		return get(aFieldName).getObjectAsString2();
	}
	
	public String getString(int aIndex) throws JIException {
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsString2();
	}
	
	public java.util.Date getDate(String aFieldName) throws JIException {
		return get(aFieldName).getObjectAsDate();
	}

	public java.util.Date getDate(int aIndex) throws JIException {
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsDate();
	}
	
	public int getInt(String aFieldName) throws JIException {
		return get(aFieldName).getObjectAsInt();
	}
	
	public int getInt(int aIndex) throws JIException {
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsInt();
	}
	
	public short getShort(int aIndex) throws JIException{
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsShort();
	}
	
	public short getShort(String aFieldName) throws JIException{
		return get(aFieldName).getObjectAsShort();
	}
	
	public boolean getBoolean(String aFieldName) throws JIException {
		return get(aFieldName).getObjectAsBoolean();
	}
	
	public boolean getBoolean(int aIndex) throws JIException {
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsBoolean();
	}
	
	public long getLong(int aIndex) throws JIException{
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsLong();
	}
	
	public long getLong(String aFieldName) throws JIException{
		return get(aFieldName).getObjectAsLong();
	}
	
	public float getFloat(int aIndex) throws JIException{
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsFloat();
	}
	
	public float getFloat(String aFieldName) throws JIException{
		return get(aFieldName).getObjectAsFloat();
	}
	
	public double getDouble(int aIndex) throws JIException{
		return callMethodA("Get", new Object[] {aIndex})[0].getObjectAsDouble();
	}
	
	public double getDouble(String aFieldName) throws JIException{
		return get(aFieldName).getObjectAsDouble();
	}
	//==================================================
	/**
	 * Формирует выборку вложенных записей для текущей записи результата. 
	 * @param iterationMode Задает тип обхода записей в получаемой выборке. EQueryResultIteration
	 * @param grouping Список группировок по которым будет вестись обход, разделенных запятыми. Для детальных записей указывается пустая строка. В случае, если группировки не указаны - будет использоваться следующая группировка, указанная в предложении запроса "ИТОГИ". 
	 * @param grouping2 Список группировок, из которых будут выбираться значения группировок для обхода, разделенных запятыми. Если указано "Все", то будут выбираться все значения группировок. Если указана пустая строка, то значения для группировок будут выбираться из предыдущей группировки. 
	 * @return OCQueryResultSelection
	 * @throws JIException
	 */
	public OCQueryResultSelection choose(EQueryResultIteration iterationMode, String grouping, String grouping2) throws JIException{
		return new OCQueryResultSelection(callMethodA("Choose", new Object[]{
				new JIVariant(ocObject2Dispatch(iterationMode)),
				new JIVariant(grouping),
				new JIVariant(grouping2)
				})[0], queryResult);
	}
	/**
	 * Возвращает результат запроса, из которого получена выборка
	 */
	public OCQueryResult getOwner() throws JIException {
		return new OCQueryResult(callMethodA("Owner"));
	}
	
	/**
	 * Имя группировки текущей записи. 
	 * @return Если группировки нет, возвращается пустая строка
	 * @throws JIException
	 */
	public String getGroup() throws JIException {
		return callMethodA("Group").getObjectAsString2();
	}
	
	/**
	 * Получает количество записей в выборке из результата запроса
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * После вызова метода Следующий (@see next) выборка спозиционируется на первую запись в ней
	 * @throws JIException
	 */
	public void reset() throws JIException {
		callMethod("Reset");
	}
	
	/**
	 * Получает следующую запись из результата запроса. Для обхода результата запроса нужно после получения выборки вызвать данный метод для позиционирования на первый элемент и далее вызывать до тех пор, пока не будет возвращено значение Ложь. 
	 * @return  Истина - следующая запись выбрана; Ложь - достигнут конец выборки
	 * @throws JIException
	 */
	public boolean next() throws JIException {
		return callMethodA("Next").getObjectAsBoolean();
	}
	
	/**
	 * Получает следующую запись по значению указанного поля. Выборка позиционируется на следующую запись со значением, отличающимся от текущего значения, по указанному полю. При первом вызове - остается на текущей записи. 
	 * @param aFieldName  Имя поля результата запроса, в котором будет осуществляться поиски следующего значения
	 * @return  Истина - следующая запись выбрана; Ложь - достигнут конец выборки
	 * @throws JIException
	 */
	public boolean nextByFieldValue(String aFieldName) throws JIException {
		JIVariant[] result = callMethodA("NextByFieldValue", new Object[]{aFieldName});
		return result[0].getObjectAsBoolean();
	}
	
	/**
	 * Получает тип текущей записи выборки
	 * @return
	 * @throws JIException
	 */
	public OCQueryRecordType getRecordType() throws JIException {
		return new OCQueryRecordType(callMethodA("RecordType"));
	}
	
	/**
	 * Получает уровень текущей записи в иерархии и группировках. Уровень считается от начальной выборки из результата запроса. 
	 * @return Уровень текущей записи
	 * @throws JIException
	 */
	public int getLevel() throws JIException {
		return callMethodA("Level").getObjectAsInt();
	}
}
