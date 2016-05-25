/**
 * 
 */
package com.ipc.oce.varset;


/**
 * Определяет набор режимов записи документа.
 * @author Konovalov
 *
 */
public class EDocumentWriteMode extends EActivator {
	
	/**
	 * @param innerString
	 */
	public EDocumentWriteMode(String innerString) {
		super(innerString);
	}
	/**
	 * В этом случае будут сохранены изменения, 
	 * внесенные в документ, и движения.
	 */
	public static final String WRITE = "EDocumentWriteMode.WRITE"; 
	
	/**
	 * В этом случае будут сохранены изменения документа, 
	 * после чего инициирована отмена проведения документа. 
	 * Оба действия производятся в одной транзакции. 
	 */
	public static final String UNDO_POSTING = "EDocumentWriteMode.UNDO_POSTING";
	
	/**
	 * В этом случае будут сохранены все изменения документа, 
	 * после чего инициировано проведение документа. 
	 * Оба действия производятся в одной транзакции
	 */
	public static final String POSTING = "EDocumentWriteMode.POSTING";
	
	

}
