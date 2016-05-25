package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;


public class OCDocumentJournalCollection extends OCObject {

	public OCDocumentJournalCollection(OCObject object) {
		super(object);
	}

	public OCDocumentJournalCollection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentJournalCollection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	/**
	 * Предназначен для управления журналами документов и предоставляет доступ к значениям типа ЖурналДокументовМенеджер.<Имя журнала документов>.
	 * @param documentJournalName
	 * @return
	 * @throws JIException
	 */
	public OCDocumentJournalManager getDocumentJournal(String documentJournalName) throws JIException{
		OCDocumentJournalManager manager = new OCDocumentJournalManager (get(documentJournalName));
		manager.setManagerName(documentJournalName);
		return manager;
	}
}
