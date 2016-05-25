/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.metadata.OCTypeDescription;

/**
 * @author Konovalov
 *
 */
public class OCValueTableColumn extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTableColumn(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCValueTableColumn(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTableColumn(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит заголовок колонки таблицы значений. Используется при визуальном
	 * отображении таблицы значений.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getTitle() throws JIException {
		return get("Title").getObjectAsString2();
	}
	
	/**
	 * Содержит заголовок колонки таблицы значений. Используется при визуальном
	 * отображении таблицы значений.
	 * @param title
	 * @throws JIException
	 */
	public void setTitle(String title) throws JIException {
		put("Title", new JIVariant(title));
	}
	
	/**
	 * Содержит строку с именем колонки.
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 * Содержит строку с именем колонки.
	 * @param name - имя колонки.
	 * @throws JIException
	 */
	public void setName(String name) throws JIException {
		put("Name", new JIVariant(name));
	}
	
	/**
	 * Содержит объект, описывающий допустимые типы значений для колонки.
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException {
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Содержит ширину колонки в символах. 
	 * Используется при визуальном отображении таблицы значений
	 * @return Integer
	 * @throws JIException - ошибка DCOM
	 */
	public Integer getWidth() throws JIException {
		return get("Width").getObjectAsInt();
	}
	
	/**
	 * Содержит ширину колонки в символах. 
	 * @param width
	 * @throws JIException
	 */
	public void setWidth(int width) throws JIException {
		put("Width", new JIVariant(width));
	}

}
