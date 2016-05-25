package com.ipc.oce.query;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Представляет собой колонку результата запроса
 * @author Konovalov
 *
 */
public class OCQueryResultColumn extends OCObject {

	public OCQueryResultColumn(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCQueryResultColumn(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Содержит имя колонки
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Содержит объект, описывающий допустимые типы значений для колонки
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException {
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Содержит ширину колонки в символах
	 * @return
	 * @throws JIException
	 */
	public Integer getWidth() throws JIException {
		return get("Width").getObjectAsInt();
	}

	@Override
	protected IJIDispatch dispatch() {
		// TODO Auto-generated method stub
		return super.dispatch();
	}
	
	
}
