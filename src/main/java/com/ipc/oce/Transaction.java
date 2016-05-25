/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;

/**
 * Класс реализующий обращение к транзакциям 1С.
 * @author Konovalov
 *
 */
public class Transaction {
	
	/**
	 * Экземпляр OCApp данного экземпляра транзации. 
	 */
	private OCApp instance = null;

	/**
	 * Конструктор транзакции.
	 * @param instance экземпляр OCApp, для которого будет создана транзакция. 
	 */
	protected Transaction(OCApp instance) {
		super();
		this.instance = instance;
	}
	
	/**
	 * Получить текущее состояние транзакции.
	 * @return  
	 * Истина - в системе имеется активная транзакция, 
	 * Ложь - в текущий момент в системе не имеется активных транзакций. 
	 * @throws JIException
	 */
	public boolean inTransaction() throws JIException{
		return instance.callMethodA("TransactionActive").getObjectAsBoolean();
	}
	
	/**
	 * Открывает транзакцию. Транзакция предназначена для записи в информационную базу согласованных изменений. Все изменения, внесенные в информационную базу после начала транзакции, будут затем либо целиком записаны, либо целиком отменены. 
	 * @throws JIException
	 */
	public void begin() throws JIException{
		if (!inTransaction()) {
			instance.callMethod("BeginTransaction");
		}
	}
	
	/**
	 * Завершает успешную транзакцию. Все изменения, внесенные в информационную базу в процессе транзакции, будут записаны. 
	 * @throws JIException
	 */
	public void commit() throws JIException{
		if (inTransaction()) {
			instance.callMethod("CommitTransaction");
		}
	}
	
	/**
	 * Отменяет открытую ранее транзакцию. Все изменения, внесенные в информационную базу в процессе транзакции, будут отменены. 
	 * @throws JIException 
	 */
	public void rollback() throws JIException {
		if (inTransaction()) {
			instance.callMethod("RollbackTransaction");
		}
	}
	
}
