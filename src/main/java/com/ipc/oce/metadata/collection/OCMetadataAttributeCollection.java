package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCAttributeMetadataObject;


public class OCMetadataAttributeCollection extends _OCMetadataObjectCollection<OCAttributeMetadataObject> {

	public OCMetadataAttributeCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataAttributeCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCAttributeMetadataObject get(int i) throws JIException {
		return new OCAttributeMetadataObject(super.get(i));
	}

	@Override
	public OCAttributeMetadataObject find(String objectName) throws JIException {
		return new OCAttributeMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCAttributeMetadataObject> iterator() {
		EnumVARIANT<OCAttributeMetadataObject> enumV = new EnumVARIANT<OCAttributeMetadataObject>(this) {
			
			@Override
			protected OCAttributeMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCAttributeMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
