/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;

/**
 * Контекст пространств имен. 
 * @author Konovalov
 *
 */
public class OCXMLNamespaceContext extends OCObject {

	/**
	 * @param object
	 */
	public OCXMLNamespaceContext(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXMLNamespaceContext(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXMLNamespaceContext(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Глубина контекста - количество вложенных элементов XML, начиная от текущего узла (текущий узел определяется от объекта владельца - ЧтениеXML или ЗаписьXML) до корня документа XML. 
	 * @return
	 * @throws JIException
	 */
	public int getDepth() throws JIException{
		return get("Depth").getObjectAsInt();
	}
	
	/**
	 * URI пространства имен по умолчанию. Пустая строка, если пространства имен по умолчанию отсутствует. 
	 * @return
	 * @throws JIException
	 */
	public String getDefaultNamespace() throws JIException{
		return get("DefaultNamespace").getObjectAsString2();
	}
	
	/**
	 * Получает массив URI пространств имен, определенных в данном контексте
	 * @return
	 * @throws JIException
	 */
	public OCArray getNamespaceURIs() throws JIException{
		return new OCArray(callMethodA("NamespaceURIs"));
	}
	
	

}
