package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * Предназначен для управления документами и предоставляет доступ к значениям типа ДокументМенеджер.<Имя документа>. Доступ к объекту осуществляется через свойство глобального контекста Документы.
 * Возможен обмен с сервером. 
 * @author Konovalov
 *
 */
public class OCDocumentCollection extends OCObject implements Iterable<OCDocumentManager>{
	
	public OCDocumentCollection(OCObject object) {
		super(object);
	}

	public OCDocumentCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Используется для доступа к определенным в конфигурации документам
	 * @param documentName
	 * @return
	 * @throws JIException
	 */
	public OCDocumentManager getDocument(String documentName) throws JIException{
		OCDocumentManager manager = new OCDocumentManager(get(documentName));
		manager.setManagerName(documentName);
		return manager;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDocumentManager> iterator() {
		EnumVARIANT<OCDocumentManager> enumV = new EnumVARIANT<OCDocumentManager>(this) {
			
			@Override
			protected OCDocumentManager castToGeneric(JIVariant variant) {
				try {
					OCDocumentManager manager = new OCDocumentManager(variant);
					manager.setManagerName(manager.toString().split("\\.")[1]);
					return manager;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
}
