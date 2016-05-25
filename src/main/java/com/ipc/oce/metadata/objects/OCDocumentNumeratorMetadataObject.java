package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.varset.OCBusinessProcessNumberPeriodicity;
import com.ipc.oce.varset.OCBusinessProcessNumberType;


public class OCDocumentNumeratorMetadataObject extends _OCCommonMetadataObject {

	public OCDocumentNumeratorMetadataObject(OCObject object) {
		super(object);
	}

	public OCDocumentNumeratorMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentNumeratorMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Максимальная длина номера объекта базы данных (например, документа, бизнес-процесса). 
	 * @return
	 * @throws JIException
	 */
	public int getNumberLength() throws JIException {
		return get("NumberLength").getObjectAsInt();
	}

	/**
	 * Если это свойство установлено в значение Истина, то при создании нового объекта базы данных будет выполняться автоматический контроль уникальности его кода/номера.
	 * @return
	 * @throws JIException
	 */
	public Boolean checkUnique() throws JIException {
		return get("CheckUnique").getObjectAsBoolean();
	}
	
	/**
	 * Устанавливает пределы контроля уникальности номеров и период повторяемости номеров (например, Год, Квартал, Месяц, День). 
	 * @return
	 * @throws JIException
	 */
	public OCBusinessProcessNumberPeriodicity getNumberPeriodicity() throws JIException {
		return new OCBusinessProcessNumberPeriodicity(get("NumberPeriodicity"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым относится данный объект метаданных.
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException {
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}
	
	/**
	 * Определяет тип номера бизнес-процесса (например, Число, Строка). 
	 * @return
	 * @throws JIException
	 */
	public OCBusinessProcessNumberType getNumberType() throws JIException {
		return new OCBusinessProcessNumberType(get("NumberType"));
	}
}
