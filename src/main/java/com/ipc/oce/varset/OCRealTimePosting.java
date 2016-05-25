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
public class OCRealTimePosting extends OCPosting implements IOCVariantSet{
	
	/**
	 * @param object
	 */
	public OCRealTimePosting(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCRealTimePosting(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCRealTimePosting(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public String stringValue() {
		return super.toString();
	}

}
