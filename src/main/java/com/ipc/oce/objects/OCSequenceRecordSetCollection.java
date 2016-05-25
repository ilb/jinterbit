/**
 * 
 */
package com.ipc.oce.objects;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.OCFixedCollection;
import com.ipc.oce.OCObject;

/**
 * @author Konovalov
 *
 */
public class OCSequenceRecordSetCollection extends OCFixedCollection implements Iterable<OCSequenceRecordSet>{

	/**
	 * @param object
	 */
	public OCSequenceRecordSetCollection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCSequenceRecordSetCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCSequenceRecordSetCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCSequenceRecordSet getElement(int index) throws JIException {
		return new OCSequenceRecordSet(super.getElement(index));
	}

	@Override
	public OCSequenceRecordSet find(String objectName) throws JIException {
		return new OCSequenceRecordSet(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCSequenceRecordSet> iterator() {
		EnumVARIANT<OCSequenceRecordSet> enumV = new EnumVARIANT<OCSequenceRecordSet>(this) {
			
			@Override
			protected OCSequenceRecordSet castToGeneric(JIVariant variant) {
				try {
					return new OCSequenceRecordSet(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
