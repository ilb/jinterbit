package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCConstantMetadataObject;


public class OCMetadataConstantCollection extends _OCMetadataObjectCollection<OCConstantMetadataObject> {

	public OCMetadataConstantCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataConstantCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCConstantMetadataObject get(int i) throws JIException {
		return new OCConstantMetadataObject(super.get(i));
	}

	@Override
	public OCConstantMetadataObject find(String objectName) throws JIException {
		return new OCConstantMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCConstantMetadataObject> iterator() {
		EnumVARIANT<OCConstantMetadataObject> enumV = new EnumVARIANT<OCConstantMetadataObject>(this) {
			
			@Override
			protected OCConstantMetadataObject castToGeneric(JIVariant variant) {
				
				try {
					return new OCConstantMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
}
