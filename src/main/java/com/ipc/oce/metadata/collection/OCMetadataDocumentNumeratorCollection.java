package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCDocumentNumeratorMetadataObject;


public class OCMetadataDocumentNumeratorCollection extends _OCMetadataObjectCollection<OCDocumentNumeratorMetadataObject> {

	public OCMetadataDocumentNumeratorCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataDocumentNumeratorCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCDocumentNumeratorMetadataObject get(int i) throws JIException {
		return new OCDocumentNumeratorMetadataObject(super.get(i));
	}

	@Override
	public OCDocumentNumeratorMetadataObject find(String objectName) throws JIException {
		return new OCDocumentNumeratorMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDocumentNumeratorMetadataObject> iterator() {
		EnumVARIANT<OCDocumentNumeratorMetadataObject> enumV = new EnumVARIANT<OCDocumentNumeratorMetadataObject>(this) {
			
			@Override
			protected OCDocumentNumeratorMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCDocumentNumeratorMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
