/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCDataCompositionSchemaDataSetQuery extends OCObject implements OCDataCompositionSchemaDataSet{

	/**
	 * @param object
	 */
	public OCDataCompositionSchemaDataSetQuery(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchemaDataSetQuery(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSetQuery(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Имя набора данных. 
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Установка имени набора данных.
	 * @param name имя набора данных. 
	 * @throws JIException
	 */
	public void setName(String name) throws JIException {
		put("Name", new JIVariant(name));
	}
	
	/**
	 * Указывает на необходимость автоматического заполнения доступных полей на основании текста запроса. 
	 * @return
	 * @throws JIException
	 */
	public boolean isAutoFillAvailableFields() throws JIException {
		return get("AutoFillAvailableFields").getObjectAsBoolean();
	}
	
	/**
	 * Указывает на необходимость автоматического заполнения доступных полей на основании текста запроса. 
	 * @param fill
	 * @throws JIException
	 */
	public void setAutoFillAvailableFields(boolean fill) throws JIException {
		put("AutoFillAvailableFields", new JIVariant(fill));
	}
	
	/**
	 * Текст запроса для получения данных набора.
	 * @return
	 * @throws JIException
	 */
	public String getQuery() throws JIException {
		return get("Query").getObjectAsString2();
	}
	
	/**
	 * Текст запроса для получения данных набора.
	 * @param query Текст запроса
	 * @throws JIException
	 */
	public void setQuery(String query) throws JIException {
		put("Query", new JIVariant(query));
	}
	
	/**
	 *  Имя источника данных, из которого будет осуществляться получение данных. 
	 * @return
	 * @throws JIException
	 */
	public String getDataSource() throws JIException {
		return get("DataSource").getObjectAsString2();
	}
	
	/**
	 * Имя источника данных, из которого будет осуществляться получение данных. 
	 * @param dataSource  Имя источника данных
	 * @throws JIException
	 */
	public void setDataSource(String dataSource) throws JIException {
		put("DataSource", new JIVariant(dataSource));
	}
	
	// TODO Fields unimplemented

}
