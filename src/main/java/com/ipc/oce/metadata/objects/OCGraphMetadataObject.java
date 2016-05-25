package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


/**
 * Используется для обращения к метаданным объекта конфигурации - графа журнала документов.
 * @author Konovalov
 *
 */
public class OCGraphMetadataObject extends _OCCommonMetadataObject {

	public OCGraphMetadataObject(OCObject object) {
		super(object);
	}

	public OCGraphMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCGraphMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	// кроме методов из _OCCommonMetadataObject сосбо больше нечего добавить

}
