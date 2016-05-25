package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.OCTypeDescription;


/**
 * Используется для обращения к метаданным объекта конфигурации - признак учета плана счетов. 
 * @author Konovalov
 *
 */
public class OCAccountingFlagMetadataObject extends _OCCommonMetadataObject {

	public OCAccountingFlagMetadataObject(OCObject object) {
		super(object);
	}

	public OCAccountingFlagMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAccountingFlagMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public OCTypeDescription getTypeDescription() throws JIException{
		return new OCTypeDescription(get("Type"));
	}
	public OCType getType() throws JIException{
		return getTypeDescription().getType();
	}
}
