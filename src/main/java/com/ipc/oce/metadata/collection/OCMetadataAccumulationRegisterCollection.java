package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCAccumulationRegisterMetadataObject;


public class OCMetadataAccumulationRegisterCollection extends _OCMetadataObjectCollection<OCAccumulationRegisterMetadataObject> {

	public OCMetadataAccumulationRegisterCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataAccumulationRegisterCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCAccumulationRegisterMetadataObject get(int i) throws JIException {
		return new OCAccumulationRegisterMetadataObject(super.get(i));
	}

	@Override
	public OCAccumulationRegisterMetadataObject find(String objectName) throws JIException {
		return new OCAccumulationRegisterMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCAccumulationRegisterMetadataObject> iterator() {
		EnumVARIANT<OCAccumulationRegisterMetadataObject> enumV = new EnumVARIANT<OCAccumulationRegisterMetadataObject>(this) {
			
			@Override
			protected OCAccumulationRegisterMetadataObject castToGeneric(JIVariant variant) {
				
				try {
					return new OCAccumulationRegisterMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
}
