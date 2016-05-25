package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCRecalculationMetadataObject;


public class OCMetadataRecalculationCollection extends _OCMetadataObjectCollection<OCRecalculationMetadataObject> {

	public OCMetadataRecalculationCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataRecalculationCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCRecalculationMetadataObject get(int i) throws JIException {
		return new OCRecalculationMetadataObject(super.get(i));
	}

	@Override
	public OCRecalculationMetadataObject find(String objectName)
			throws JIException {
		return new OCRecalculationMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCRecalculationMetadataObject> iterator() {
		EnumVARIANT<OCRecalculationMetadataObject> enumV = new EnumVARIANT<OCRecalculationMetadataObject>(this) {
			
			@Override
			protected OCRecalculationMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCRecalculationMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
