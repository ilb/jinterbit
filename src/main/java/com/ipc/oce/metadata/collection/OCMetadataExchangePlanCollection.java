package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCExchangePlanMetadataObject;


public class OCMetadataExchangePlanCollection extends _OCMetadataObjectCollection<OCExchangePlanMetadataObject> {

	public OCMetadataExchangePlanCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataExchangePlanCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCExchangePlanMetadataObject get(int i) throws JIException {
		return new OCExchangePlanMetadataObject(super.get(i));
	}

	@Override
	public OCExchangePlanMetadataObject find(String objectName)
			throws JIException {
		return new OCExchangePlanMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCExchangePlanMetadataObject> iterator() {
		EnumVARIANT<OCExchangePlanMetadataObject> enumV = new EnumVARIANT<OCExchangePlanMetadataObject>(this) {
			
			@Override
			protected OCExchangePlanMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCExchangePlanMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
