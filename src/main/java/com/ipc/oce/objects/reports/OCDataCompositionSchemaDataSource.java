/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Описание источника данных схемы компоновки данных. 
 * @author Konovalov
 *
 */
public class OCDataCompositionSchemaDataSource extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionSchemaDataSource(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchemaDataSource(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSource(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Имя источника данных. 
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Имя источника данных. 
	 * @param name - Имя источника данных. 
	 * @throws JIException
	 */
	public void setName(String name) throws JIException {
		put("Name", new JIVariant(name));
	}
	
	/**
	 * Строка соединения с источником данных.
	 * @return
	 * @throws JIException
	 */
	public String getConnectionString() throws JIException {
		return get("ConnectionString").getObjectAsString2();
	}
	
	/**
	 * Строка соединения с источником данных.
	 * @param connectionString
	 * @throws JIException
	 */
	public void setConnectionString(String connectionString) throws JIException {
		put("ConnectionString", new JIVariant(connectionString));
	}
	
	/**
	 * Тип источника данных. Для текущей информационной базы - "Local". 
	 * @return
	 * @throws JIException
	 */
	public String getDataSourceType() throws JIException {
		return get("DataSourceType").getObjectAsString2();
	}
	
	/**
	 * Тип источника данных. Для текущей информационной базы - "Local". 
	 * @param dataSourceType
	 * @throws JIException
	 */
	public void setDataSourceType(String dataSourceType) throws JIException {
		put("DataSourceType", new JIVariant(dataSourceType));
	}
}
