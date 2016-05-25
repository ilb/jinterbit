/**
 * 
 */
package com.ipc.oce.varset;


/**
 * Содержит варианты форм представления свойства объекта XDTO в формате XML.
 * @author Konovalov
 *
 */
public class EXMLForm extends EActivator {
	
	/**
	 * @param innerString
	 */
	public EXMLForm(String innerString) {
		super(innerString);
	}

	/**
	 * Представление в виде атрибута XML. 
	 */
	public static final String ATTRIBUTE = "EXMLForm.ATTRIBUTE";
	
	/**
	 * Представление свойства в виде содержания элемента XML.
	 */
	public static final String TEXT = "EXMLForm.TEXT";
	
	/**
	 * Представление в виде элемента XML.
	 */
	public static final String ELEMENT = "EXMLForm.ELEMENT";

	
	

}
