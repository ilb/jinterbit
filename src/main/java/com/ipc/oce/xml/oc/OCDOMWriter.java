/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Объект записи узлов DOM. 
 * @author Konovalov
 *
 */
public class OCDOMWriter extends OCObject {

	/**
	 * @param object
	 */
	public OCDOMWriter(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDOMWriter(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDOMWriter(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Выполняет запись документа DOM. 
	 * @param Записываемый документ DOM
	 * @param Объект, записывающий данные XML
	 * @throws JIException
	 */
	public void write(OCDOMDocument document, OCXMLWriter writer) throws JIException{
		callMethod("Write", new Object[]{ocObject2Dispatch(document), ocObject2Dispatch(writer)});
	}
	
	/**
	 * Выполняет запись узла DOM. 
	 * @param Записываемый узел DOM
	 * @param Объект, записывающий данные XML
	 * @throws JIException
	 */
	public void write(OCDOMElement element, OCXMLWriter writer) throws JIException{
		callMethod("Write", new Object[]{ocObject2Dispatch(element), ocObject2Dispatch(writer)});
	}

}
