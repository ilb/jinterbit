/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Значение содержит двоичные данные, которые считываются из файла. Значение
 * может быть сохранено в ХранилищеЗначения. Хранимые данные могут быть записаны
 * в файл. Сериализуется. XML-сериализация. Поддержка отображения в XDTO;
 * пространство имен: {http://www.w3.org/2001/XMLSchema}. Имя типа XDTO: может
 * быть любым из перечисленных: base64Binary, hexBinary.
 * 
 * @author Konovalov
 * 
 */
public class OCBinaryData extends OCObject {
	
	private String fileName = null;

	/**
	 * @param object
	 */
	public OCBinaryData(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCBinaryData(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCBinaryData(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public String getFileName() {
		return fileName;
	}

	protected void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Записывает двоичные данные в файл.
	 * 
	 * @param fileName
	 *            - Имя файла, в который необходимо записать двоичные данные.
	 *            Если файл существует, он будет перезаписан, иначе - создан.
	 * @throws JIException
	 */
	public void write(String fileName) throws JIException {
		callMethod("Write", new JIVariant(fileName));
	}
	
	/**
	 * Получает из двоичных данных строку, закодированную по алгоритму base64.
	 * @return
	 * @throws JIException
	 */
	public String base64String() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).base64String(this);
	}

}
