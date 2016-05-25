package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;


public class OCCatalogCollection extends OCObject implements Iterable<OCCatalogManager>{

	public OCCatalogCollection(OCObject object) {
		super(object);
	}

	public OCCatalogCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCatalogCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	/**
	 * Предназначен для управления справочниками и предоставляет доступ к значениям типа СправочникМенеджер.<Имя справочника>. Доступ к объекту осуществляется через свойство глобального контекста Справочники.
	 * @param catalogName
	 * @return
	 * @throws JIException
	 */
	public OCCatalogManager getCatalog(String catalogName) throws JIException{
		OCCatalogManager manager = new OCCatalogManager(get(catalogName));
		manager.setManagerName(catalogName);
		return manager;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCCatalogManager> iterator() {
		EnumVARIANT<OCCatalogManager> enumV = new EnumVARIANT<OCCatalogManager>(this) {
			
			@Override
			protected OCCatalogManager castToGeneric(JIVariant variant) {
				try {
					OCCatalogManager manager = new OCCatalogManager(variant);
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
