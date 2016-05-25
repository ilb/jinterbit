/**
 * 
 */
package com.ipc.oce.xml.oc;

import java.util.ArrayList;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCArray;
import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.varset.EXMLForm;
import com.ipc.oce.varset.EXMLTypeAssignment;

/**
 * Фабрика типов XDTO. Cодержит определения всех типов, позволяет осуществлять чтения/запись данных XDTO в XML
 * @author Konovalov
 *
 */
public class OCXDTOFactory extends OCObject {

	public OCXDTOFactory(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCXDTOFactory(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCXDTOFactory(OCObject object) {
		super(object);
	}
	
	/**
	 * Записывает указанный элемент данных XDTO в объект записи XML
	 * @param writer Объект записи XML
	 * @param object Записываемое значение OCXDTODataObject
	 * @param [localName] Локальное имя записываемого элемента данных
	 * @param [namespace_uri]  URI пространства имен записываемого элемента данных
	 * @param [form] Форма представления элемента данных в XDTO
	 * @param [assignment] Вариант назначения типа элемента данных XDTO
	 * @throws JIException
	 */
	public void writeXML(OCXMLWriter writer, OCXDTODataObject object, String localName, String namespaceURI, EXMLForm form, EXMLTypeAssignment assignment) throws JIException{
		writeXML(writer, (OCObject) object, localName, namespaceURI, form, assignment);
	}
	
	/**
	 * Записывает указанный элемент данных XDTO в объект записи XML
	 * @param writer Объект записи XML
	 * @param object Записываемое значение OCXDTODataValue
	 * @param [localName] Локальное имя записываемого элемента данных
	 * @param [namespace_uri]  URI пространства имен записываемого элемента данных
	 * @param [form] Форма представления элемента данных в XDTO
	 * @param [assignment] Вариант назначения типа элемента данных XDTO
	 * @throws JIException
	 */
	public void writeXML(OCXMLWriter writer, OCXDTODataValue value, String localName, String namespaceURI, EXMLForm form, EXMLTypeAssignment assignment) throws JIException{
		writeXML(writer, (OCObject) value, localName, namespaceURI, form, assignment);
	}
	
	private void writeXML(OCXMLWriter writer, OCObject object, String localName, String namespaceURI, EXMLForm form, EXMLTypeAssignment assignment) throws JIException{
		callMethod("WriteXML", new Object[]{
				ocObject2Dispatch(writer),
				ocObject2Dispatch(object),
				localName != null ? new JIVariant(localName) : null,
				namespaceURI != null ? new JIVariant(namespaceURI) : null,
				form != null ? ocObject2Dispatch(form) : null,
				assignment != null ? ocObject2Dispatch(assignment) : null
		});
	}
	
	/**
	 * Содержит коллекцию пакетов XDTO, составляющих фабрику.
	 * @return OCXDTOPackageCollection
	 * @throws JIException
	 */
	public OCXDTOPackageCollection getPackages() throws JIException{
		return new OCXDTOPackageCollection(get("Packages"));
	}
	
	/**
	 * Получение списка namespace URI пакетов XDTO.
	 * @return List<String>
	 * @throws JIException ошибка работы DCOM
	 */
	public final List<String> getNamespacesURI() throws JIException {
	
		OCXDTOPackageCollection pacCollection = getPackages();
		int pacColSZ = pacCollection.size();
		
		List<String> res = new ArrayList<String>(pacColSZ);
		
		for (int z = 0; z < pacColSZ; z++) {
			OCXDTOPackage package1 = pacCollection.getPackage(z);
			res.add(package1.getNamespaceURI());
		}
		
		return res;
	}
	
	public String getCurrentConfigURI() throws JIException {
		OCXDTOPackageCollection pacCollection = getPackages();
		for (OCXDTOPackage package1 : pacCollection) {
			String uri = package1.getNamespaceURI();
			if (uri.endsWith("current-config")) {
				return uri;
			}
		}
		return null;
	}
	
	/**
	 * Выполняет экспорт пакетов XDTO, имеющих указанные URI пространства имен в набор схем XML. 
	 * @param uriNamespace  строка, соответствующая URI пространства имен экспортируемых пакетов. 
	 * @return XMLSchemaSet
	 * @throws JIException
	 */
	public OCXMLSchemaSet exportXMLSchema(String uriNamespace) throws JIException{
		return new OCXMLSchemaSet(callMethodA("ExportXMLSchema", new Object[]{new JIVariant(uriNamespace)})[0]);
	}
	
	/**
	 * Выполняет экспорт пакетов XDTO, имеющих указанные URI пространства имен в набор схем XML. 
	 * @param uriNamespaces массив, соответствующая URI пространства имен экспортируемых пакетов. 
	 * @return OCXMLSchemaSet
	 * @throws JIException
	 */
	public OCXMLSchemaSet exportXMLSchema(OCArray uriNamespaces) throws JIException{
		return new OCXMLSchemaSet(callMethodA("ExportXMLSchema", new Object[]{new JIVariant(ocObject2Dispatch(uriNamespaces))})[0]);
	}
	
	/**
	 * Выполняет экспорт пакетов XDTO, имеющих указанные URI пространства имен в набор схем XML. 
	 * @param uriNamespaces массив, соответствующая URI пространства имен экспортируемых пакетов. 
	 * @return OCXMLSchemaSet
	 * @throws JIException
	 */
	public OCXMLSchemaSet exportXMLSchema(String[] uriNamespaces) throws JIException{
		OCArray array = OCApp.getInstance(getAssociatedSessionID()).newArray();
		if (uriNamespaces != null) {
			for (String element : uriNamespaces) {
				array.add(new OCVariant(element));
			}
		}
		return exportXMLSchema(array);
	}
	
	/**
	 * Выполняет экспорт пакетов XDTO, имеющих указанные URI пространства имен в модели XDTO 
	 * @param namespace Строка, соответствующая URI пространства имен экспортируемых пакетов. 
	 * @return OCXDTODataObject
	 * @throws JIException
	 */
	public OCXDTODataObject exportXDTOModel(String namespace) throws JIException{
		return new OCXDTODataObject(callMethodA("ExportXDTOModel", new Object[]{new JIVariant(namespace)})[0]);
	}
	
	/**
	 * Выполняет экспорт пакетов XDTO, имеющих указанные URI пространства имен в модели XDTO 
	 * @param Массив строк, соответствующая URI пространства имен экспортируемых пакетов. 
	 * @return OCXDTODataObject
	 * @throws JIException
	 */
	public OCXDTODataObject exportXDTOModel(OCArray namespaces) throws JIException{
		return new OCXDTODataObject(callMethodA("ExportXDTOModel", new Object[]{ocObject2Dispatch(namespaces)})[0]);
	}
	
	/**
	 * Получение типа XDTO по имени и URI пространства имен
	 * @param URI пространства имен
	 * @param name
	 * @return OCXDTOValueType
	 * @throws JIException
	 */
	public OCXDTOValueType createValueType(String namespaceURI, String name) throws JIException{
		return new OCXDTOValueType(callMethodA("Type", new Object[] {
				new JIVariant(namespaceURI), new JIVariant(name) })[0]);
	}
	
	/**
	 * Получение типа XDTO по имени и URI пространства имен
	 * @param URI пространства имен
	 * @param name
	 * @return OCXDTOObjectType
	 * @throws JIException
	 */
	public OCXDTOObjectType createObjectType(String namespaceURI, String name) throws JIException{
		return new OCXDTOObjectType(callMethodA("Type", new Object[] {
				new JIVariant(namespaceURI), new JIVariant(name) })[0]);
	}
	
	/**
	 * Создание объекта, указанного типа
	 * @param Тип, объект которого необходимо создать
	 * @return OCXDTODataValue
	 * @throws JIException
	 */
	public OCXDTODataObject createDataObject(OCXDTOObjectType objectType) throws JIException{
		return new OCXDTODataObject(callMethodA("Create", new Object[]{ocObject2Dispatch(objectType)})[0]);
	}
	
	/**
	 * Создает значение XDTO по лексическому представлению значения
	 * @param valueType - Тип, значение которого необходимо создать
	 * @param lexValue - Лексическое представление значения
	 * @return OCXDTODataValue
	 * @throws JIException
	 */
	public OCXDTODataValue createDataValue(OCXDTOValueType valueType, String lexValue) throws JIException{
		return new OCXDTODataValue(callMethodA("Create", new Object[]{ocObject2Dispatch(valueType), new JIVariant(lexValue)})[0]);
	}

}
