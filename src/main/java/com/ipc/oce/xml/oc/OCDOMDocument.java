/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Документ DOM. 
 * @author Konovalov
 *
 */
public class OCDOMDocument extends OCObject {

	/**
	 * @param object
	 */
	public OCDOMDocument(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDOMDocument(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDOMDocument(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получить URI документа DOM
	 * @return
	 * @throws JIException
	 */
	public String getDocumentURI() throws JIException{
		return get("DocumentURI").getObjectAsString2();
	}
	
	/**
	 * Установить URI документа DOM
	 * @param uri
	 * @throws JIException
	 */
	public void setDocumentURI(String uri) throws JIException{
		put("DocumentURI", new JIVariant(uri));
	}
	
	/**
	 * URI пространства имен узла. Свойство имеет значение Неопределено для узлов, отличных Атрибут и Элемент, или для узлов, созданных при помощи метода DOM Level 1
	 * @return String
	 * @throws JIException
	 */
	public String getNamespaceURI() throws JIException{
		return get("NamespaceURI").getObjectAsString2();
	}
}
