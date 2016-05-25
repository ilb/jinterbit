/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Описывает параметры колонки дерева значений.
 * 
 * @author Konovalov
 * 
 */
public class OCValueTreeColumn extends OCObject {

	/**
	 * @param object
	 */
	public OCValueTreeColumn(OCObject object) {
		super(object);

	}

	/**
	 * @param aDispatch
	 */
	public OCValueTreeColumn(IJIDispatch aDispatch) {
		super(aDispatch);

	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCValueTreeColumn(JIVariant aDispatch) throws JIException {
		super(aDispatch);

	}
	
	/**
	 * Содержит текст заголовка колонки дерева значений. Может быть использован при визуальном представлении дерева. 
	 * @return
	 * @throws JIException
	 */
	public String getTitle() throws JIException {
		return get("Title").getObjectAsString2();
	}
	
	/**
	 * Содержит текст заголовка колонки дерева значений. Может быть использован при визуальном представлении дерева. 
	 * @param title
	 * @throws JIException
	 */
	public void setTitle(String title) throws JIException {
		put("Title", new JIVariant(title));
	}
	
	/**
	 *  Содержит имя колонки. 
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}
	
	/**
	 *  Содержит имя колонки. 
	 * @param name
	 * @throws JIException
	 */
	public void setName(String name) throws JIException {
		put("Name", new JIVariant(name));
	}
	
	/**
	 * Содержит объект, описывающий допустимые типы значений для колонки дерева. 
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException {
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Содержит ширину колонки дерева значений в символах. Может быть использован при визуальном представлении дерева. 
	 * @return
	 * @throws JIException
	 */
	public int getWidth() throws JIException {
		return get("Width").getObjectAsInt();
	}
	
	/**
	 * Содержит ширину колонки дерева значений в символах. Может быть использован при визуальном представлении дерева. 
	 * @param width
	 * @throws JIException
	 */
	public void setWidth(int width) throws JIException {
		put("Width", new JIVariant(width));
	}

}
