/**
 * 
 */
package com.ipc.oce.varset;


/**
 * Содержит варианты обхода результата запроса
 * @author Konovalov
 *
 */
public class EQueryResultIteration extends EActivator{
	/**
	 * @param innerString
	 */
	public EQueryResultIteration(String innerString) {
		super(innerString);
	}

	/**
	 * Обход записей результата запроса по группировкам. 
	 */
	public static final String BYGROUPS = "EQueryResultIteration.BYGROUPS";
	
	/**
	 * Обход записей результата запроса по группировкам с учетом иерархии. 
	 */
	public static final String BYGROUPS_WITH_HIERARCHY = "EQueryResultIteration.BYGROUPS_WITH_HIERARCHY";
	
	/**
	 * Прямой тип обхода записей результата запроса. При таком способе обход осуществляется без группировок и иерархии. 
	 */
	public static final String LINEAR = "EQueryResultIteration.LINEAR";

	
	
	
	
}
