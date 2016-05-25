package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Используется для обращения к метаданным объекта конфигурации - признак учета
 * субконто плана счетов.
 * 
 * @author Konovalov
 * 
 */
public class OCExtDimensionAccountingFlagMetadataObject extends _OCCommonMetadataObject {

	public OCExtDimensionAccountingFlagMetadataObject(OCObject object) {
		super(object);
	}

	public OCExtDimensionAccountingFlagMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCExtDimensionAccountingFlagMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCTypeDescription getTypeDescription() throws JIException {
		return new OCTypeDescription(get("Type"));
	}
}
