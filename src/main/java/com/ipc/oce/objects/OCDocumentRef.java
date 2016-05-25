package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;


/**
 * Используется для указания ссылки на документ в реквизитах других объектов и переменных встроенного языка. Данный объект не содержит средств для модификации документа, однако позволяет обращаться к его реквизитам и другой информации об документе. При обращении к свойствам объекта будет выполняться считывание всех данных документа из базы данных, но оно будет оптимизировано при многократном обращении к данному документу как через этот объект, так и через другое равное ему значение.
 * @author Konovalov
 *
 */
public class OCDocumentRef extends _OCCommonRef implements AttributeGetter{
	public OCDocumentRef(OCObject object) {
		super(object);
	}

	public OCDocumentRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentRef(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	
	/**
	 * Содержит дату и время документа. 
	 * @return
	 * @throws JIException
	 */
	public Date getDate() throws JIException{
		return get("Date").getObjectAsDate();
	}
	
	/**
	 * Содержит номер документа. Тип значения зависит от установки в конфигураторе представления номера документа. 
	 * @return
	 * @throws JIException
	 */
	public int getNumberAsInt() throws JIException{
		return get("Number").getObjectAsInt();
	}
	
	/**
	 *  Содержит номер документа. Тип значения зависит от установки в конфигураторе представления номера документа. 
	 * @return
	 * @throws JIException
	 */
	public String getNumberAsString() throws JIException {
		JIVariant var = get("Number");
		String res = null;
		try {
			res = var.getObjectAsString2();
		} catch (ClassCastException cce) {
			res = "" + var.getObjectAsInt();
		}
		return res;
	}
	
	/**
	 * Содержит признак пометки на удаление документа.
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * признак проведенности документа. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isPosted() throws JIException{
		return get("Posted").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на документ. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentRef getRef() throws JIException{
		return new OCDocumentRef(super.getRef());
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных документа. Другой путь получения того же значения - через свойство глобального контекста Метаданные (OCConfigurationMetadataObject).
	 * @return
	 * @throws JIException
	 */
	public OCDocumentMetadataObject getMetadata() throws JIException{
		return new OCDocumentMetadataObject(super.getMetadata());
	}
	
	/**
	 * Получает момент времени документа. 
	 * @return
	 * @throws JIException
	 */
	public OCPointInTime getPointInTime() throws JIException{
		return new OCPointInTime(callMethodA("PointInTime"));
	}
	
	/**
	 * Получает объект для модификации и записи документа, на котором в данный момент спозиционирована выборка
	 * @return
	 * @throws JIException 
	 */
	public OCDocumentObject getObject() throws JIException{
		return new OCDocumentObject(super.getObject());
	}
	
	
	/**
	 * Создает новый документ копированием существующего. 
	 * Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return
	 * @throws JIException
	 */
	public OCDocumentObject copy() throws JIException{
		return new OCDocumentObject(super.copy());
	}
	
	/**
	 * Набор свойств содержит значения реквизитов документа. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param attributeName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе. 
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
	}
}
