/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.varset.EXMLForm;
import com.ipc.oce.varset.EXMLTypeAssignment;

/**
 * Сериализатор значений 8.1 в XML на основе XDTO. 
 * @author Konovalov
 *
 */
public class OCXDTOSerializer extends OCObject {

	/**
	 * @param object
	 */
	public OCXDTOSerializer(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCXDTOSerializer(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCXDTOSerializer(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Фабрика типов XDTO. 
	 * @return
	 * @throws JIException
	 */
	public OCXDTOFactory getFactory() throws JIException{
		return new OCXDTOFactory(get("Factory"));
	}
	
	/**
	 * Получает XML представление значения для помещения в текст элемента или значение атрибута XML
	 * @param object
	 * @return
	 * @throws JIException
	 */
	public String xmlString(Object object) throws JIException{
		if (object instanceof OCObject) {
			OCObject oco = (OCObject) object;
			return callMethodA(
					"XMLString", 
					new Object[]{ocObject2Dispatch(oco)}
					)[0].getObjectAsString2();
		} else {
			return callMethodA("XMLString", new Object[]{JIVariant.makeVariant(object)})[0].getObjectAsString2();
		}
	}
	
	/**
	 * Производит проверку возможности чтения из объекта ЧтениеXML значения. 
	 * @param reader Объект, через который производится чтение XML
	 * @return  
	 * Истина - тип 1С:Предприятия существует; 
	 * Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean canReadXML(OCXMLReader reader) throws JIException {
		return callMethodA("CanReadXML", new Object[]{
				ocObject2Dispatch(reader)
				})[0].getObjectAsBoolean();
	}
	
	
	/**
	 * Записывает значения в формате XML без указания имени элемента. В качестве имени элемента будет использован тип значения
	 * @param writer  Объект, через который осуществляется запись XML
	 * @param object Записываемое в поток XML значение. Тип параметра определяется совокупностью типов, для которых определена XML-сериализация. 
	 * @throws JIException
	 */
	public void writeXML(OCXMLWriter writer, OCObject object) throws JIException{ 
		callMethod("WriteXML", new Object[]{new JIVariant(ocObject2Dispatch(writer)), new JIVariant(ocObject2Dispatch(object))});
	}
	
	/**
	 * Записывает значения в формате XML без указания имени элемента. В качестве имени элемента будет использован тип значения
	 * @param writer Объект, через который осуществляется запись XML.
	 * @param object Записываемое в поток XML значение. Тип параметра определяется совокупностью типов, для которых определена XML-сериализация
	 * @param assignment Определяет необходимость назначения типа элементу XML. Значение по умолчанию: Неявное 
	 * @param form Форма записи элемента данных в XML
	 * @throws JIException
	 */
	public void writeXML(OCXMLWriter writer, OCObject object, EXMLTypeAssignment assignment, EXMLForm form) throws JIException{ 
		callMethod("WriteXML", new Object[] {
				new JIVariant(ocObject2Dispatch(writer)),
				new JIVariant(ocObject2Dispatch(object)),
				assignment != null ? ocObject2Dispatch(assignment) : null,
				form != null ? ocObject2Dispatch(form) : null });
	}
	
	/**
	 * Записывает значения в формате XML с указанием полного имени элемента. 
	 * @param writer Объект, через который осуществляется запись XML
	 * @param object Записываемое в поток XML значение. Тип параметра определяется совокупностью типов, для которых определена XML-сериализация. 
	 * @param fullname  Полное имя элемента XML, в который будет записано значение
	 * @throws JIException
	 */
	public void writeXML(OCXMLWriter writer, OCObject object, String fullname) throws JIException{ 
		callMethod("WriteXML", new Object[]{new JIVariant(ocObject2Dispatch(writer)), new JIVariant(ocObject2Dispatch(object)), new JIVariant(fullname)});
	}
	
	/**
	 * Записывает значения в формате XML с указанием локального имени элемента и пространства имен, к которому принадлежит локальное имя.
	 * @param writer Объект, через который осуществляется запись XML
	 * @param object Записываемое в поток XML значение. Тип параметра определяется совокупностью типов, для которых определена XML-сериализация
	 * @param localname Локальное имя элемента XML, в который будет записано значение
	 * @param uriNS URI пространства имен, к которому принадлежит указанное ЛокальноеИмя. 
	 * @throws JIException 
	 */
	public void writeXML(OCXMLWriter writer, OCObject object, String localname, String uriNS) throws JIException{ 
		callMethod("WriteXML", new Object[]{new JIVariant(ocObject2Dispatch(writer)), new JIVariant(ocObject2Dispatch(object)), new JIVariant(localname), new JIVariant(uriNS)});
	}
	
	/**
	 * Метод использует внутренний OCXMLWriter с директивой UTF-8.
	 * @param object объект для сериализации
	 * @return строковое представление XML.
	 * @throws JIException
	 */
	public String writeXML(OCObject object) throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		OCXMLWriter writer = app.newXMLWriter();
		writer.setString("UTF-8");
		writeXML(writer, object);
		return writer.close();
	}
	
	/**
	 * Производит запись значения в объект XDTO. Для типа записываемого значения должно быть преобразование в XDTO
	 * @param object Произвольный. Значение, которое должно быть записано в XDTO объект
	 * @return OCXDTODataObject
	 * @throws JIException
	 */
	public OCXDTODataObject writeXDTO(OCObject object) throws JIException{
		return new OCXDTODataObject(callMethodA("WriteXDTO", new Object[]{new JIVariant(ocObject2Dispatch(object))})[0]);
	}
	
	/**
	 * Производит чтение значения в формате XML. Прочитаны могут значения тех типов, которые могут быть записаны методом ЗаписатьXML. 
	 * @param reader Объект, через который происходит чтение XML
	 * @return Значение считанного типа
	 * @throws JIException
	 */
	public OCObject readXML(OCXMLReader reader) throws JIException{
		return new OCObject(callMethodA("ReadXML", new Object[]{ocObject2Dispatch(reader)})[0]);
	}
	
	/**
	 * Производит чтение значения в формате XML. Прочитаны могут значения тех типов, которые могут быть записаны методом ЗаписатьXML. 
	 * @param reader Объект, через который происходит чтение XML
	 * @param type Тип значения, которое должно быть прочитано из XML. Если тип не указан, то будет произведена попытка определения типа значения непосредственно из представления XML. Если тип не указан и установить его не представляется возможным, или считываемые данные не соответствуют значению типа, то будет вызвано исключение. 
	 * @return Значение считанного типа
	 * @throws JIException
	 */
	public OCObject readXML(OCXMLReader reader, OCType type) throws JIException{
		return new OCObject(callMethodA("ReadXML", new Object[] {
				ocObject2Dispatch(reader),
				type != null ? ocObject2Dispatch(type) : null })[0]);
	}
	
	/**
	 * Метод использует внутренний OCXMLReader
	 * @param xml - строка XML.
	 * @return OCObject
	 * @throws JIException
	 */
	public OCObject readXML(String xml) throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		OCXMLReader reader = app.newXMLReader();
		reader.setString(xml);
		return readXML(reader);
	}
	
	/**
	 * Производит чтение объекта XDTO в значение, используя соответствующее преобразование типа XDTO в тип платформы
	 * @param dataObject Объект XDTO.
	 * @return Произвольный
	 * @throws JIException
	 */
	public OCVariant readXDTO(OCXDTODataObject dataObject) throws JIException{
		return new OCVariant(callMethodA("ReadXDTO", new Object[]{ocObject2Dispatch(dataObject)})[0]);
	}

}
