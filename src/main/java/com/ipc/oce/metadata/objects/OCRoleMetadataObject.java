/**
 * 
 */
package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Используется для обращения к метаданным объекта конфигурации - роль. 
 * @author Konovalov
 *
 */
public class OCRoleMetadataObject extends _OCCommonMetadataObject {

	/**
	 * @param object
	 */
	public OCRoleMetadataObject(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCRoleMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCRoleMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

}
