package com.ipc.oce.query;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCValueTable;
import com.ipc.oce.OCValueTree;
import com.ipc.oce.StaticFieldInstance;
import com.ipc.oce.varset.EQueryResultIteration;

/**
 * Содержит результат выполнения запроса. Предназначен для хранения и обработки
 * полученных данных.
 * 
 * @author Konovalov
 * 
 */
public class OCQueryResult extends OCObject {
	private OCQueryResultColumnsCollection columnsCollection = null;
	
	public OCQueryResult(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCQueryResult(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public OCQueryResult(OCObject object) {
		super(object);
	}

	/**
	 * Содержит коллекцию колонок результата запроса.
	 * 
	 * @return OCQueryResultColumnsCollection
	 * @throws JIException
	 */
	public OCQueryResultColumnsCollection getColumns() throws JIException {
		if (columnsCollection == null) {
			columnsCollection = new OCQueryResultColumnsCollection(get("Columns"));
		}
		return columnsCollection;
	}

	/**
	 * Формирует выборку записей из результата запроса. Получение выборок очень
	 * большого размера (более 64Mb) требует наличия достаточного количества
	 * свободного места на диске, используемом для размещения временных файлов
	 * сервера и клиента.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCQueryResultSelection choose() throws JIException {
		//return new OCQueryResultSelection(callMethodA("Choose"), this);
		EQueryResultIteration qri = OCApp.getInstance(getAssociatedSessionID()).findVarset(EQueryResultIteration.LINEAR);
		return choose(qri, null, null);
	}
	
	/**
	 * Формирует выборку записей из результата запроса
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
				})[0], this);
	}
	
	/**
	 * Истина - нет ни одной записи; Ложь - в противном случае
	 * @return
	 * @throws JIException
	 */
	public boolean isEmpty() throws JIException {
		return callMethodA("IsEmpty").getObjectAsBoolean();
	}
	
	/**
	 * Создает таблицу значений (или дерево значений) и копирует в нее все
	 * записи набора. Тип обхода - прямой.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCValueTable unload() throws JIException {
		return new OCValueTable(callMethodA("Unload"));
	}
	
	/**
	 * Создает таблицу значений (или дерево значений) и копирует в нее все
	 * записи набора.
	 * 
	 * @param <T>
	 *            OCValueTable или OCValueTree
	 * @param unloadType
	 *            - Задает тип обхода записей в получаемой выборке. Значение по
	 *            умолчанию: Прямой
	 * @return Тип: ТаблицаЗначений, ДеревоЗначений. Если тип обхода задан
	 *         Прямой, результат выгружается в таблицу значений, в противном
	 *         случае в дерево значений.
	 * @throws JIException
	 */
	public <T extends OCObject> T unload(StaticFieldInstance unloadType) throws JIException {
		JIVariant res = null;
		OCObject resObject = null;
		if (unloadType != null) {
			res = callMethodA("Unload", new JIVariant(ocObject2Dispatch(unloadType)))[0];
			String unloadTypeString = unloadType.getFieldName();
			if (unloadTypeString.endsWith("Linear") || unloadTypeString.endsWith("Прямой")) {
				resObject = new OCValueTable(res);
			} else {
				resObject = new OCValueTree(res);
			}
		} else {
			resObject = unload();
		}
		return (T) resObject;
	}
	
	@Override
	public String toString() {
		try {
			return "QueryResult. empty=" + isEmpty() + ", "
					+ getColumns().toString();
		} catch (JIException e) {
			return "";
		}
	}
	
	
}
