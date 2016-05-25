/**
 * 
 */
package com.ipc.oce;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;


/**
 * Предназначен для последовательного чтения текстовых файлов (большой длины). 
 * @author Konovalov
 *
 */
public class OCTextReader extends OCObject {

	/**
	 * @param object
	 */
	public OCTextReader(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCTextReader(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCTextReader(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Закрывает открытый текстовый файл. Закрытие файла выполняется автоматически при удалении объекта ЧтениеТекста. 
	 * @throws JIException
	 */
	public void close() throws JIException {
		callMethod("Close");
	}
	
	/**
	 * Открывает (монопольно) текстовый файл для чтения. 
	 * @param fileName Имя читаемого файла. 
	 * @throws JIException
	 */
	public void open(String fileName) throws JIException {
		callMethod("Open", new JIVariant(fileName));
	}
	
	/**
	 * Открывает (монопольно) текстовый файл для чтения.
	 * @param fileName Имя читаемого файла. 
	 * @param encoding  Определяет кодировку текста читаемого текстового файла (согласован с текстовым документом). Если параметр не задан, формат текста будет определен автоматически по сигнатуре BOM в начале файла; если сигнатура BOM в файле отсутствует, файл будет открыт в кодировке ANSI.
	 * @throws JIException
	 */
	public void open(String fileName, String encoding) throws JIException {
		callMethod("Open", new Object[]{new JIVariant(fileName), new JIVariant(encoding)});
	}
	
	/**
	 * Открывает (монопольно) текстовый файл для чтения.
	 * @param fileName Имя читаемого файла. 
	 * @param encoding  Определяет кодировку текста читаемого текстового файла (согласован с текстовым документом). Если параметр не задан, формат текста будет определен автоматически по сигнатуре BOM в начале файла; если сигнатура BOM в файле отсутствует, файл будет открыт в кодировке ANSI.
	 * @param rowDelimeter Указывает строку, являющуюся разделителем строк в читаемом файле.
	 * @throws JIException
	 */
	public void open(String fileName, String encoding, String rowDelimeter) throws JIException {
		callMethod("Open", new Object[]{
					new JIVariant(fileName), 
					new JIVariant(encoding),
					new JIVariant(rowDelimeter)
				});
	}
	
	/**
	 * Считывает строку произвольной длины. Строка нулевой длины не является признаком конца файла. 
	 * @return
	 * @throws JIException
	 */
	public String read() throws JIException {
		return callMethodA("Read").getObjectAsString2();
	}
	
	/**
	 * Считывает строку произвольной длины и возвращает ее как InputStream.
	 * @return
	 * @throws JIException
	 */
	public InputStream readAsStream() throws JIException {
		return new ByteArrayInputStream(read().getBytes());
	}
	
	/**
	 * Считывает строку произвольной длины. Строка нулевой длины не является признаком конца файла.
	 * @param length Размер возвращаемой строки. 
	 * @return Строка, Неопределено. В случае, если читаемый файл завершен, то возвращается Неопределено. 
	 * @throws JIException
	 */
	public String read(int length) throws JIException {
		return callMethodA("Read", new Object[]{new JIVariant(length)})[0].getObjectAsString2();
	}
	
	/**
	 * Считывает строку текста, ограниченную разделителем. Строка нулевой длины не является признаком конца файла. 
	 * @return строка
	 * @throws JIException
	 */
	public String readLine() throws JIException {
		return callMethodA("ReadLine").getObjectAsString2();
	}
	
	/**
	 * Считывает строку текста, ограниченную разделителем. Строка нулевой длины не является признаком конца файла.
	 * @param delimeter разделитель строк 
	 * @return строка
	 * @throws JIException
	 */
	public String readLine(String delimeter) throws JIException {
		return callMethodA("ReadLine", new Object[]{new JIVariant(delimeter)})[0].getObjectAsString2();
	}
	
}
