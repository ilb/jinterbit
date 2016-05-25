/**
 * 
 */
package com.ipc.oce.varset;


/**
 * Содержит варианты необходимости назначения типа элементу XML при сохранении значения
 * @author Konovalov
 *
 */
public class EXMLTypeAssignment extends EActivator {

	/**
	 * @param innerString
	 */
	public EXMLTypeAssignment(String innerString) {
		super(innerString);
	}

	/**
	 * Назначение типа при сохранении значения в поток XML производиться не будет. 
	 */
	public static final String IMPLICIT = "EXMLTypeAssignment.IMPLICIT";
	
	/**
	 * Явное назначение типа при записи значения в поток XML
	 */
	public static final String EXPLICIT = "EXMLTypeAssignment.EXPLICIT";
	/**
	 * @param object
	 */


}
