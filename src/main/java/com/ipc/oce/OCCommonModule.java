/**
 * 
 */
package com.ipc.oce;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

/**
 * Предоставляет доступ к экспортным процедурам и функциям неглобального общего
 * модуля (т.е. модуля для которого не установлен признак Глобальный при
 * конфигурировании).
 * 
 * @author Konovalov
 * 
 */
public class OCCommonModule extends OCObject {

	/**
	 * @param object
	 */
	public OCCommonModule(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCCommonModule(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCCommonModule(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Конвертация массива OCVariant в JIVariant.
	 * @param params
	 * @return
	 */
	private JIVariant[] variant2JI(OCVariant[] params) {
		JIVariant[] vars = new JIVariant[params.length];
		int index = 0;
		for (int z = 0; z < params.length; z++) {
			vars[z] = params[z].getJIVariant();
		}
		return vars;
	}
	
	/**
	 * Вызов функции модуля.
	 * @param functionName - имя функции
	 * @param params - параметры функции
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant callFunction(String functionName, OCVariant[] params) throws JIException {
		
		return new OCVariant(callMethodA(functionName, (Object[])variant2JI(params))[0]);
	}
	
	/**
	 * Вызов процедуры модуля.
	 * @param functionName - имя процедуры
	 * @param params - параметры процедуры
	 * @throws JIException
	 */
	public void callProcedure(String functionName, OCVariant[] params) throws JIException {
		callMethod(functionName, (Object[])variant2JI(params));
	}
	

}
