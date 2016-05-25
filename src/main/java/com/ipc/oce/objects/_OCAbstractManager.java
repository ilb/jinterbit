/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * @author Konovalov
 *
 */
public abstract class _OCAbstractManager extends OCObject {
	
	protected String managerName = null;
	
	protected _OCCommonMetadataObject metadata = null;
	
	
	public _OCAbstractManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}
	
	public _OCAbstractManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public _OCAbstractManager(OCObject object) {
		super(object);
	}
	
	/**
	 * Установка имени менеджера объекта
	 * @param name
	 */
	protected void setManagerName(String name) {
		this.managerName = name;
	}
	
	/**
	 * Получение имени менеджера объекта
	 * @return
	 */
	public String getManagerName(){
		return managerName;
	}
	
	/**
	 * Метод должен загружать метаданные соответствующего типа с заданным именем объекта
	 * @return
	 * @throws JIException
	 */
	protected abstract _OCCommonMetadataObject loadMetadata() throws JIException;
	
	/**
	 * Получение метаданных объекта.
	 * @return _OCCommonMetadataObject
	 * @throws JIException
	 */
	public _OCCommonMetadataObject getMetadata() throws JIException {
		if (managerName == null) {
			throw new IllegalStateException(
					"Manager name is null. Use setManagerName method!");
		}
		if (metadata == null) {
			metadata = loadMetadata();
		}
		return metadata;
	}
	
	/**
	 * short path method for 
	 * OCApp.getInstance(getAssociatedSessionID()).createStructure() .
	 * @return OCStructure
	 * @throws JIException - ошибка вызова метода DCOM.
	 */
	public OCStructure createEmptyStruct() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).newStructure();
	}
}
