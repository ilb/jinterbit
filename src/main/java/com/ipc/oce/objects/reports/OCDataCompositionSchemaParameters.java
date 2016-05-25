/**
 * 
 */
package com.ipc.oce.objects.reports;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCDataCompositionSchemaParameters extends OCObject implements Iterable<OCDataCompositionSchemaParameter> {

	protected OCDataCompositionSchemaParameters(IJIDispatch aDispatch) {
		super(aDispatch);

	}

	protected OCDataCompositionSchemaParameters(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);

	}

	protected OCDataCompositionSchemaParameters(OCObject object) {
		super(object);

	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDataCompositionSchemaParameter> iterator() {
		EnumVARIANT<OCDataCompositionSchemaParameter> enumV = new EnumVARIANT<OCDataCompositionSchemaParameter>(this) {
			
			@Override
			protected OCDataCompositionSchemaParameter castToGeneric(JIVariant variant) {
				try {
					return new OCDataCompositionSchemaParameter(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}

}
