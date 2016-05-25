package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCEnumMetadataObject;


public class OCMetadataEnumCollection extends _OCMetadataObjectCollection<OCEnumMetadataObject> {

	public OCMetadataEnumCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataEnumCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCEnumMetadataObject get(int i) throws JIException {
		return new OCEnumMetadataObject(super.get(i));
	}

	@Override
	public OCEnumMetadataObject find(String objectName) throws JIException {
		return new OCEnumMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCEnumMetadataObject> iterator() {
		EnumVARIANT<OCEnumMetadataObject> enumV = new EnumVARIANT<OCEnumMetadataObject>(this) {
			
			@Override
			protected OCEnumMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCEnumMetadataObject meta = new OCEnumMetadataObject(variant);
					return meta;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
	
	
}
