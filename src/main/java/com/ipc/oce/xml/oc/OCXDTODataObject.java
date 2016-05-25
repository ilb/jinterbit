/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.varset.EXMLForm;

/**
 * Объект данных модели XDTO
 * @author Konovalov
 *
 */
public class OCXDTODataObject extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTODataObject(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTODataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTODataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает объект-владелец данного объекта. 
	 * @return OCXDTODataObject или null если owner отсутствует
	 * @throws JIException
	 */
	public OCXDTODataObject getOwner() throws JIException{
		try {
			return new OCXDTODataObject(callMethodA("Owner"));
		} catch (IllegalStateException ise) {
			return null;
		}
	}
	
	/**
	 * Получает свойство объекта-владельца, владеющее данным объектом
	 * @return
	 * @throws JIException
	 */
	public OCXDTOProperty getOwningProperty() throws JIException{
		try {
			return new OCXDTOProperty(callMethodA("OwningProperty"));
		} catch (IllegalStateException ise) {
			return null;
		}
	}
	
	/**
	 * Установка значения нового произвольного свойства. Добавляет значение произвольного свойства. Имя свойства будет создано автоматически, на основе локального имени XML представления свойства.
	 * В случае, если произвольное свойство с полученным именем уже существует и, если предыдущая установка значения произвольного свойства выполнялась для данного произвольного свойства, либо другого свойства в случае наличия последовательности, - свойство становится списковым с неограниченной верхней границей. 
	 * В противном случае будет инициировано исключение.
	 * Тип создаваемого свойства всегда устанавливается anyType пространства имен схемы XML. 
	 * Вызов метода может быть осуществлен только для объектов типов с установленным свойством Открытый, в противном случае будет инициировано исключение. 
	 * @param Форма XML представления произвольного свойства 
	 * @param URI пространства имен XML представления произвольного свойства. 
	 * @param Локальное имя произвольного свойства
	 * @param Элемент данных XDTO (<b>значение</b>), являющийся значением произвольного свойства
	 * @throws JIException 
	 */
	public void addValue(EXMLForm form, String namespaceURI, String localname, OCXDTODataValue dataValue) throws JIException{
		callMethod("Add", new Object[]{
				ocObject2Dispatch(form),
				new JIVariant(namespaceURI),
				new JIVariant(localname),
				ocObject2Dispatch(dataValue)
		});
	}
	
	/**
	 * Установка значения нового произвольного свойства. Добавляет значение произвольного свойства. Имя свойства будет создано автоматически, на основе локального имени XML представления свойства.
	 * В случае, если произвольное свойство с полученным именем уже существует и, если предыдущая установка значения произвольного свойства выполнялась для данного произвольного свойства, либо другого свойства в случае наличия последовательности, - свойство становится списковым с неограниченной верхней границей. 
	 * В противном случае будет инициировано исключение.
	 * Тип создаваемого свойства всегда устанавливается anyType пространства имен схемы XML. 
	 * Вызов метода может быть осуществлен только для объектов типов с установленным свойством Открытый, в противном случае будет инициировано исключение. 
	 * @param Форма XML представления произвольного свойства 
	 * @param URI пространства имен XML представления произвольного свойства. 
	 * @param Локальное имя произвольного свойства
	 * @param Элемент данных XDTO (<b>объект</b>), являющийся значением произвольного свойства
	 * @throws JIException 
	 */
	public void addValue(EXMLForm form, String namespaceURI, String localname, OCXDTODataObject dataObject) throws JIException{
		callMethod("Add", new Object[]{
				ocObject2Dispatch(form),
				new JIVariant(namespaceURI),
				new JIVariant(localname),
				ocObject2Dispatch(dataObject)
		});
	}
	
	/**
	 * Установка значения существующего произвольного свойства. Добавляет значение произвольного свойства с указанным именем.
	 * Если свойства с указанным именем не существует, то будет инициировано исключение.
	 * Если предыдущая установка значения произвольного свойства выполнялась для данного произвольного свойства, либо другого свойства в случае наличия последовательности, - свойство становится списковым с неограниченной верхней границей. 
	 * В противном случае будет инициировано исключение. 
	 * Вызов метода может быть осуществлен только для объектов типов с установленным свойством Открытый, в противном случае будет инициировано исключение. 
	 * @param Имя произвольного свойства
	 * @param Элемент данных XDTO (значение), являющийся значением произвольного свойства. 
	 * @throws JIException
	 */
	public void addValue(String elementName, OCXDTODataValue dataValue) throws JIException{
		callMethod("Add", new Object[]{new JIVariant(elementName), ocObject2Dispatch(dataValue)});
	}
	
	/**
	 * Установка значения существующего произвольного свойства. Добавляет значение произвольного свойства с указанным именем.
	 * Если свойства с указанным именем не существует, то будет инициировано исключение.
	 * Если предыдущая установка значения произвольного свойства выполнялась для данного произвольного свойства, либо другого свойства в случае наличия последовательности, - свойство становится списковым с неограниченной верхней границей. 
	 * В противном случае будет инициировано исключение. 
	 * Вызов метода может быть осуществлен только для объектов типов с установленным свойством Открытый, в противном случае будет инициировано исключение. 
	 * @param Имя произвольного свойства
	 * @param Элемент данных XDTO (объект), являющийся значением произвольного свойства. 
	 * @throws JIException
	 */
	public void addValue(String elementName, OCXDTODataObject dataObject) throws JIException{
		callMethod("Add", new Object[]{new JIVariant(elementName), ocObject2Dispatch(dataObject)});
	}
	
	/**
	 * Установка свойств значения свойств объекта XDTO. Конкретный состав свойств зависит от типа объекта метаданных
	 * @param propertieName Имя устанавливаемого свойства
	 * @param dataObject OCXDTODataObject
	 * @throws JIException
	 */
	public void setValue(String propertieName, OCXDTODataObject dataObject) throws JIException{
		put(propertieName, new JIVariant(ocObject2Dispatch(dataObject)));
	}
	
	/**
	 * Установка свойств значения свойств объекта XDTO. Конкретный состав свойств зависит от типа объекта метаданных
	 * @param propertieName Имя устанавливаемого свойства
	 * @param object произвольный объект
	 * @throws JIException
	 */
	public void setValue(String propertieName, OCVariant object) throws JIException{
		put(propertieName, ocVariant2JI(object));
	}
	
	/**
	 * Получение значения по свойству
	 * @param propName OCXDTOProperty
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValue(OCXDTOProperty propName) throws JIException{
		return new OCVariant(callMethodA("Get", new Object[]{ocObject2Dispatch(propName)})[0]);
	}
	
	/**
	 * Получение по get
	 * @param propName
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getValue(String propName) throws JIException{
		return new OCVariant(get(propName));
	}
	
	/**
	 * Получает значение свойства по выражению XPath. 
	 * @param xpath Строка-выражение XPath для получения значения свойства
	 * @return
	 * @throws JIException
	 */
	public OCVariant getValueForXPath(String xpath) throws JIException{
		return new OCVariant(callMethodA("Get", new Object[]{new JIVariant(xpath)})[0]);
	}
	
	// TODO надо доделать, пока все что хотел от xsdo получил
	
}
