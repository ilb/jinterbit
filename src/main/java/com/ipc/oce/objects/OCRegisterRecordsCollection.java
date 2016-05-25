package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Коллекция движений документа. 
 * @author Konovalov
 *
 */
public class OCRegisterRecordsCollection extends OCObject {

	public OCRegisterRecordsCollection(OCObject object) {
		super(object);
	}

	public OCRegisterRecordsCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCRegisterRecordsCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public Integer size() throws JIException{
		return callMethodA("Count").getObjectAsInt();
	}
	
	// TODO : 1) Нужно сюда вернуться и найти описание этого объекта в help 1C 2) попытка №2 пока недала результатов
	public OCObject get(Integer i) throws JIException{
		return new OCObject(callMethodA("Get", new Object[]{new JIVariant(i)})[0]);
	}

}
