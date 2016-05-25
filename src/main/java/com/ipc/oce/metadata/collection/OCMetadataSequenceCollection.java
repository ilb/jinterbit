package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCSequenceMetadataObject;


public class OCMetadataSequenceCollection extends _OCMetadataObjectCollection<OCSequenceMetadataObject> {

	public OCMetadataSequenceCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataSequenceCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCSequenceMetadataObject get(int i) throws JIException {
		return new OCSequenceMetadataObject(super.get(i));
	}

	@Override
	public OCSequenceMetadataObject find(String objectName) throws JIException {
		return new OCSequenceMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCSequenceMetadataObject> iterator() {
		EnumVARIANT<OCSequenceMetadataObject> enumV = new EnumVARIANT<OCSequenceMetadataObject>(this) {
			
			@Override
			protected OCSequenceMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCSequenceMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
