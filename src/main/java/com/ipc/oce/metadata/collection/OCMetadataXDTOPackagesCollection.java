/**
 * 
 */
package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCXDTOPackageMetadataObject;

/**
 * 
 * @author Konovalov
 * 
 */
public class OCMetadataXDTOPackagesCollection extends _OCMetadataObjectCollection<OCXDTOPackageMetadataObject> {

	public OCMetadataXDTOPackagesCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataXDTOPackagesCollection(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	@Override
	public OCXDTOPackageMetadataObject get(int i) throws JIException {
		return new OCXDTOPackageMetadataObject(super.get(i));
	}

	@Override
	public OCXDTOPackageMetadataObject find(String objectName)
			throws JIException {
		OCObject var = super.find(objectName);
		return var != null ? new OCXDTOPackageMetadataObject(var) : null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCXDTOPackageMetadataObject> iterator() {
		EnumVARIANT<OCXDTOPackageMetadataObject> enumV = new EnumVARIANT<OCXDTOPackageMetadataObject>(this) {
			
			@Override
			protected OCXDTOPackageMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCXDTOPackageMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

}
