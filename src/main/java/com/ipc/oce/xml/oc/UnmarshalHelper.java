/**
 * 
 */
package com.ipc.oce.xml.oc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.objects.OCUUID;

/**
 * Helper класс для преобразования xml данных в объекты 1С
 * @author Konovalov
 * @see MarshalHelper
 *
 */
public class UnmarshalHelper {
	
	private static final transient Log LOG = LogFactory.getLog(UnmarshalHelper.class);
	
	private OCApp appInstance = null;

	/**
	 * Конструктов
	 * @param appInstance экземпляр активного контекста 1С
	 */
	public UnmarshalHelper(OCApp appInstance) {
		super();
		this.appInstance = appInstance;
	}
	
	/**
	 * Преобразование xml строки в объект 1С.
	 * @param xmlData страка с xml
	 * @return объект 1С
	 * @throws JIException
	 * @throws TransformerException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public OCObject xml2object(String xmlData) throws JIException, ParserConfigurationException, SAXException, IOException, TransformerException{
		return xml2object(xmlData, false);
	}
	
	/**
	 * Преобразование xml строки в объект 1С.
	 * @param xmlData  страка с xml
	 * @param preventEmptyRef Если True по пустой тэг Ref заполняется новым UUID из 1С (используется для создания новых объектов 1С из xml), false - пропускает xml в исходном виде
	 * @return объект 1С
	 * @throws JIException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public OCObject xml2object(String xmlData, Boolean preventEmptyRef) throws JIException, ParserConfigurationException, SAXException, IOException, TransformerException{
		if (preventEmptyRef) {
			Document w3cDocument = com.ipc.oce.xml.xerces.Utils.xml2Document(xmlData);
			NodeList nodeList = w3cDocument.getElementsByTagName("Ref");
			if (nodeList != null && nodeList.getLength() == 1) { // only one Ref tag
				Node node = nodeList.item(0);
				if (node.getTextContent() == null || node.getTextContent().trim().length() == 0) {
					OCUUID uuid = appInstance.createUUID();		
					LOG.info("Empty Ref UUID detected. New UUID generated " + uuid.toString());
					node.setTextContent(uuid.toString());
				}
			} else {
				throw new SAXException("Tag Ref not found or encounted more then once. Set Ref manually or use preventEmptyRef");
			}
			xmlData = com.ipc.oce.xml.xerces.Utils.document2XML(w3cDocument);
		}
		OCXMLReader xmlReader = appInstance.newXMLReader();
		xmlReader.setString(xmlData);
		OCObject object = appInstance.readXML(xmlReader);
		return object;
	}
	
	/**
	 * Чтение локальных данных через InputStream
	 * @param is возодной поток с xml данными
	 * @throws IOException
	 * @throws JIException 
	 * @throws TransformerException 
	 * @throws TransformerFactoryConfigurationError 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public OCObject xml2object(InputStream is) throws IOException, JIException, ParserConfigurationException, SAXException, TransformerException{
		return xml2object(is, false);
	}
	
	/**
	 * Чтение локальных данных через InputStream
	 * @param is is возодной поток с xml данными
	 * @param preventEmptyRef Если True по пустой тэг Ref заполняется новым UUID из 1С (используется для создания новых объектов 1С из xml), false - пропускает xml в исходном виде
	 * @return объект 1С
	 * @throws IOException
	 * @throws JIException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public OCObject xml2object(InputStream is, Boolean preventEmptyRef) throws IOException, JIException, ParserConfigurationException, SAXException, TransformerException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		int readed = 0;
		byte[] buffer = new byte[128];
		while ((readed = is.read(buffer)) != -1) {
			baos.write(buffer, 0, readed);
		}
		System.out.println(baos.toString());
		return xml2object(baos.toString(), preventEmptyRef);
	}
	
}
