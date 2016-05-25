/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

import com.ipc.oce.OCApp;
import com.ipc.oce.StaticFieldInstance;

/**
 * @author Konovalov
 *
 */
public class OCXSComponentType extends StaticFieldInstance {
	
	/**
	 * Аннотация.
	 */
	public static final String ANNOTATION = "Annotation";
	
	/**
	 * Include.
	 */
	public static final String INCLUDE = "Include";
	
	/**
	 * Группа модели.
	 */
	public static final String MODEL_GROUP = "ModelGroup";
	
	/**
	 * Документация.
	 */
	public static final String DOCUMENTATION = "Documentation";
	
	/**
	 * Директива импорта.
	 */
	public static final String IMPORT = "Import";
	
	/**
	 * Информация приложения. 
	 */
	public static final String APP_INFO = "AppInfo";
	
	/**
	 * Компонента использования атрибута.
	 */
	public static final String ATTIBUTE_USE = "AttributeUse";
	
	/**
	 * Фасет максимального включающего значения.
	 */
	public static final String MAX_INCLUSIVE_FACET = "MaxInclusiveFacet";
	
	/**
	 * Фасет максимального исключающего значения.
	 */
	public static final String MAX_EXCLUSIVE_FACET = "MaxExclusiveFacet";
	
	/**
	 * Маска.
	 */
	public static final String WILDCARD = "Wildcard";
	
	/**
	 * Фасет минимального включающего значения.
	 */
	public static final String MIN_INCLUSIVE_FACET = "MinInclusiveFacet";
	
	/**
	 * Фасет минимального исключающего значения.
	 */
	public static final String MIN_EXCLUSIVE_FACET = "MinExclusiveFacet";
	
	/**
	 * Объявление атрибута.
	 */
	public static final String ATTRIBUTE_DECLARATION = "AttributeDeclaration";
	
	/**
	 * Объявление нотации.
	 */
	public static final String NOTATION_DECLARATION = "NotationDeclaration";
	
	/**
	 * Объявление элемента.
	 */
	public static final String ELEMENT_DECLARATION = "ElementDeclaration";
	
	/**
	 * Определение XPath.
	 */
	public static final String XPATH_DEFINITION = "XPathDefinition";
	
	/**
	 * Определение группы атрибутов.
	 */
	public static final String ATTRIBUTE_GROUP_DEFINITION = "AttributeGroupDefinition";
	
	/**
	 * Определение группы модели. 
	 */
	public static final String MODEL_GROUP_DEFINITION = "ModelGroupDefinition";
	
	/**
	 * Определение ограничения идентичности. 
	 */
	public static final String IDENTITY_CONSTRAINT_DEFINITION = "IdentityConstraintDefinition";
	
	/**
	 * Определение простого типа.
	 */
	public static final String SIMPLE_TYPE_DEFINITION = "SimpleTypeDefinition";
	
	/**
	 * Определение составного типа.
	 */
	public static final String COMPLEX_TYPE_DEFINITION = "ComplexTypeDefinition";
	
	/**
	 * Директива переопределения.
	 */
	public static final String REDEFINE = "Redefine";
	
	/**
	 * Схема.
	 */
	public static final String SCHEMA = "Schema";
	
	// TODO и т.д. см. XSComponentType
	
	
	/**
	 * @param object
	 * @throws JIException
	 */
	protected OCXSComponentType(JIVariant object) throws JIException {
		super(object);
	}
	
	/**
	 * Определение типа компонента.
	 * Пример:
	 *  OCXSBasicComponent basicComponent = schemaComponents.get(0);
	 *	OCXSComponentType componentType = basicComponent.getComponentType();
	 *	System.out.println("isType: " + componentType.isTypeOf(OCXSComponentType.IMPORT));
	 * @param typeName имя типа (например Annotation, Schema, ComplexTypeDefinition)
	 * @return true если тип объекта соответствует имени типа
	 * @throws JIException
	 */
	public boolean isTypeOf(String typeName) throws JIException {
		typeName = "XSComponentType." + typeName;
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		StaticFieldInstance sfi = app.getStaticFields(typeName);
		return sfi.equals(this);
	}

}
