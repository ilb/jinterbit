/**
 * 
 */
package com.ipc.oce.query;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCQueryParametersDescription extends OCObject {

	/**
	 * @param object
	 */
	public OCQueryParametersDescription(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCQueryParametersDescription(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCQueryParametersDescription(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * С помощью данного свойства осуществляется доступ к значению параметра по его имени
	 * @param имя параметра
	 * @return OCQueryParameterDescription
	 * @throws JIException
	 */
	public OCQueryParameterDescription getParameter(String name) throws JIException{
		return new OCQueryParameterDescription(get(name));
	}
	
	/**
	 * Получает количество параметров в коллекции. 
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Осуществляет поиск параметра запроса по имени. 
	 * @param name Имя искомого параметра. 
	 * @return OCQueryParameterDescription
	 * @throws JIException
	 */
	public OCQueryParameterDescription find(String name) throws JIException{
		return new OCQueryParameterDescription(callMethodA("Find", new Object[]{new JIVariant(name)})[0]);
	}

}
