/**
 * 
 */
package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Используется для обращения к метаданным объекта конфигурации - значение
 * перечисления.
 * 
 * @author Konovalov
 * 
 */
public class OCEnumValueMetadataObject extends _OCCommonMetadataObject {

	public OCEnumValueMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCEnumValueMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCEnumValueMetadataObject(OCObject object) {
		super(object);
	}
}
