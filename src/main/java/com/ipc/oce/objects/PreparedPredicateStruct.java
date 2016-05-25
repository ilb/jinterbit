/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;

/**
 * Damn stupid wrapper class. Package view only!!
 * 
 * @author Konovalov
 * 
 */
class PreparedPredicateStruct extends OCStructure {

	/**
	 * @param object
	 */
	PreparedPredicateStruct(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	PreparedPredicateStruct(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	PreparedPredicateStruct(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	protected IJIDispatch dispatch() {
		return super.dispatch();
	}

}
