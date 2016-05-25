package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCChartsOfCalculationTypeMetadataObject;


public class OCMetadataChartsOfCalculationTypeCollection extends _OCMetadataObjectCollection<OCChartsOfCalculationTypeMetadataObject> {

	public OCMetadataChartsOfCalculationTypeCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataChartsOfCalculationTypeCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCChartsOfCalculationTypeMetadataObject get(int i) throws JIException {
		return new OCChartsOfCalculationTypeMetadataObject(super.get(i));
	}

	@Override
	public OCChartsOfCalculationTypeMetadataObject find(String objectName) throws JIException {
		return new OCChartsOfCalculationTypeMetadataObject(
				super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCChartsOfCalculationTypeMetadataObject> iterator() {
		EnumVARIANT<OCChartsOfCalculationTypeMetadataObject> enumV = new EnumVARIANT<OCChartsOfCalculationTypeMetadataObject>(this) {
			
			@Override
			protected OCChartsOfCalculationTypeMetadataObject castToGeneric(
					JIVariant variant) {
				try {
					return new OCChartsOfCalculationTypeMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
