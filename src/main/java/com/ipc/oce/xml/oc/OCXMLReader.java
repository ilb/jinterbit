/**
 * 
 */
package com.ipc.oce.xml.oc;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Предназначен для последовательного чтения XML-данных из файла или строки. Автоматически обрабатывает объявление XML-документа и распознает тип кодировки файла.
 * @author Konovalov
 *
 */
public class OCXMLReader extends OCObject {

	public OCXMLReader(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCXMLReader(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Устанавливает строку, содержащую текст XML для чтения данным объектом. Если перед вызовом данного метода уже производилось чтение XML из другого файла или строки, то чтение прекращается и объект инициализируется для чтения из указанной строки. 
	 * @param xmlData
	 * @throws JIException
	 */
	public void setString(String xmlData) throws JIException{
		callMethod("SetString", new Object[]{new JIVariant(xmlData)});
	}
	
	/**
	 * Открывает XML-файл для чтения данным объектом. Если перед вызовом данного метода уже производилось чтение XML из другого файла или строки, то чтение прекращается и объект инициализируется для чтения из указанного файла.
	 * ВНИМАНИЕ!! Это путь до файла на стороне 1С!!! 
	 * @param filePath
	 * @throws JIException
	 */
	public void openRemoteFile(String filePath) throws JIException{
		callMethod("OpenFile", new Object[]{new JIVariant(filePath)});
	}

}
