/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;

/**
 * Предназначен для определения вида движения записи регистра бухгалтерии.
 * Возможен обмен с сервером. XML-сериализация. Поддержка отображения в XDTO;
 * пространство имен: {http://v8.1c.ru/8.1/data/enterprise}. Имя типа XDTO:
 * AccountingRecordType.
 * 
 * @author Konovalov
 * 
 */
public class EAccountingRecordType extends EActivator {

	/**
	 * Движение средств в дебет счета. 
	 */
	public static final String DEBIT = "EAccountingRecordType.DEBIT";
	
	/**
	 * Движение средств в кредит счета. 
	 */
	public static final String CREDIT = "EAccountingRecordType.CREDIT";
	
	public EAccountingRecordType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public EAccountingRecordType(String innerString) {
		super(innerString);
	}
}
