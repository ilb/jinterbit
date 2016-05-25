/**
 * 
 */
package com.ipc.oce.varset;


/**
 * @author Konovalov
 *
 */
public class EDocumentPostingMode extends EActivator{
	/**
	 * @param innerString
	 */
	public EDocumentPostingMode(String innerString) {
		super(innerString);
	}
	/**
	 * Неоперативное проведение, т.е. проведение выполняется не в реальном времени. Например, используется при проведении задним числом
	 */
	public static final String REGULAR = "EDocumentPostingMode.REGULAR";
	/**
	 * Оперативное проведение, т.е. проведение выполняется в реальном времени. При этом может, например, выполняться контроль текущих остатков
	 */
	public static final String REALTIME = "EDocumentPostingMode.REALTIME";
	
	

	
}
