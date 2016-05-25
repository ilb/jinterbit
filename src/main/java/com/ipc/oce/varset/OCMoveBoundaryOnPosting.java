package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет варианты действий системы с границей последовательности при проведении. 
 * @author Konovalov
 *
 */
public class OCMoveBoundaryOnPosting extends OCObject implements IOCVariantSet{

	public static final String DONT_MOVE = "НеПеремещать";
	public static final String MOVE = "Перемещать";
	
	public OCMoveBoundaryOnPosting(OCObject object) {
		super(object);
	}

	public OCMoveBoundaryOnPosting(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMoveBoundaryOnPosting(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
