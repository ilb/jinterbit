package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCTabularSectionMetadataObject;


public class OCMetadataTabularSectionCollection extends _OCMetadataObjectCollection<OCTabularSectionMetadataObject> {

	public OCMetadataTabularSectionCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataTabularSectionCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCTabularSectionMetadataObject get(int i) throws JIException {
		return new OCTabularSectionMetadataObject(super.get(i));
	}

	@Override
	public OCTabularSectionMetadataObject find(String objectName)
			throws JIException {
		return new OCTabularSectionMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCTabularSectionMetadataObject> iterator() {
		EnumVARIANT<OCTabularSectionMetadataObject> enumV = new EnumVARIANT<OCTabularSectionMetadataObject>(this) {
			
			@Override
			protected OCTabularSectionMetadataObject castToGeneric(JIVariant variant) {
				
				try {
					return new OCTabularSectionMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
	
}
