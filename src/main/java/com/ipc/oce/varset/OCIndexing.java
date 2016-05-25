package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Определяет набор вариантов индексирования по реквизиту справочника, документа (а также по реквизиту табличной части), измерению регистра. 
 * @author Konovalov
 *
 */
public class OCIndexing extends OCObject implements IOCVariantSet{

	public static final String INDEX = "Индексировать";
	public static final String INDEX_W_ADDITIONAL_ORDER = "ИндексироватьСДопУпорядочиванием";
	public static final String DONT_INDEX = "НеИндексировать";
	
	public OCIndexing(OCObject object) {
		super(object);
	}

	public OCIndexing(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCIndexing(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public String getIndexingAsString(){
		return toString();
	}

	
	public String stringValue() {
		return getIndexingAsString();
	}
}
