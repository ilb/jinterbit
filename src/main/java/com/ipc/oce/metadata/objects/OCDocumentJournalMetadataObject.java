package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataGraphCollection;


public class OCDocumentJournalMetadataObject extends _OCCommonMetadataObject {

	public OCDocumentJournalMetadataObject(OCObject object) {
		super(object);
	}

	public OCDocumentJournalMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentJournalMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Коллекция объектов метаданных, описывающих графы журнала документов. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataGraphCollection getColumns() throws JIException{
		return new OCMetadataGraphCollection(get("Columns"));
	}
}
