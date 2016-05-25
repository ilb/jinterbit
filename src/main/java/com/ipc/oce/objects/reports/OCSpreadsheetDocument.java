/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.StaticFieldInstance;

/**
 * Используется для доступа к свойствам и методам табличного документа в целом.
 * При помощи этого объекта возможно управление общими характеристиками
 * табличного документа, получение его областей, а также присоединение к
 * табличному документу других табличных документов, группировка строк и колонок
 * табличного документа. Для табличного документа системой устанавливается язык
 * по умолчанию.
 * 
 * @author Konovalov
 * 
 */
public class OCSpreadsheetDocument extends CommonTemplate {

	/**
	 * @param object
	 */
	public OCSpreadsheetDocument(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCSpreadsheetDocument(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCSpreadsheetDocument(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Очищает табличный документ. 
	 * @throws JIException
	 */
	public void clear() throws JIException {
		callMethod("Clear");
	}
	
	/**
	 * Содержит верхний колонтитул табличного документа.
	 * @return OCSpreadsheetDocumentHeaderFooter
	 * @throws JIException
	 */
	public OCSpreadsheetDocumentHeaderFooter getHeader() throws JIException {
		return new OCSpreadsheetDocumentHeaderFooter(get("Header"));
	}
	
	/**
	 * Используется для получения области табличного документа по имени. 
	 * @param name -  Имя области или адрес в формате "R1C1:R2C2", где число после "R" обозначает номер строки, число после "C" - номер колонки, символом ":" (двоеточие) разделены координаты левого верхнего и правого нижнего угла области. В качестве имени также можно передавать пересечение двух областей, записываемое как "<Имя области 1> | <Имя области 2>". 
	 * @return OCSpreadsheetDocument Область, как новый табличный документ. 
	 * @throws JIException
	 */
	public OCSpreadsheetDocument getArea(String name) throws JIException {
		return new OCSpreadsheetDocument(callMethodA("GetArea", new JIVariant(name))[0]);
	}
	
	/**
	 * Содержит коллекцию параметров макета табличного документа.
	 * @return OCSpreadsheetDocumentTemplateParameters
	 * @throws JIException
	 */
	public OCSpreadsheetDocumentTemplateParameters getParameters() throws JIException {
		return new OCSpreadsheetDocumentTemplateParameters(get("Parameters"));
	}
	
	private void write(String filePath, String fileType) throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		StaticFieldInstance mode = app.getStaticFields(fileType);
		write(filePath, mode);
	}
	
	public void write(String filePath, StaticFieldInstance mode) throws JIException {
		callMethod("Write", new Object[]{
				new JIVariant(filePath),
				mode != null ?new JIVariant(ocObject2Dispatch(mode)) : null
		});
	}
	
	public void writeXLS(String filePath) throws JIException {
		write(filePath, "SpreadsheetDocumentFileType.XLS");
	}
	
	public void writeTXT(String filePath) throws JIException {
		write(filePath, "SpreadsheetDocumentFileType.TXT");
	}
	
	public void writeMXL(String filePath) throws JIException {
		write(filePath, (StaticFieldInstance)null);
	}
}
