/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCDocumentNumberPeriodicity extends OCBusinessProcessNumberPeriodicity {

	/**
	 * @param object
	 */
	public OCDocumentNumberPeriodicity(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDocumentNumberPeriodicity(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDocumentNumberPeriodicity(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

}
