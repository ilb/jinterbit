/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Значение простого типа XDTO
 * @author Konovalov
 *
 */
public class OCXDTODataValue extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTODataValue(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTODataValue(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTODataValue(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит примитивное значение платформы, в которое отображается данное значение XDTO
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue() throws JIException{
		return new OCVariant(get("Value"));
	}
	
	/**
	 * Содержит лексическое представление значения
	 * @return
	 * @throws JIException
	 */
	public String getLexicalValue() throws JIException{
		return get("LexicalValue").getObjectAsString2();
	}
	
	/**
	 * Содержит коллекцию значений XDTO. Определена для типов списков
	 * @return OCXDTODataValueCollection
	 * @throws JIException
	 */
	public OCXDTODataValueCollection list() throws JIException{
		return new OCXDTODataValueCollection(get("List"));
	}
	
	/**
	 * Получает тип данного значения XDTO
	 * @return OCXDTOValueType
	 * @throws JIException
	 */
	public OCXDTOValueType getType() throws JIException{
		return new OCXDTOValueType(callMethodA("Type"));
	}
	
}
