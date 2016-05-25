/**
 * 
 */
package com.ipc.oce.xml.xerces;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Konovalov
 * 
 */
public final class Utils {

	/**
	 * no instances. 
	 */
	private Utils() {
		
	}
	
	/**
	 * Преобразование W3C Document (XML) в строковое представление.
	 * @param document - W3C org.w3c.dom.Document
	 * @return строковое представление XML-документа
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public static String document2XML(Document document) throws TransformerException {
		return document2Writer(document).toString();
	}
	
	/**
	 * Преобразование W3C Document (XML) в java.io.Writer .
	 * @param org.w3c.dom.Document
	 * @return java.io.Writer
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public static Writer document2Writer(Document document) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		// initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(document);
		transformer.transform(source, result);

		return result.getWriter();
	}
	
	/**
	 * Перобразование строкового представления XML в объект W3C Document
	 * @param stringXML строка XML
	 * @return Document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document xml2Document(String stringXML) throws ParserConfigurationException, SAXException, IOException {
		InputStream pStm = new ByteArrayInputStream(stringXML.getBytes("UTF-8"));
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(pStm);

	}
	
	/**
	 * Вычисление контрольной суммы от массива байт (CRC32).
	 * @param bytes входной массив байт
	 * @return значение контрольной суммы
	 */
	public static long getCRC32(byte[] bytes) {
		Checksum crc32 = new CRC32();
		crc32.update(bytes, 0, bytes.length);
		return crc32.getValue();
	}

}
