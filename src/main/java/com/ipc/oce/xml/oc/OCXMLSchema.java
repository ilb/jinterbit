/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;

/**
 * Объект схемы XML.
 * @author Konovalov
 *
 */
public class OCXMLSchema extends OCXSBasicComponent {


	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXMLSchema(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	
	
	protected OCXMLSchema(OCObject object) {
		super(object);
	}



	/**
	 * Содержит список аннотаций.
	 * @return OCXSComponentFixedList
	 * @throws JIException
	 */
	public OCXSComponentFixedList getAnnotations() throws JIException {
		return new OCXSComponentFixedList(get("Annotations"));
	}
	
	/**
	 * Список компонент схемы XML, которыми владеет данная компонента. 
	 * @return OCXSComponentFixedList
	 * @throws JIException
	 */
	public OCXSComponentFixedList getComponents() throws JIException {
		return new OCXSComponentFixedList(get("Components"));
	}
	
	/**
	 * Компонента, владеющая данной компонентой.
	 * @return OCXSBasicComponent
	 * @throws JIException
	 */
	public OCXSBasicComponent getContainer() throws JIException {
		JIVariant var = get("Container");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCXSBasicComponent(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Корневая компонента схемы XML - компонента, не имеющая владельца и являющаяся владельцем данной компоненты или ее компоненты-владельца (рекурсивно). 
	 * @return
	 * @throws JIException
	 */
	public OCXSBasicComponent getRootContainer() throws JIException {
		JIVariant var = get("RootContainer");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCXSBasicComponent(var);
		} else {
			return null;
		}
	}
	
	/**
	 * Содержит коллекцию определений типов.
	 * @return OCXSNamedComponentMap
	 * @throws JIException
	 */
	public OCXSNamedComponentMap getTypeDefinitions() throws JIException {
		return new OCXSNamedComponentMap(get("TypeDefinitions"));
	}
	
	public OCXSBasicComponent getTypeDefinitionByName(String name) throws JIException {
		return getTypeDefinitions().getByName(name);
	}
	
	/**
	 * Содержит пространство имен собственно схемы XML
	 * @return
	 * @throws JIException
	 */
	public String getSchemaForSchemaNamespaceURI() throws JIException{
		return get("SchemaForSchemaNamespaceURI").getObjectAsString2();
	}
	
	/**
	 * Получить версию схемы
	 * @return
	 * @throws JIException
	 */
	public String getVersion() throws JIException{
		return get("Version").getObjectAsString2();
	}
	
	/**
	 * Установка версии схемы.
	 * @param version версия схемы
	 * @throws JIException
	 */
	public void setVersion(String version) throws JIException{
		put("Version", new JIVariant(version));
	}
	
	/**
	 * URI пространства имен схемы.
	 * @return TargetNamespace
	 * @throws JIException
	 */
	public String getTargetNamespace() throws JIException{
		return get("TargetNamespace").getObjectAsString2();
	}
	
	/**
	 * URI пространства имен схемы
	 * @param namespace URI пространства имен схемы
	 * @throws JIException
	 */
	public void setTargetNamespace(String namespace) throws JIException{
		put("TargetNamespace", new JIVariant(namespace));
	}
	
	/**
	 * Получить документ DOM, соответствующий схеме XML
	 * @return OCDOMDocument
	 * @throws JIException
	 */
	public OCDOMDocument getDOMDocument() throws JIException{
		JIVariant variant = get("DOMDocument");
		if (variant.getType() == JIVariant.VT_EMPTY) {
			updateDOMElement();
			variant = get("DOMDocument");
		}
		return new OCDOMDocument(variant);
	}
	
	/**
	 * Установить документ DOM, соответствующий схеме XML
	 * @param OCDOMDocument
	 * @throws JIException
	 */
	public void setDOMDocument(OCDOMDocument document) throws JIException{
		put("DOMDocument", new JIVariant(ocObject2Dispatch(document)) );
	}
	
	/**
	 * Получить расположение схемы.
	 * @return
	 * @throws JIException
	 */
	public String getSchemaLocation() throws JIException{
		return get("SchemaLocation").getObjectAsString2();
	}
	
	/**
	 * Определяет расположение схемы.
	 * @param location
	 * @throws JIException
	 */
	public void setSchemaLocation(String location) throws JIException{
		put("SchemaLocation", new JIVariant(location));
	}
	
	/**
	 * Получение схемы в виде строки.
	 * @param encoding установка кодировки в заголовке вывода xml
	 * @return строковое представление xsd
	 * @throws JIException
	 */
	public String getSchemaAsString(String encoding) throws JIException{
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		
		OCDOMDocument document = getDOMDocument();
		
		OCXMLWriter xmlWriter = app.newXMLWriter();
		xmlWriter.setString(encoding);
		
		OCDOMWriter domWriter = app.newDOMWriter();
		domWriter.write(document, xmlWriter);
		
		String res = xmlWriter.close();
		
		return res;
	}
	
	/**
	 * Получение схемы в виде строки
	 * @return строковое представление xsd
	 * @throws JIException
	 */
	public String getSchemaAsString() throws JIException{
		return getSchemaAsString("UTF-8");
	}

}
