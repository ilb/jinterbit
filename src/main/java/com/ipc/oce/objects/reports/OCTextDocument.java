/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Текстовый документ предназначен для работы с текстами. Объект позволяет
 * получать и сохранять текст в файле, работать со строками, открывать текст в
 * текстовом редакторе 1С:Предприятия.
 * 
 * @author Konovalov
 * 
 */
public class OCTextDocument extends CommonTemplate {

	/**
	 * @param object
	 */
	public OCTextDocument(OCObject object) {
		super(object);

	}

	/**
	 * @param aDispatch
	 */
	public OCTextDocument(IJIDispatch aDispatch) {
		super(aDispatch);

	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCTextDocument(JIVariant aDispatch) throws JIException {
		super(aDispatch);

	}
	
	/**
	 * Определяет разделитель строк. Может принимать только следующие значения:
	 * ВК, ПС или ВК+ПС. При попытке установки других значений будет
	 * инициировано исключение.
	 * 
	 * @return String Значение по умолчанию ПС.
	 * @throws JIException
	 */
	public String getLineSeparator() throws JIException {
		return get("LineSeparator").getObjectAsString2();
	}

}
