package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;

/**
 * Используется для обращения к метаданным объекта конфигурации - язык. 
 * @author Konovalov
 *
 */
public class OCLanguage extends _OCCommonMetadataObject {

	public OCLanguage(OCObject object) {
		super(object);
	}

	public OCLanguage(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCLanguage(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Код языка, например "en". 
	 * @return
	 * @throws JIException
	 */
	public String getLanguageCode() throws JIException {
		return get("LanguageCode").getObjectAsString2();
	}
}
