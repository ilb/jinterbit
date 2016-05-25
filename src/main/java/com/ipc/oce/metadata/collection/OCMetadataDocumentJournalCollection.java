package com.ipc.oce.metadata.collection;

import java.util.Iterator;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.EnumVARIANT;
import com.ipc.oce.metadata.objects.OCDocumentJournalMetadataObject;


public class OCMetadataDocumentJournalCollection extends _OCMetadataObjectCollection<OCDocumentJournalMetadataObject> {

	public OCMetadataDocumentJournalCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCMetadataDocumentJournalCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	@Override
	public OCDocumentJournalMetadataObject get(int i) throws JIException {
		return new OCDocumentJournalMetadataObject(super.get(i));
	}

	@Override
	public OCDocumentJournalMetadataObject find(String objectName) throws JIException {
		return new OCDocumentJournalMetadataObject(super.find(objectName));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<OCDocumentJournalMetadataObject> iterator() {
		EnumVARIANT<OCDocumentJournalMetadataObject> enumV = new EnumVARIANT<OCDocumentJournalMetadataObject>(this) {
			
			@Override
			protected OCDocumentJournalMetadataObject castToGeneric(JIVariant variant) {
				try {
					return new OCDocumentJournalMetadataObject(variant);
				} catch (JIException e) {
					throw new RuntimeException(e);
				}
			}
		};
		return enumV;
	}
	
}
