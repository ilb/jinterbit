/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Определяет возможность/невозможность проведения документа.
 * @author Konovalov
 *
 */
public class OCPosting extends OCObject implements IOCVariantSet {

	public static final String DENY = "Запретить";
	public static final String ALLOW = "Разрешить";
	/**
	 * @param object
	 */
	public OCPosting(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCPosting(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCPosting(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public String stringValue() {
		return toString();
	}

}
