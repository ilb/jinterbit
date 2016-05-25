package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.objects.OCDocumentJournalMetadataObject;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;


/**
 * Предназначен для управления журналом документов, как объектом конфигурации. С помощью этого объекта можно получить выборку документов, регистрируемых в журнале, работать с формами и макетами конкретного журнала документов. Доступ к объекту осуществляется через свойство объекта ЖурналыДокументовМенеджер. 
 * Полное имя типа объекта определяется с учетом имени журнала документов конфигурации. Например, для журнала документов "Реализация" имя типа будет выглядеть ЖурналДокументовМенеджер.Реализация.
 * @author Konovalov
 *
 */
public class OCDocumentJournalManager extends _OCAbstractManager {

	public OCDocumentJournalManager(OCObject object) {
		super(object);
	}

	public OCDocumentJournalManager(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentJournalManager(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Формирует выборку документов, регистрируемых в журнале.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select() throws JIException{
		return new OCDocumentSelection(callMethodA("Select"));
	}
	
	/**
	 * Формирует выборку документов, регистрируемых в журнале, за определенный период.
	 * @param startDate Дата и время начала периода выборки документов. Если параметр не указан, то выбираются все документы, начиная с самого первого документа в базе данных.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select(Date startDate) throws JIException{
		return new OCDocumentSelection(callMethodA("Select",
				new Object[] { JIVariant.makeVariant(startDate) })[0]);
	}
	
	/**
	 * Формирует выборку документов, регистрируемых в журнале, за определенный период.
	 * @param startDate Дата и время начала периода выборки документов. Если параметр не указан, то выбираются все документы, начиная с самого первого документа в базе данных.
	 * @param endDate Дата окончания периода выбираемых документов. Если параметр не указан, то выбираются все документы, начиная с самого первого документа в базе данных.
	 * @return
	 * @throws JIException
	 */
	public OCDocumentSelection select(Date startDate, Date endDate) throws JIException{
		return new OCDocumentSelection(callMethodA(
				"Select",
				new Object[] { JIVariant.makeVariant(startDate),
						JIVariant.makeVariant(endDate) })[0]);
	}

	/* (non-Javadoc)
	 * @see com.ipc.oce.objects.OCAbstractManager#loadMetadata()
	 */
	@Override
	protected _OCCommonMetadataObject loadMetadata() throws JIException {
		return OCApp.getInstance(getAssociatedSessionID()).getMetadata().getDocumentJournals().find(managerName);
	}

	@Override
	public OCDocumentJournalMetadataObject getMetadata() throws JIException {
		return new OCDocumentJournalMetadataObject(super.getMetadata());
	} 
	
	

}
