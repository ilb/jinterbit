package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCCatalogMetadataObject;


public class OCMetadataCatalogCollection extends _OCMetadataObjectCollection<OCCatalogMetadataObject> {

	public OCMetadataCatalogCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataCatalogCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCCatalogMetadataObject get(int i) throws JIException {
		return new OCCatalogMetadataObject(super.get(i));
	}

	@Override
	public OCCatalogMetadataObject find(String objectName) throws JIException {
		OCObject object = super.find(objectName);
		if (object != null) {
			return new OCCatalogMetadataObject(object);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCCatalogMetadataObject> iterator() {
		EnumVARIANT<OCCatalogMetadataObject> enumV = new EnumVARIANT<OCCatalogMetadataObject>(this) {
			
			@Override
			protected OCCatalogMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCCatalogMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
	
}
