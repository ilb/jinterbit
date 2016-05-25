/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Определяет тип значений модели XDTO
 * @author Konovalov
 *
 */
public class OCXDTOValueType extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTOValueType(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOValueType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOValueType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * URI пространства имен типа значения модели XDTO
	 * @return
	 * @throws JIException
	 */
	public String getNamespaceURI() throws JIException{
		return get("NamespaceURI").getObjectAsString2();
	}
	
	/**
	 * Содержит базовый тип данного типа
	 * @return
	 * @throws JIException
	 */
	public OCXDTOValueType getBaseType() throws JIException{
		return new OCXDTOValueType(get("BaseType"));
	}
	
	/**
	 * Имя типа значения модели XDTO
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException{
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Коллекция типов значений, участвующих в объединении
	 * @return
	 * @throws JIException
	 */
	public OCXDTOValueTypeCollection getMemberTypes() throws JIException{
		return new OCXDTOValueTypeCollection(get("MemberTypes"));
	}
	
	/**
	 * Содержит тип значения элемента списка
	 * @return
	 * @throws JIException
	 */
	public OCXDTOValueType getListItemType() throws JIException{
		try {
			return new OCXDTOValueType(get("ListItemType"));
		} catch (IllegalStateException ise) {
			return null;
		}
	}
	
	/**
	 * Осуществляет проверку лексического представления значения на соответствие типу значения
	 * @param string Лексическое представление значения
	 * @throws JIException
	 */
	public Boolean validate(String string) throws JIException{
		return callMethodA("Validate", new Object[]{new JIVariant(string)})[0].getObjectAsBoolean();
	}
	
	/**
	 * Определяет, является ли переданный тип значения потомком данного типа
	 * @param valueType Анализируемый тип. 
	 * @return Истина - переданный тип является потомком данного, Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isDescendant(OCXDTOValueType valueType) throws JIException{
		return  callMethodA("IsDescendant", new Object[]{ocObject2Dispatch(valueType)})[0].getObjectAsBoolean();
	}

}
