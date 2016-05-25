package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCDimensionMetadataObject;


public class OCMetadataDimensionCollection extends _OCMetadataObjectCollection<OCDimensionMetadataObject> {

	public OCMetadataDimensionCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataDimensionCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCDimensionMetadataObject get(int i) throws JIException {
		return new OCDimensionMetadataObject(super.get(i));
	}

	@Override
	public OCDimensionMetadataObject find(String objectName) throws JIException {
		return new OCDimensionMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDimensionMetadataObject> iterator() {
		EnumVARIANT<OCDimensionMetadataObject> enumV = new EnumVARIANT<OCDimensionMetadataObject>(this) {
			
			@Override
			protected OCDimensionMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCDimensionMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
}
