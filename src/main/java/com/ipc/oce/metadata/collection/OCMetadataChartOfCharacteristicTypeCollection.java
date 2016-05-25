package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCChartOfCharacteristicTypeMetadataObject;


public class OCMetadataChartOfCharacteristicTypeCollection extends _OCMetadataObjectCollection<OCChartOfCharacteristicTypeMetadataObject> {

	public OCMetadataChartOfCharacteristicTypeCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataChartOfCharacteristicTypeCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCChartOfCharacteristicTypeMetadataObject get(int i) throws JIException {
		return new OCChartOfCharacteristicTypeMetadataObject(super.get(i));
	}

	@Override
	public OCChartOfCharacteristicTypeMetadataObject find(String objectName) throws JIException {
		return new OCChartOfCharacteristicTypeMetadataObject(
				super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCChartOfCharacteristicTypeMetadataObject> iterator() {
		EnumVARIANT<OCChartOfCharacteristicTypeMetadataObject> enumV = new EnumVARIANT<OCChartOfCharacteristicTypeMetadataObject>(this) {
			
			@Override
			protected OCChartOfCharacteristicTypeMetadataObject castToGeneric(
					JIVariant variant) {

				try {
					return new OCChartOfCharacteristicTypeMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
	

}
