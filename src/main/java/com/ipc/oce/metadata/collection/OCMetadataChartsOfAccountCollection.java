package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCChartsOfAccountsMetadataObject;


public class OCMetadataChartsOfAccountCollection extends _OCMetadataObjectCollection<OCChartsOfAccountsMetadataObject> {

	public OCMetadataChartsOfAccountCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataChartsOfAccountCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCChartsOfAccountsMetadataObject get(int i) throws JIException {
		return new OCChartsOfAccountsMetadataObject(super.get(i));
	}

	@Override
	public OCChartsOfAccountsMetadataObject find(String objectName) throws JIException {
		return new OCChartsOfAccountsMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCChartsOfAccountsMetadataObject> iterator() {
		EnumVARIANT<OCChartsOfAccountsMetadataObject> enumV = new EnumVARIANT<OCChartsOfAccountsMetadataObject>(this) {
			
			@Override
			protected OCChartsOfAccountsMetadataObject castToGeneric(JIVariant variant) {

				try {
					return new OCChartsOfAccountsMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
	
}
