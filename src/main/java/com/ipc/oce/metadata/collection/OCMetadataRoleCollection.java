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
import com.ipc.oce.metadata.objects.OCRoleMetadataObject;

/**
 * @author Konovalov
 *
 */
public class OCMetadataRoleCollection extends _OCMetadataObjectCollection<OCRoleMetadataObject> {

	/**
	 * @param aDispatch
	 */
	public OCMetadataRoleCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCMetadataRoleCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCRoleMetadataObject get(int i) throws JIException {
		return new OCRoleMetadataObject(super.get(i));
	}

	@Override
	public OCObject find(String objectName) throws JIException {
		OCObject obj = super.find(objectName);
		if (obj != null) {
			return new OCRoleMetadataObject(super.find(objectName));
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCRoleMetadataObject> iterator() {
		EnumVARIANT<OCRoleMetadataObject> enumV = new EnumVARIANT<OCRoleMetadataObject>(this) {
			
			@Override
			protected OCRoleMetadataObject castToGeneric(JIVariant variant) {
				try {
					OCRoleMetadataObject meta = new OCRoleMetadataObject(variant);
					return meta;
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
