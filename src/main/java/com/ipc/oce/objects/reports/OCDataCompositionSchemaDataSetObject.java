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
public class OCDataCompositionSchemaDataSetObject extends OCObject implements OCDataCompositionSchemaDataSet{

	/**
	 * @param object
	 */
	public OCDataCompositionSchemaDataSetObject(OCObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchemaDataSetObject(IJIDispatch aDispatch) {
		super(aDispatch);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSetObject(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
		// TODO Auto-generated constructor stub
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
	 * Содержит имя объекта из которого будут получаться данные. 
	 * @return
	 * @throws JIException
	 */
	public String getObjectName() throws JIException {
		return get("ObjectName").getObjectAsString2();
	}
	
	/**
	 * Содержит имя объекта из которого будут получаться данные. 
	 * @param name имя объекта
	 * @throws JIException
	 */
	public void setObjectName(String name) throws JIException {
		put("ObjectName", new JIVariant(name));
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
