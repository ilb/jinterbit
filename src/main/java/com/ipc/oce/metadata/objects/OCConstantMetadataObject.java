package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.OCType;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * Используется для обращения к метаданным объекта конфигурации - константа. 
 * @author Konovalov
 *
 */
public class OCConstantMetadataObject extends _OCCommonMetadataObject {

	public OCConstantMetadataObject(OCObject object) {
		super(object);
	}

	public OCConstantMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCConstantMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Тип данных объекта метаданных. 
	 * @return OCTypeDescription
	 * @throws JIException
	 */
	public OCTypeDescription getTypeDescription() throws JIException{
		return new OCTypeDescription(get("Type"));
	}
	
	/**
	 * Тип данных объекта метаданных. 
	 * @return OCTypeDescription
	 * @throws JIException
	 */
	public OCType getType() throws JIException{
		return getTypeDescription().getType();
	}
}
