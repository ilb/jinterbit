/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Виды движений регистра накопления.
 * @author Konovalov
 *
 */
public class OCAccumulationRecordType extends OCObject implements IOCVariantSet {

	public static final String RECEIPT = "Приход";
	public static final String EXPENSE = "Расход";
	/**
	 * @param object
	 */
	public OCAccumulationRecordType(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCAccumulationRecordType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCAccumulationRecordType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public String stringValue() {
		return toString();
	}

}
