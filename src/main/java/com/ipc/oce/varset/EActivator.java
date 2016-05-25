/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;

/**
 * Класс реализующий привязку суперкласса к dispatch конкретной сессии и хранение строки внутреннего представления объекта
 * @author Konovalov
 *
 */
public abstract class EActivator extends OCObject {

	private String innerString = null;
	
	private boolean binded = false;
	
	@SuppressWarnings("deprecation")
	protected EActivator(String innerString) {
		this.innerString = innerString;
	}
	
	/**
	 * Для создания экземпляров не из таблиц активации, а из объектов 1С.
	 * @param aDispatch
	 * @throws JIException
	 */
	protected EActivator(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public String getInnerString() throws JIException {
		if (innerString == null) {
			OCApp app = OCApp.getInstance(getAssociatedSessionID());
			innerString = app.valueToStringInternal(this);
		}
		return innerString;
	}
	
	/**
	 * Привязка активатора к конкретному экземпляру сессии, dispatch полечается из представления внутренней строки
	 * @param application
	 * @throws JIException
	 */
	public void bind(OCApp application) throws JIException {
		IJIDispatch dispatch = ocObject2Dispatch(application.valueFromStringInternal(getInnerString()));
		setDispatch(dispatch);
		binded = true;
	}
	
	public boolean wasBinded(){
		return binded;
	}
	
	
}
