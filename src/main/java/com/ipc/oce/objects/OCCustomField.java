/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCValueList;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Допустимое поле для отбора, порядка, коллекций полей построителя отчета. 
 * @author Konovalov
 *
 */
public class OCCustomField extends OCObject {

	/**
	 * @param object
	 */
	public OCCustomField(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCCustomField(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCCustomField(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Показывает, является ли поле допустимым к использованию в качестве измерения построителя отчета. 
	 * @return boolean
	 * @throws JIException
	 */
	public boolean isDimension() throws JIException {
		return get("Dimension").getObjectAsBoolean();
	}
	
	public void setDimension(boolean dimension) throws JIException {
		put("Dimension", new JIVariant(dimension));
	}
	
	/**
	 * Содержит имя поля. 
	 * @return String
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Установка имени поля.
	 * @param name
	 * @throws JIException
	 */
	public void setName(String name) throws JIException {
		put("Name", new JIVariant(name));
	}
	
	/**
	 * Признак того, что поле может выступать в качестве элемента отбора. 
	 * @return
	 * @throws JIException
	 */
	public boolean isFilter() throws JIException {
		return get("Filter").getObjectAsBoolean();
	}
	
	public void setFilter(boolean filter) throws JIException {
		put("Filter", new JIVariant(filter));
	}
	
	/**
	 * Содержит дочерние поля настройки. 
	 * @return
	 * @throws JIException
	 */
	public OCCustomFields getFields() throws JIException {
		return new OCCustomFields(get("Fields"));
	}
	
	/**
	 * Признак того, что поле может выступать в качестве элемента порядка. 
	 * @return
	 * @throws JIException
	 */
	public boolean isOrder() throws JIException {
		return get("Order").getObjectAsBoolean();
	}
	
	public void setOrder(boolean order) throws JIException {
		put("Order", new JIVariant(order));
	}
	
	/**
	 * Представление поля. 
	 * @return
	 * @throws JIException
	 */
	public String getPresentation() throws JIException {
		return get("Presentation").getObjectAsString2();
	}
	
	/**
	 * Представление поля. 
	 * @param presentation
	 * @throws JIException
	 */
	public void setPresentation(String presentation) throws JIException {
		put("Presentation", new JIVariant(presentation));
	}
	
	/**
	 * Содержит полный путь к полю. 
	 * @return
	 * @throws JIException
	 */
	public String getDataPath() throws JIException {
		return get("DataPath").getObjectAsString2();
	}
	
	/**
	 * Содержит родительское поле. 
	 * @return
	 * @throws JIException
	 */
	public OCCustomField getParent() throws JIException {
		return new OCCustomField(get("Parent"));
	}
	
	/**
	 * Список допустимых значений для поля. 
	 * @return OCValueList
	 * @throws JIException
	 */
	public OCValueList getValueList() throws JIException {
		return new OCValueList(get("ValueList"));
	}
	
	/**
	 * Тип поля.
	 * @return OCTypeDescription
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException {
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Тип поля.
	 * @param typeDescription
	 * @throws JIException
	 */
	public void setValueType(OCTypeDescription typeDescription) throws JIException {
		put("ValueType", new JIVariant(ocObject2Dispatch(typeDescription)));
	}
}
