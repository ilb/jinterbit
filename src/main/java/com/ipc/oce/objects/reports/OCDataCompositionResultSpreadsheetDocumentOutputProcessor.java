/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCDataCompositionResultSpreadsheetDocumentOutputProcessor extends
		OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionResultSpreadsheetDocumentOutputProcessor(
			OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionResultSpreadsheetDocumentOutputProcessor(
			IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionResultSpreadsheetDocumentOutputProcessor(
			JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Устанавливает табличный документ, в который нужно выводить результат.
	 * @param document - Документ, в который будет осуществляться вывод.
	 * @throws JIException
	 */
	public void setDocument(OCSpreadsheetDocument document) throws JIException {
		callMethod("SetDocument", 
				document != null ? new JIVariant(ocObject2Dispatch(document)) : null
		);
	}
	
	/**
	 * Устанавливает табличный документ, в который нужно выводить результат.
	 * @throws JIException
	 */
	public void setDocument() throws JIException {
		setDocument(null);
	}
	
	/**
	 * Выводит весь результат в объект. При этом автоматически исполняется метод
	 * НачатьВывод, перебираются все элементы процессора компоновки и выводятся
	 * в результат. После чего исполняется метод ЗакончитьВывод, результат
	 * которого возвращается данным методом.
	 * 
	 * @param compositionProcessor - Процессор компоновки данных, из которого будут получаться элементы результата. 
	 * @return OCSpreadsheetDocument
	 * @throws JIException
	 */
	public OCSpreadsheetDocument output(OCDataCompositionProcessor compositionProcessor) throws JIException {
		return new OCSpreadsheetDocument(callMethodA("Output", new Object[]{
				new JIVariant(ocObject2Dispatch(compositionProcessor)),
				new JIVariant(false)
		})[0]);
	}

}
