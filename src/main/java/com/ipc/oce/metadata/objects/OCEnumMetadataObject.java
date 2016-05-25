package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataEnumValueCollection;

/**
 * Используется для обращения к метаданным объекта конфигурации - перечисление.
 * 
 * @author Konovalov
 * 
 */
public class OCEnumMetadataObject extends _OCCommonMetadataObject {

	public OCEnumMetadataObject(OCObject object) {
		super(object);
	}

	public OCEnumMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCEnumMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Коллекция объектов метаданных, описывающих значения данного перечисления.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataEnumValueCollection getEnumValues() throws JIException {
		return new OCMetadataEnumValueCollection(get("EnumValues"));
	}
}
