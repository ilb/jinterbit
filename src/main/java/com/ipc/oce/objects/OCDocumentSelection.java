package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;


/**
 * Формирует выборку документов за определенный период. Получаемая выборка может быть упорядочена по реквизитам документа. 
 * @author Konovalov
 *
 */
public class OCDocumentSelection extends _OCCommonSelection implements AttributeGetter {
	
	public OCDocumentSelection(OCObject object) {
		super(object);
	}

	public OCDocumentSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCDocumentSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Содержит номер документа. Тип значения зависит от установки в конфигураторе представления номера документа. 
	 * @return
	 * @throws JIException
	 */
	public int getNumberAsInt() throws JIException {
		JIVariant var = get("Number");
		try {
			return var.getObjectAsInt();
		} catch (java.lang.IllegalStateException ise) {
			return Integer.valueOf(var.getObjectAsString2());
		}
	}
	/**
	 *  Содержит номер документа. Тип значения зависит от установки в конфигураторе представления номера документа. 
	 * @return
	 * @throws JIException
	 */
	public String getNumberAsString() throws JIException{
		JIVariant var = get("Number");
		String res = null;
		try {
			res = var.getObjectAsString2();
		} catch (java.lang.IllegalStateException cce) {
			res = "" + var.getObjectAsInt();
		}
		return res;
	}
	public Object getNumber() throws JIException{
		JIVariant var = get("Number");
		Object res = null;
		try {
			res = var.getObjectAsInt();
		} catch (java.lang.IllegalStateException cce) {
			res = var.getObjectAsString2();
		}
		return res;
	}
	/**
	 * Содержит признак пометки на удаление документа.
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException {
		return get("DeletionMark").getObjectAsBoolean();
	}
	/**
	 * Содержит дату и время документа. 
	 * @return Date
	 * @throws JIException
	 */
	public Date getDate() throws JIException {
		return get("Date").getObjectAsDate();
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
	 * @return OCDocumentRef
	 * @throws JIException
	 */
	public OCDocumentRef getRef() throws JIException{
		return new OCDocumentRef(get("Ref"));
	}
	
	/**
	 * Набор свойств содержит значения реквизитов документа. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param attributeName имя реквизита (атрибута)
	 * @return 
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе. 
	 * @param tabularSectionName имя табличной секции
	 * @return 
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * Получает объект для модификации и записи документа, на котором в данный момент спозиционирована выборка
	 * @return
	 * @throws JIException 
	 */
	public OCDocumentObject getObject() throws JIException{
		return new OCDocumentObject(callMethodA("GetObject"));
	}
}
