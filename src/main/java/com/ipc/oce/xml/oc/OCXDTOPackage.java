/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Коллекция типов XDTO, принадлежащих одному пространству имен
 * @author Konovalov
 *
 */
public class OCXDTOPackage extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTOPackage(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOPackage(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOPackage(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * URI пространства имен пакета типов.
	 * @return
	 * @throws JIException
	 */
	public String getNamespaceURI() throws JIException{
		return get("NamespaceURI").getObjectAsString2();
	}
	
	/**
	 * Содержит коллекцию пакетов XDTO, от которых есть зависимости у данного пакета (ссылки на типы). 
	 * @return
	 * @throws JIException
	 */
	public OCXDTOPackageCollection getDependencies() throws JIException{
		return new OCXDTOPackageCollection(get("Dependencies"));
	}
	
	/**
	 * Содержит коллекцию корневых свойств пакета XDTO
	 * @return
	 * @throws JIException
	 */
	public OCXDTOPropertyCollection getRootProperties() throws JIException{
		return new OCXDTOPropertyCollection(get("RootProperties"));
	}
	
	/**
	 * Получает количество элементов коллекции. 
	 * @return
	 * @throws JIException
	 */
	public int size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает тип XDTO по индексу.
	 * @param index индекс
	 * @return OCXDTOValueType
	 * @throws JIException
	 */
	public OCXDTOValueType getValueType(int index) throws JIException{
		return new OCXDTOValueType(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Получает тип XDTO по имени. 
	 * @param name Имя получаемого типа XDTO. 
	 * @return
	 * @throws JIException
	 */
	public OCXDTOValueType getValueType(String name) throws JIException{
		return new OCXDTOValueType(callMethodA("Get", new Object[]{new JIVariant(name)})[0]);
	}
	
	/**
	 * Получает тип XDTO по индексу.
	 * @param index
	 * @return OCXDTOObjectType
	 * @throws JIException
	 */
	public OCXDTOObjectType getObjectType(int index) throws JIException{
		return new OCXDTOObjectType(callMethodA("Get", new Object[]{new JIVariant(index)})[0]);
	}
	
	/**
	 * Получает тип XDTO по имени. 
	 * @param name Имя получаемого типа XDTO. 
	 * @return
	 * @throws JIException
	 */
	public OCXDTOObjectType getObjectType(String name) throws JIException{
		return new OCXDTOObjectType(callMethodA("Get", new Object[]{new JIVariant(name)})[0]);
	}
	
	/**
	 * Получает тип XDTO по индексу.
	 * @param <T> тип объекта OCXDTOObjectType или OCXDTOValueType
	 * @param index Индекс элемента коллекции. 
	 * @return Может возвращаеть объекты двух типов. Рекомендуется в качестве generic-а использовать Object и далее instanceof. ТипЗначенияXDTO, ТипОбъектаXDTO. (OCXDTOObjectType, OCXDTOValueType)
	 * @throws JIException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getType(int index) throws JIException{
		Object res = null;
		try {
			OCXDTOValueType vt = getValueType(index);
			vt.getListItemType();
			res = vt;
		} catch (JIException exception) { // let's play in exception
			OCXDTOObjectType ot = getObjectType(index);
			ot.isOpen();
			res = ot;
		}
		return (T) res;
	}
	
	/**
	 * Получает тип XDTO по имени. 
	 * @param <T> OCXDTOObjectType или OCXDTOValueType
	 * @param name Имя получаемого типа XDTO. 
	 * @return Может возвращаеть объекты двух типов. Рекомендуется в качестве generic-а использовать Object и далее instanceof. ТипЗначенияXDTO, ТипОбъектаXDTO. (OCXDTOObjectType, OCXDTOValueType) 
	 * @throws JIException
	 */
	@SuppressWarnings("unchecked")
	public <T> T getType(String name) throws JIException {
		Object res = null;
		try {
			OCXDTOValueType vt = getValueType(name);
			vt.getListItemType();
			res = vt;
		} catch (JIException exception) {
			OCXDTOObjectType ot = getObjectType(name);
			ot.isOpen();
			res = ot;
		}
		return (T) res;
	}
}
