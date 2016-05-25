/**
 * 
 */
package com.ipc.oce.query;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * @author Konovalov
 *
 */
public class OCQueryParameterDescription extends OCObject {

	/**
	 * @param object
	 */
	public OCQueryParameterDescription(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCQueryParameterDescription(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCQueryParameterDescription(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит имя параметра. 
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException{
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Содержит рекомендуемый тип параметра, определяемый на основе анализа операции, производимой с параметром
	 * @return OCTypeDescription
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException{
		return new OCTypeDescription(callMethodA("ValueType"));
	}

}
