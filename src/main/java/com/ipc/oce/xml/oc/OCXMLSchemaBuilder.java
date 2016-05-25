/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Построитель схемы XML.
 * @author Konovalov
 *
 */
public class OCXMLSchemaBuilder extends OCObject {

	/**
	 * @param object
	 */
	public OCXMLSchemaBuilder(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXMLSchemaBuilder(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXMLSchemaBuilder(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Создает схему на основе элемента DOM
	 * @param document - Элемент DOM, на основе которого необходимо создать схему XML
	 * @return OCXMLSchema
	 * @throws JIException
	 */
	public OCXMLSchema createXMLSchema(OCDOMDocument document) throws JIException{
		return new OCXMLSchema(callMethodA("CreateXMLSchema", new Object[]{ocObject2Dispatch(document)})[0]);
	}

}
