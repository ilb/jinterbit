/**
 * 
 */
package com.ipc.oce.xml.oc;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Набор схем XML
 * @author Konovalov
 *
 */
public class OCXMLSchemaSet extends OCObject implements Iterable<OCXMLSchema> {

	public OCXMLSchemaSet(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCXMLSchemaSet(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCXMLSchemaSet(OCObject object) {
		super(object);
	}

	/**
	 * Добавляет схему в набор. 
	 * @param schema Схема XML, добавляемая к набору схем. 
	 * @throws JIException
	 */
	public void add(OCXMLSchema schema) throws JIException{
		callMethod("Add", new Object[]{new JIVariant(ocObject2Dispatch(schema))});
	}
	
	/**
	 * Получает количество схем в наборе
	 * @return
	 * @throws JIException
	 */
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает схему из набора по индексу 
	 * @param index Индекс схемы в наборе
	 * @return
	 * @throws JIException
	 */
	public OCXMLSchema getSchema(int index) throws JIException{
		return new OCXMLSchema(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Получает схему XML из набора схем по URI пространства имен. 
	 * @param uriNamespace URI пространства имен, для которого нужно получить схему из набора
	 * @return OCXMLSchema
	 * @throws JIException 
	 */
	public OCXMLSchema getSchema(String uriNamespace) throws JIException{
		return new OCXMLSchema(callMethodA("Get", new Object[]{new JIVariant(uriNamespace)})[0]);
	}
	
	/**
	 * Проверяет набора схем XML на правильность в соответствии со стандартом
	 * @param schemaSet Набор схем XML, используемый для разыменования ссылок на схему из проверяемого набора
	 * @return  Истина - успешная проверка набора схем; Ложь - в противном случае
	 * @throws JIException
	 */
	public Boolean validate(OCXMLSchemaSet schemaSet) throws JIException{
		return callMethodA("Validate", new Object[]{ocObject2Dispatch(schemaSet)})[0].getObjectAsBoolean();
	}
	
	/**
	 * Удаляет схему с указанным URI пространства имен из набора схем
	 * @param uriNamespace URI пространства имен удаляемой схемы
	 * @throws JIException
	 */
	public void delete(String uriNamespace) throws JIException{
		callMethod("Delete", new Object[]{new JIVariant(uriNamespace)});
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCXMLSchema> iterator() {
		EnumVARIANT<OCXMLSchema> enumV = new EnumVARIANT<OCXMLSchema>(this) {
			
			@Override
			protected OCXMLSchema castToGeneric(JIVariant variant) {
				try {
					return new OCXMLSchema(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
