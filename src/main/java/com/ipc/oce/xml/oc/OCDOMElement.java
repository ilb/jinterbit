/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.OCObject;

/**
 * Узел элемента DOM.
 * @author Konovalov
 *
 */
public class OCDOMElement extends OCObject {

	/**
	 * @param object
	 */
	public OCDOMElement(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDOMElement(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * URI пространства имен узла. Свойство имеет значение Неопределено для
	 * узлов, отличных Атрибут и Элемент
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getNamespaceURI() throws JIException {
		JIVariant var = get("NamespaceURI");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return var.getObjectAsString2();
		} else {
			return null;
		}
	}
	
	/**
	 * Базовый URI узла DOM. Вычисляется согласно рекомендации XML Base.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getBaseURI() throws JIException {
		return get("BaseURI").getObjectAsString2();
	}
	
	/**
	 * Имя элемента узла.
	 * @return
	 * @throws JIException 
	 */
	public String getTagName() throws JIException {
		return get("TagName").getObjectAsString2();
	}

	/**
	 * Локальное имя узла DOM.
	 * @return
	 * @throws JIException
	 */
	public String getLocalName() throws JIException {
		return get("LocalName").getObjectAsString2();
	}
	
	/**
	 * Префикс узла DOM. Свойство имеет значение Неопределено для узлов,
	 * отличных Атрибут и Элемент
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getPrefix() throws JIException {
		try {
			return get("Prefix").getObjectAsString2();
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 
	 * @param prefix
	 * @throws JIException
	 */
	public void setPrefix(String prefix) throws JIException {
		put("Prefix", new JIVariant(prefix));
	}
}
