/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Объект модели XDTO
 * @author Konovalov
 *
 */
public class OCXDTOObjectType extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTOObjectType(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOObjectType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOObjectType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * URI пространства имен типа объекта
	 * @return
	 * @throws JIException
	 */
	public String getNamespaceURI() throws JIException{
		return get("NamespaceURI").getObjectAsString2();
	}
	
	/**
	 * Содержит признак абстрактного типа объекта. 
	 * @return Истина - тип абстрактный.
	 * @throws JIException
	 */
	public Boolean isAbstract() throws JIException{
		return get("Abstract").getObjectAsBoolean();
	}
	
	/**
	 * Базовый тип объекта
	 * @return
	 * @throws JIException
	 */
	public OCXDTOObjectType getBaseType() throws JIException{
		return new OCXDTOObjectType(get("BaseType"));
	}
	
	/**
	 * Имя типа объекта
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException{
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Содержит признак открытости типа. Открытый тип может содержать пользовательские свойства
	 * @return Истина - тип открытый. 
	 * @throws JIException
	 */
	public Boolean isOpen() throws JIException{
		return get("Open").getObjectAsBoolean();
	}
	
	/**
	 * Содержит признак наличия у объекта данного типа последовательности
	 * @return Истина - тип последовательный. 
	 * @throws JIException
	 */
	public Boolean isSequenced() throws JIException{
		return get("Sequenced").getObjectAsBoolean();
	}
	
	/**
	 * Содержит коллекцию свойств объекта. 
	 * @return OCXDTOPropertyCollection
	 * @throws JIException
	 */
	public OCXDTOPropertyCollection getProperties() throws JIException{
		return new OCXDTOPropertyCollection(get("Properties"));
	}
	
	/**
	 * Содержит признак наличия у объектов данного типа смешанного содержания (текст и элементы). 
	 * @return Истина - тип смешанный. 
	 * @throws JIException
	 */
	public Boolean isMixed() throws JIException{
		return get("Mixed").getObjectAsBoolean();
	}
	
	/**
	 * Содержит признак упорядоченности свойств объекта данного типа
	 * @return Истина - порядок следования свойств в объекте должен соответствовать порядку следования свойств в типе объекта. В противном случае порядок следования свойств может быть произвольным. 
	 * @throws JIException
	 */
	public Boolean isOrdered() throws JIException{
		return get("Ordered").getObjectAsBoolean();
	}
	
	/**
	 * Проверяет соответствие объекта модели XDTO. 
	 * @param dataObject Проверяемый объект
	 * @return Boolean
	 * @throws JIException
	 */
	public Boolean validate(OCXDTODataObject dataObject) throws JIException{
		return callMethodA("Validate", new Object[]{ocObject2Dispatch(dataObject)})[0].getObjectAsBoolean();
	}
	
	/**
	 * Определяет, является ли переданный тип значения потомком данного типа
	 * @param objectType Проверяемый тип. 
	 * @return  Истина - переданный тип является потомком данного, Ложь - в противном случае
	 * @throws JIException
	 */
	public Boolean isDescendant(OCXDTOObjectType objectType) throws JIException{
		return callMethodA("IsDescendant", new Object[]{ocObject2Dispatch(objectType)})[0].getObjectAsBoolean();
	}

}
