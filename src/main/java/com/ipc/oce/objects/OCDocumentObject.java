package com.ipc.oce.objects;

import java.util.Date;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.StaticFieldInstance;
import com.ipc.oce.metadata.objects.OCDocumentMetadataObject;
import com.ipc.oce.varset.EDocumentPostingMode;
import com.ipc.oce.varset.EDocumentWriteMode;


/**
 * Предназначен для модификации документов. Позволяет изменять реквизиты документа и записывать его. 
 * Важно! У всех событий этого объекта назначены предопределенные процедуры-обработчики. 
 * @author Konovalov
 *
 */
public class OCDocumentObject extends _OCCommonObject implements AttributeBean{
	
	
	public OCDocumentObject(IJIDispatch aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCDocumentObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	public OCDocumentObject(OCObject object) {
		super(object);
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
	 * Установка даты и времени документа
	 * @param date
	 * @throws JIException
	 */
	public void setDate(Date date) throws JIException{
		put("Date", new JIVariant(date));
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
	 * Содержит номер документа. Тип значения зависит от установки в
	 * конфигураторе представления номера документа.
	 * 
	 * @return
	 * @throws JIException
	 */
	public String getNumberAsString() throws JIException{
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
	 * Содержит номер документа. Тип значения зависит от установки в
	 * конфигураторе представления номера документа.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Object getNumber() throws JIException {
		JIVariant var = get("Number");
		Object res = null;
		if (var.getType() == JIVariant.VT_BSTR) {
			res = var.getObjectAsString2();
		} else {
			res = Integer.valueOf(var.getObjectAsInt());
		}
		return res;
	}
	
	/**
	 * Установка номера документа
	 * @param newNumber
	 * @throws JIException
	 */
	public void setNumber(Object newNumber) throws JIException{
		put("Number", JIVariant.makeVariant(newNumber));
	}
	
	/**
	 * Предоставляет доступ к коллекции наборов записей движений документа. Свойства коллекции содержат наборы записей движений документа, которые включены для данного документа в конфигурации. Имена свойств совпадают с именами объектов конфигурации, по которым могут совершаться движения, как они заданы в конфигураторе. 
	 * @return
	 * @throws JIException
	 */
	public OCRegisterRecordsCollection getRegisterRecords() throws JIException{
		return new OCRegisterRecordsCollection(get("RegisterRecords"));
	}
	
	/**
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые значения, связанные с объектом, на время выполнения некоторых операций, без изменения объекта. Например, при обработке событий в подписке на события.
	 * @return
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException{
		return new OCStructure(get("AdditionalProperties"));
	}
	
	/**
	 * Используется для управления обменом данных. С помощью данного свойства настраивается состав узлов-получателей, для которых будут регистрироваться изменения данных, узел-отправитель, из которого получена записываемая информация, а также устанавливается режим Загрузка, указывающий, что выполняется перенос информации. 
	 * @return
	 * @throws JIException
	 */
	public OCDataExchangeParameters getDataExchange() throws JIException{
		return new OCDataExchangeParameters(get("DataExchange"));
	}
	
	public OCSequenceRecordSetCollection getBelongingToSequences() throws JIException{
		return new OCSequenceRecordSetCollection(get("BelongingToSequences"));
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
	 * Установка признака проведенности документа. Свойство может быть изменено. Если понятие проведенности документа отличается от стандартного (проведение документа говорит от наличии движений регистров), то разработчик конфигурации предоставляет пользователям средства формирования движений, а данное свойство может быть использовано для иных, определяемых конкретной задачей, целей (например, построение специального отчета, индикации того, что данный документ уже оказывает влияние на итоговые или учетные данные, и т.д.) 
	 * @param postedMark
	 * @throws JIException
	 */
	public void setPosted(Boolean postedMark) throws JIException{
		put("Posted", new JIVariant(postedMark));
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
	 * Набор свойств содержит значения реквизитов документа. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param attributeName
	 * @return значение простого типа или ссылку на объект
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException {
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Установка значения атрибута документа
	 * @param attributeName
	 * @param variant
	 * @throws JIException
	 */
	public void setAttributeValue(String attributeName, OCVariant variant) throws JIException {
		put(attributeName, variant);
	}
	
	/**
	 * Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе. 
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException {
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	@Override
	/**
	 * Предоставляет доступ к объекту описания метаданных документа. Другой путь получения того же значения - через свойство глобального контекста Метаданные (OCConfigurationMetadataObject).
	 * @return
	 * @throws JIException
	 */
	public OCDocumentMetadataObject getMetadata() throws JIException {
		return new OCDocumentMetadataObject(super.getMetadata());
	}
	
	/**
	 * Записывает документ в базу данных. Для документов с поддержкой
	 * уникальности номеров в процессе записи, если нужно, проверяется
	 * уникальность номера
	 * 
	 * @param writeMode
	 *            EDocumentWriteMode Позволяет выбрать один из возможных режимов
	 *            записи. (Значение по умолчанию: Запись )
	 * @param postingMode
	 *            EDocumentPostingMode Позволяет выбрать один из возможных
	 *            режимов проведения. (Значение по умолчанию: Неоперативный)
	 * @throws JIException
	 */
	public void write(EDocumentWriteMode writeMode, EDocumentPostingMode postingMode) throws JIException{
		if (preWH != null) {
			preWH.handle(this);
		}
		callMethod("Write", new Object[] {
				writeMode != null ? ocObject2Dispatch(writeMode) : null,
				postingMode != null ? ocObject2Dispatch(postingMode) : null });
		if (postWH != null) {
			postWH.handle(this);
		}
	}
	
	/**
	 * Записывает документ в базу данных. Для документов с поддержкой
	 * уникальности номеров в процессе записи, если нужно, проверяется
	 * уникальность номера
	 * 
	 * @param writeMode
	 *            EDocumentWriteMode Позволяет выбрать один из возможных режимов
	 *            записи. (Значение по умолчанию: Запись )
	 * @param postingMode
	 *            EDocumentPostingMode Позволяет выбрать один из возможных
	 *            режимов проведения. (Значение по умолчанию: Неоперативный)
	 * @throws JIException
	 */
	public void write(StaticFieldInstance writeMode, StaticFieldInstance postingMode) throws JIException{
		if (preWH != null) {
			preWH.handle(this);
		}
		callMethod("Write", new Object[] {
				writeMode != null ? ocObject2Dispatch(writeMode) : null,
				postingMode != null ? ocObject2Dispatch(postingMode) : null });
		if (postWH != null) {
			postWH.handle(this);
		}
	}
	
	/**
	 * Определяет, был ли изменен объект после считывания из базы данных. 
	 * @return
	 * @throws JIException 
	 */
	public Boolean isModified() throws JIException{
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Получает момент времени документа. 
	 * @return
	 * @throws JIException
	 */
	public OCPointInTime getPointInTime() throws JIException{
		return new OCPointInTime(callMethodA("PointInTime"));
	}
	
	@Override
	/**
	 * Создает новый документ копированием существующего. 
	 * Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return OCDocumentObject
	 * @throws JIException
	 */
	public OCDocumentObject copy() throws JIException {
		return new OCDocumentObject(super.copy());
	}
	
	/**
	 * Устанавливает режим определения времени при записи документа. 
	 * @throws JIException
	 */
	public void setTime() throws JIException{
		callMethod("SetTime");
	}
	
	/**
	 * Устанавливает новый номер документа для заданного префикса номера. 
	 * Для документов с нумерацией в пределах периода, номер устанавливается в пределах периода, соответствующего установленной дате документа. Если дата - пустая, процедура установит номер в пределах периода соответствующего пустой дате. 
	 * @param numPrefix
	 * @throws JIException 
	 */
	public void setNewNumber(String numPrefix) throws JIException {
		if (numPrefix == null) {
			numPrefix = "";
		}
		
		callMethod("SetNewNumber", new Object[]{
				JIVariant.makeVariant(numPrefix)
				});
	}
	
	/**
	 * Устанавливает/снимет пометку на удаление документа. Документ при этом не удаляется из базы данных. Окончательное удаление помеченных документов происходит при удалении помеченных объектов. 
	 * @param mark  Признак установки / снятие пометки на удаление. Истина - пометка будет установлена; Ложь - пометка будет снята
	 * @throws JIException
	 */
	public void setDeletionMark(Boolean mark) throws JIException{
		callMethod("SetDeletionMark", new Object[]{JIVariant.makeVariant(mark)});
	}
	
	
	/**
	 * Устанавливает значение для нового (созданного и еще не записанного) объекта, которое будет назначено при записи в качестве ссылки. Значение не может равняться ссылке какого-либо из имеющихся в базе данных объекта данного типа. Уникальность ссылки проверяется при записи объекта. 
	 * См. также:
	 * ДокументМенеджер, метод ПолучитьСсылку
	 * @param ref Ссылка, которая будет назначена при записи нового объекта
	 * @throws JIException
	 */
	public void setNewObjectRef(OCDocumentRef ref) throws JIException{
		callMethod("SetNewObjectRef", new Object[]{new JIVariant(ref.dispatch())});
	}
}
