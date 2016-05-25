package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCGraphMetadataObject;


public class OCMetadataGraphCollection extends _OCMetadataObjectCollection<OCGraphMetadataObject> {

	public OCMetadataGraphCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataGraphCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCGraphMetadataObject get(int i) throws JIException {
		return new OCGraphMetadataObject(super.get(i));
	}

	@Override
	public OCGraphMetadataObject find(String objectName) throws JIException {
		return new OCGraphMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCGraphMetadataObject> iterator() {
		EnumVARIANT<OCGraphMetadataObject> enumV = new EnumVARIANT<OCGraphMetadataObject>(this) {
			
			@Override
			protected OCGraphMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCGraphMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
	
}
