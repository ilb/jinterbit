package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCEnumValueMetadataObject;


public class OCMetadataEnumValueCollection extends _OCMetadataObjectCollection<OCEnumValueMetadataObject> {


	public OCMetadataEnumValueCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataEnumValueCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCEnumValueMetadataObject get(int i) throws JIException {
		return new OCEnumValueMetadataObject(super.get(i));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCEnumValueMetadataObject> iterator() {
		EnumVARIANT<OCEnumValueMetadataObject> enumV = new EnumVARIANT<OCEnumValueMetadataObject>(this) {
			
			@Override
			protected OCEnumValueMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCEnumValueMetadataObject meta = new OCEnumValueMetadataObject(variant);
					return meta;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
