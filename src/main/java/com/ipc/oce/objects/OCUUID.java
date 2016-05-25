package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Предназначен для создания и хранения глобального уникального идентификатора GUID.
 * @author Konovalov
 */
public class OCUUID extends OCObject {
	private String uuid = null;
	
	/**
	 * @param object
	 */
	public OCUUID(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCUUID(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCUUID(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	@Override
	public String toString() {
		if (uuid == null) {
			uuid =  super.toString();
		}
		return uuid;
	}

	@Override
	protected IJIDispatch dispatch() {
		return super.dispatch();
	}

}
