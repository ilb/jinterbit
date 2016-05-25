/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Тип данных XML представляется двумя строковыми величинами: именем типа и URI
 * пространства имен. Данный объект объединяет две эти величины. Возможен обмен
 * с сервером. Сериализуется.
 * 
 * @author Konovalov
 * 
 */
public class OCXMLDataType extends OCObject {

	/**
	 * @param object
	 */
	public OCXMLDataType(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXMLDataType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXMLDataType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * URI пространства имен, к которому относится тип XML.
	 * @return String
	 * @throws JIException
	 */
	public String getNamespaceURI() throws JIException {
		return get("NamespaceURI").getObjectAsString2();
	}
	
	/**
	 * Представление типа данных XML по имени типа XML. 
	 * @return String
	 * @throws JIException
	 */
	public String getTypeName() throws JIException {
		return get("TypeName").getObjectAsString2();
	}

}
