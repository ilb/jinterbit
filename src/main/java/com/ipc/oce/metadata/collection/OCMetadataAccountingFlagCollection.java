package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCAccountingFlagMetadataObject;


public class OCMetadataAccountingFlagCollection extends _OCMetadataObjectCollection<OCAccountingFlagMetadataObject> {

	public OCMetadataAccountingFlagCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataAccountingFlagCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCAccountingFlagMetadataObject get(int i) throws JIException {
		return new OCAccountingFlagMetadataObject(super.get(i));
	}

	@Override
	public OCAccountingFlagMetadataObject find(String objectName) throws JIException {
		return new OCAccountingFlagMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCAccountingFlagMetadataObject> iterator() {
		EnumVARIANT<OCAccountingFlagMetadataObject> enumV = new EnumVARIANT<OCAccountingFlagMetadataObject>(this) {
			
			@Override
			protected OCAccountingFlagMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCAccountingFlagMetadataObject meta = new OCAccountingFlagMetadataObject(variant);
					return meta;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	
	
	

}
