package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCResourceMetadataObject;


public class OCMetadataResourceCollection extends _OCMetadataObjectCollection<OCResourceMetadataObject> {

	public OCMetadataResourceCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataResourceCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	@Override
	public OCResourceMetadataObject get(int i) throws JIException {
		return new OCResourceMetadataObject(super.get(i));
	}

	@Override
	public OCResourceMetadataObject find(String objectName) throws JIException {
		return new OCResourceMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCResourceMetadataObject> iterator() {
		EnumVARIANT<OCResourceMetadataObject> enumV = new EnumVARIANT<OCResourceMetadataObject>(this) {
			
			@Override
			protected OCResourceMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCResourceMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
