/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.OCObject;

/**
 * Базовый класс для элементов XSD схем.
 * @author Konovalov
 *
 */
public class OCXSBasicComponent extends OCObject {

	protected OCXSBasicComponent(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	protected OCXSBasicComponent(OCObject object) {
		super(object);
	}
	
	/**
	 * Определение типа компонента.
	 * Пример:
	 *  OCXSBasicComponent basicComponent = schemaComponents.get(0);
	 *	System.out.println("isType: " + basicComponent.isTypeOf(OCXSComponentType.IMPORT));
	 * @param typeName имя типа (например Annotation, Schema, ComplexTypeDefinition)
	 * @return true если тип объекта соответствует имени типа
	 * @throws JIException
	 */
	public boolean isTypeOf(String typeName) throws JIException {
		return getComponentType().isTypeOf(typeName);
	}

	/**
	 * Возвращает тип компоненты схемы XML. 
	 * @return
	 * @throws JIException
	 */
	public OCXSComponentType getComponentType() throws JIException {
		return new OCXSComponentType(get("ComponentType"));
	}

	/**
	 * Производит синхронизацию свойств компоненты и структуры элемента DOM
	 * @throws JIException
	 */
	public void updateDOMElement() throws JIException {
		callMethod("UpdateDOMElement");
	}
	
	/**
	 * Элемент DOM, который соответствует данной компоненте схемы XML.
	 * @return OCDOMElement
	 * @throws JIException
	 */
	public OCDOMElement getDOMElement() throws JIException {
		JIVariant var = get("DOMElement");
		if (var.getType() == JIVariant.VT_EMPTY) {
			updateDOMElement();
			var = get("DOMElement");
		}
		return new OCDOMElement(var);
	}
	
	/**
	 * Схема XML, которой принадлежит данная компонента.
	 * @return
	 * @throws JIException
	 */
	public OCXMLSchema getSchema() throws JIException {
		JIVariant var = get("Schema");
		if (var.getType() != JIVariant.VT_EMPTY) {
			return new OCXMLSchema(var);
		} else {
			return null;
		}
	}
	
}
