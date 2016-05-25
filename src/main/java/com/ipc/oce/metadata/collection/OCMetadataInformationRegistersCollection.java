package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCInformationRegisterMetadataObject;

public class OCMetadataInformationRegistersCollection extends _OCMetadataObjectCollection<OCInformationRegisterMetadataObject> {

	public OCMetadataInformationRegistersCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataInformationRegistersCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCInformationRegisterMetadataObject get(int i) throws JIException {
		return new OCInformationRegisterMetadataObject(super.get(i));
	}

	@Override
	public OCInformationRegisterMetadataObject find(String objectName)
			throws JIException {
		return new OCInformationRegisterMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCInformationRegisterMetadataObject> iterator() {
		EnumVARIANT<OCInformationRegisterMetadataObject> enumV = new EnumVARIANT<OCInformationRegisterMetadataObject>(this) {
			
			@Override
			protected OCInformationRegisterMetadataObject castToGeneric(
					JIVariant variant) {

				try {
					return new OCInformationRegisterMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
