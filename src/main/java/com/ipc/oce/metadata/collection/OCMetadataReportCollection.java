package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCReportMetadataObject;


public class OCMetadataReportCollection extends _OCMetadataObjectCollection<OCReportMetadataObject> {

	public OCMetadataReportCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataReportCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCReportMetadataObject get(int i) throws JIException {
		return new OCReportMetadataObject(super.get(i));
	}

	@Override
	public OCReportMetadataObject find(String objectName) throws JIException {
		return new OCReportMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCReportMetadataObject> iterator() {
		EnumVARIANT<OCReportMetadataObject> enumV = new EnumVARIANT<OCReportMetadataObject>(this) {
			
			@Override
			protected OCReportMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCReportMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
