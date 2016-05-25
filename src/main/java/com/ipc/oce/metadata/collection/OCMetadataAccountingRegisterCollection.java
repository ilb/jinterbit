package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCAccountingRegisterMetadataObject;


public class OCMetadataAccountingRegisterCollection extends _OCMetadataObjectCollection<OCAccountingRegisterMetadataObject> {

	public OCMetadataAccountingRegisterCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCMetadataAccountingRegisterCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	@Override
	public OCAccountingRegisterMetadataObject get(int i) throws JIException {
		return new OCAccountingRegisterMetadataObject(super.get(i));
	}

	@Override
	public OCAccountingRegisterMetadataObject find(String objectName) throws JIException {
		return new OCAccountingRegisterMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCAccountingRegisterMetadataObject> iterator() {
		EnumVARIANT<OCAccountingRegisterMetadataObject> enumV = new EnumVARIANT<OCAccountingRegisterMetadataObject>(this) {

			@Override
			protected OCAccountingRegisterMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCAccountingRegisterMetadataObject meta = new OCAccountingRegisterMetadataObject(variant);
					return meta;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
