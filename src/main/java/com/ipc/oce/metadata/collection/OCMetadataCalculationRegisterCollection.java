package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCCalculationRegisterMetadataObject;

public class OCMetadataCalculationRegisterCollection extends _OCMetadataObjectCollection<OCCalculationRegisterMetadataObject> {

	public OCMetadataCalculationRegisterCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataCalculationRegisterCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCCalculationRegisterMetadataObject get(int i) throws JIException {
		return new OCCalculationRegisterMetadataObject(super.get(i));
	}

	@Override
	public OCCalculationRegisterMetadataObject find(String objectName) throws JIException {
		return new OCCalculationRegisterMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCCalculationRegisterMetadataObject> iterator() {
		EnumVARIANT<OCCalculationRegisterMetadataObject> enumV = new EnumVARIANT<OCCalculationRegisterMetadataObject>(this) {
			
			@Override
			protected OCCalculationRegisterMetadataObject castToGeneric(JIVariant variant) {

				try {
					return new OCCalculationRegisterMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
