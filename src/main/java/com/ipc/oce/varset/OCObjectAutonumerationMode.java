package com.ipc.oce.varset;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Режим автонумерации прикладных объектов конфигурации определяет, переиспользовать или нет автоматически полученные номера объектов, если они не записаны в базу данных.
 * @author Konovalov
 *
 */
public class OCObjectAutonumerationMode extends OCObject implements IOCVariantSet{
	public static final String NOT_AUTOFREE = "НеОсвобождатьАвтоматически";
	public static final String AUTOFREE = "ОсвобождатьАвтоматически";
	public OCObjectAutonumerationMode(OCObject object) {
		super(object);
	}

	public OCObjectAutonumerationMode(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCObjectAutonumerationMode(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	public String stringValue() {
		return toString();
	}

}
