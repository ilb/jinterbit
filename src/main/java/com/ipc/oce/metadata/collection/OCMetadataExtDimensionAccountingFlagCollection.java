package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCExtDimensionAccountingFlagMetadataObject;


public class OCMetadataExtDimensionAccountingFlagCollection extends _OCMetadataObjectCollection<OCExtDimensionAccountingFlagMetadataObject> {

	public OCMetadataExtDimensionAccountingFlagCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataExtDimensionAccountingFlagCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCExtDimensionAccountingFlagMetadataObject get(int i)
			throws JIException {
		return new OCExtDimensionAccountingFlagMetadataObject(super.get(i));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCExtDimensionAccountingFlagMetadataObject> iterator() {
		EnumVARIANT<OCExtDimensionAccountingFlagMetadataObject> enumV = new EnumVARIANT<OCExtDimensionAccountingFlagMetadataObject>(this) {
			
			@Override
			protected OCExtDimensionAccountingFlagMetadataObject castToGeneric(
					JIVariant variant) {
				try {
					return new OCExtDimensionAccountingFlagMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

	
}
