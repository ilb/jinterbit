/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Содержит описание набора данных схемы компоновки данных. Поддержка
 * отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data-composition-system/schema}. Имя типа XDTO:
 * DataSetUnion.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionSchemaDataSetUnion extends OCObject implements OCDataCompositionSchemaDataSet{

	/**
	 * @param object
	 */
	public OCDataCompositionSchemaDataSetUnion(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchemaDataSetUnion(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSetUnion(JIVariant aDispatch)
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
	
	// TODO Fields unimplemented
	
	/**
	 * Содержит наборы данных - составные части объединения. 
	 */
	public OCDataCompositionSchemaDataSets getItems() throws JIException {
		return new OCDataCompositionSchemaDataSets(get("Items"));
	}
}
