package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCSubsystemMetadataObject;


public class OCMetadataSubsystemCollection extends _OCMetadataObjectCollection<OCSubsystemMetadataObject> {

	public OCMetadataSubsystemCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataSubsystemCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	@Override
	public OCSubsystemMetadataObject get(int i) throws JIException {
		return new OCSubsystemMetadataObject(super.get(i));
	}

	@Override
	public OCSubsystemMetadataObject find(String objectName) throws JIException {
		return new OCSubsystemMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCSubsystemMetadataObject> iterator() {
		EnumVARIANT<OCSubsystemMetadataObject> enumV = new EnumVARIANT<OCSubsystemMetadataObject>(this) {
			
			@Override
			protected OCSubsystemMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCSubsystemMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
