package com.ipc.oce.query;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Представляет собой коллекцию колонок результата запроса.
 * @author Konovalov
 *
 */
public class OCQueryResultColumnsCollection extends OCObject {
	
	/**
	 * Количество колонок в курсоре.
	 */
	private Integer colSZ = null;
	
	private Map<Integer, OCQueryResultColumn> intCache = new LinkedHashMap<Integer, OCQueryResultColumn>();
	
	private Map<String, OCQueryResultColumn> stringCache = new LinkedHashMap<String, OCQueryResultColumn>();
	
	private List<String> listOfColNames = null;
	
	public OCQueryResultColumnsCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCQueryResultColumnsCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public List<String> getNames() throws JIException {
		if (listOfColNames == null) {
			listOfColNames = new ArrayList<String>();
			int size = size();
			for (int z = 0; z < size; z++) {
				listOfColNames.add(getColumn(z).getName());
			}
		}
		return listOfColNames;
	}

	/**
	 * Получает индекс колонки результата запроса.
	 * @param aColumn
	 * @return Индекс указанной колонки в коллекции. Если не найдено, то возвращается -1
	 * @throws JIException
	 */
	public int indexOf(OCQueryResultColumn column) throws JIException {
		return callMethodA("IndexOf", new Object[] {new JIVariant(column.dispatch())})[0].getObjectAsInt();
	}
	
	/**
	 * Получает количество колонок результата запроса.
	 * @return количество колонок результата запроса
	 * @throws JIException - ошибка обращения к DCOM-серверу
	 */
	public final int size() throws JIException {
		if (colSZ == null) {
			colSZ = callMethodA("Count").getObjectAsInt();
		}
		return colSZ;
	}
	
	/**
	 * Получает количество колонок результата запроса.
	 * @deprecated will be removed in 0.4.0. Replaced by size()
	 * @return количество колонок результата запроса
	 * @throws JIException
	 */
	public int count() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Осуществляет поиск колонки результата запроса по имени
	 * @param Имя колонки результата запроса
	 * @return  Если указанная колонка отсутствует, то возвращается значение Неопределено. 
	 * @throws JIException
	 */
	public OCQueryResultColumn find(String aColumnName) throws JIException {
		OCQueryResultColumn col = null;
		if (stringCache.containsKey(aColumnName)) {
			col = stringCache.get(aColumnName);
		} else {
			col = new OCQueryResultColumn(callMethodA("Find",
					new Object[] { aColumnName })[0]);
			stringCache.put(aColumnName, col);
		}
		return col;
	}
	
	/**
	 * Получает значение по индексу. 
	 * @param aColumnIndex Индекс колонки. 
	 * @return OCQueryResultColumn
	 * @throws JIException
	 */
	public OCQueryResultColumn getColumn(int aColumnIndex) throws JIException {
		
		OCQueryResultColumn col = null;
		if (intCache.containsKey(aColumnIndex)) {
			col = intCache.get(aColumnIndex);
		} else {
			col = new OCQueryResultColumn(callMethodA("Get",
					new Object[] { aColumnIndex })[0]);
			intCache.put(aColumnIndex, col);
		}
		return col;
	}
	
	/**
	 * Набор свойств содержит значения колонок результата запроса. Доступ к значению осуществляется по имени колонки. Имя свойства совпадают с именами полей запроса.
	 * @param aColumnName Имя свойства совпадают с именами полей запроса
	 * @return OCQueryResultColumn
	 * @throws JIException
	 */
	public OCQueryResultColumn getColumn(String aColumnName) throws JIException {
		OCQueryResultColumn col = null;
		if (stringCache.containsKey(aColumnName)) {
			col = stringCache.get(aColumnName);
		} else {
			col = new OCQueryResultColumn(get(aColumnName));
			stringCache.put(aColumnName, col);
		}
		return col;
	}

	@Override
	public String toString() {
		try {
			return getNames().toString();
		} catch (JIException e) {
			return getClass().getName();
		}
	}
	
	
}
