/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Используется для доступа к колонтитулам табличного документа. Колонтитул является специальным текстом, выводимым вверху или внизу каждой страницы при выводе документа на печать. В тексте колонтитула можно использовать следующие управляющие конструкции: 
[&НомерСтраницы] ([&PageNumber]) - при печати в данном месте будет выведен номер страницы, 
[&СтраницВсего] ([&PagesTotal]) - при печати в данном месте будет выведено общее количество страниц, 
[&Дата] ([&Date]) - при печати в данном месте будет выведена текущая дата, 
[&Время] ([&Time]) - при печати в данном месте будет выведено текущее время.

 * @author Konovalov
 *
 */
public class OCSpreadsheetDocumentHeaderFooter extends OCObject {

	/**
	 * @param object
	 */
	public OCSpreadsheetDocumentHeaderFooter(OCObject object) {
		super(object);

	}

	/**
	 * @param aDispatch
	 */
	public OCSpreadsheetDocumentHeaderFooter(IJIDispatch aDispatch) {
		super(aDispatch);

	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCSpreadsheetDocumentHeaderFooter(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);

	}
	
	/**
	 * Содержит признак вывода колонтитула на печать. 
	 * @return
	 * @throws JIException
	 */
	public boolean isEnabled() throws JIException {
		return get("Enabled").getObjectAsBoolean();
	}
	
	/**
	 * Содержит признак вывода колонтитула на печать. 
	 * @param enabled
	 * @throws JIException
	 */
	public void setEnabled(boolean enabled) throws JIException {
		put("Enabled", new JIVariant(enabled));
	}
	
	/**
	 * Содержит номер страницы, начиная с которой будет выводиться колонтитул. 
	 * @return
	 * @throws JIException
	 */
	public int getStartPage() throws JIException {
		return get("StartPage").getObjectAsInt();
	}
	
	/**
	 * Содержит номер страницы, начиная с которой будет выводиться колонтитул. 
	 * @param index
	 * @throws JIException
	 */
	public void setStartPage(int index) throws JIException {
		put("StartPage", new JIVariant(index));
	}

	/**
	 * Содержит текст в центре колонтитула.
	 * @return
	 * @throws JIException
	 */
	public String getCenterText() throws JIException {
		return get("CenterText").getObjectAsString2();
	}
	
	/**
	 * Содержит текст в центре колонтитула.
	 * @param text
	 * @throws JIException
	 */
	public void setCenterText(String text) throws JIException {
		put("CenterText", new JIVariant(text));
	}
	
	/**
	 * Содержит текст в левой части колонтитула. 
	 * @return
	 * @throws JIException
	 */
	public String getLeftText() throws JIException {
		return get("LeftText").getObjectAsString2();
	}
	
	/**
	 * Содержит текст в левой части колонтитула. 
	 * @param text
	 * @throws JIException
	 */
	public void setLeftText(String text) throws JIException {
		put("LeftText", new JIVariant(text));
	}
	
	/**
	 * Содержит текст в правой части колонтитула. 
	 * @return
	 * @throws JIException
	 */
	public String getRightText() throws JIException {
		return get("RightText").getObjectAsString2();
	}
	
	/**
	 * Содержит текст в правой части колонтитула. 
	 * @param text
	 * @throws JIException
	 */
	public void setRightText(String text) throws JIException {
		put("RightText", new JIVariant(text));
	}
	
	public String getVerticalAlign() throws JIException {
		return (new OCObject(get("VerticalAlign"))).toString();
	}
	
}
