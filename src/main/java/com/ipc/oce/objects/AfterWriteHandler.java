/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;

/**
 * Класс обработчик действий производимых с объектом перед записью в базу данных.
 * @author Konovalov
 *
 */
public abstract class AfterWriteHandler {
	
	private OCApp app = null;
	
	protected AfterWriteHandler() {
		super();
	}
	
	protected final void setAppInstance(OCApp app) {
		this.app = app;
	}
	
	public abstract void handle(_OCCommonObject object) throws JIException;
}
