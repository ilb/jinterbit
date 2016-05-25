/**
 * 
 */
package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Содержит типы записей выборки результатов запроса
 * @author Konovalov
 *
 */
public class OCQueryRecordType extends OCObject implements IOCVariantSet {

	public static final String DETAILED_RECORD = "Детальная запись";
	public static final String GROUP_TOTAL = "ИтогПоГруппировке";
	public static final String TOTAL_BY_HIERARCHY = "ИтогПоИерархии";
	public static final String OVERALL = "ОбщийИтог";
	/**
	 * @param object
	 */
	public OCQueryRecordType(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCQueryRecordType(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCQueryRecordType(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public String stringValue() {
		return toString();
	}

}
