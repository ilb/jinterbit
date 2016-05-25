/**
 * 
 */
package com.ipc.oce.query;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Threadsafe instance.
 * @author Konovalov
 *
 */
public class QueryTemplate {
	
	private OCApp appInstance = null;
	
	/**
	 * Default constructor
	 */
	public QueryTemplate() {
		super();
	}
	
	public QueryTemplate(OCApp appIntsance) {
		super();
		this.appInstance = appIntsance;
	}
	
	/**
	 * Установка активного экземпляра OCApp
	 * @param appInstance
	 */
	public void setAppInstance(OCApp appInstance) {
		this.appInstance = appInstance;
	}
	
	public <T> List<T> queryWithMapper(String query, RowMapper<T> mapper) throws Throwable{
		return queryWithMapper(query, null, mapper);
	}

	public <T> List<T> queryWithMapper(String query, LinkedHashMap<String, OCVariant> params, RowMapper<T> mapper) throws Throwable{
		List<T> resultList = null;
		OCQueryResultSelection selection = queryForSelection(query, params);
		resultList = new ArrayList<T>(selection.size());
		int rowNumber = 0;
		while (selection.next()) {
			resultList.add(mapper.mapRow(selection, rowNumber));
			rowNumber++;
		}
		return resultList;
	}

	public List<OCObject> queryForObjects(String query) throws JIException{
		return queryForObjects(query, null);
	}
	
	public List<OCObject> queryForObjects(String query, LinkedHashMap<String, OCVariant> params) throws JIException{
		List<OCObject> resultList = null;
		
		OCQueryResultSelection selection = queryForSelection(query, params);
		
		resultList = new ArrayList<OCObject>(selection.size());
		
		while (selection.next()) {
			OCObject obj = new OCObject(selection.getObject(0));
			resultList.add(obj);
		}
		return resultList;
	}
	
	public OCQueryResultSelection queryForSelection(String query) throws JIException{
		return queryForSelection(query, null);
	}
	
	public OCQueryResultSelection queryForSelection(String query, Map<String, OCVariant> params) throws JIException{
		OCQuery iquery = appInstance.newQuery(query);
		// prepare parameters -------------------------
		if (params != null && params.size() > 0) {
			iquery.setParameters(params);
		}
		
		OCQueryResult result = iquery.execute();
		OCQueryResultSelection selection = result.choose();
		return selection;
	}
	
	 
}
