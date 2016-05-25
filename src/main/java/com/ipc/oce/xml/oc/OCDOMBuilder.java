/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Объект чтения документа DOM. 
 * @author Konovalov
 *
 */
public class OCDOMBuilder extends OCObject {

	/**
	 * @param object
	 */
	public OCDOMBuilder(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDOMBuilder(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDOMBuilder(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Создает документ DOM. Производит чтения документа XML из источника данных XML
	 * @param Объект чтения данных XML 
	 * @return
	 * @throws JIException 
	 */
	public OCDOMDocument read(OCXMLReader reader) throws JIException{
		return new OCDOMDocument(callMethodA("Read", new Object[]{ocObject2Dispatch(reader)})[0]);
	}

}
