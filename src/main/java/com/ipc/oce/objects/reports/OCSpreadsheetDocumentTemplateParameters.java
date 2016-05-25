/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;

/**
 * Представляет собой коллекцию параметров, используемых в ячейках макета табличного документа. 
 * @author Konovalov
 *
 */
public class OCSpreadsheetDocumentTemplateParameters extends OCObject {

	/**
	 * @param object
	 */
	public OCSpreadsheetDocumentTemplateParameters(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCSpreadsheetDocumentTemplateParameters(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCSpreadsheetDocumentTemplateParameters(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получает количество параметров в коллекции. 
	 * @return int
	 * @throws JIException
	 */
	public int size() throws JIException {
		return callMethodA("Count").getObjectAsInt();
	}
	
	/**
	 * Получает значение по имени.
	 * @param name - имя параметра.
	 * @return OCVariant, Произвольный
	 * @throws JIException
	 */
	public OCVariant getParameter(String name) throws JIException {
		return new OCVariant(get(name));
	}
	
	/**
	 * Устанавливает значение по имени.
	 * @param name имя параметра.
	 * @param variant Устанавливаемое значение.
	 * @throws JIException
	 */
	public void setParameter(String name, OCVariant variant) throws JIException {
		put(name, variant);
	}
	
	/**
	 * Получает значение по индексу.
	 * @param index - Индекс параметра.
	 * @return OCVariant, Произвольный
	 * @throws JIException
	 */
	public OCVariant getParameter(int index) throws JIException {
		return new OCVariant(callMethodA("Get", new JIVariant(index))[0]);
	}
	
	/**
	 * Устанавливает значение по индексу.
	 * @param index Индекс параметра.
	 * @param variant Устанавливаемое значение.
	 * @throws JIException
	 */
	public void setParameter(int index, OCVariant variant) throws JIException {
		callMethod("Set", new Object[]{new JIVariant(index), ocVariant2JI(variant)});
	}

}
