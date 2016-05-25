package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCCatalogMetadataObject;
/**
 * Предназначен для модификации элементов справочника. Позволяет изменять значения реквизитов элемента справочника и записывать его.
 * Важно! У всех событий этого объекта назначены предопределенные процедуры-обработчики. Имена процедур соответствует именам событий. Процедуры должны располагаться в модуле справочника.
 * @author Konovalov
 *
 */

public class OCCatalogObject extends _OCCommonObject implements AttributeBean{

	public OCCatalogObject(OCObject object) {
		super(object);
	}

	public OCCatalogObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCatalogObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения реквизитов справочника. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param attributeName
	 * @return
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
	 * Набор свойств содержит табличные части справочника. Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException {
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * Тип: Число, Строка. Содержит код элемента справочника. Строка или число в зависимости от настроек справочника в конфигураторе. 
	 * @return
	 * @throws JIException
	 */
	public Object getCode() throws JIException {
		JIVariant var = get("Code");
		Object res = null;
		try {
			res = var.getObjectAsString2();
		} catch (java.lang.IllegalStateException cce) {
			res = var.getObjectAsInt();
		}
		return res;
	}
	
	/**
	 * Установка кода элемента справочника
	 * @param code
	 * @throws JIException
	 */
	public void setCode(Object code) throws JIException{
		put("Code", JIVariant.makeVariant(code));
	}
	
	/**
	 * Содержит наименование элемента справочника. 
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	/**
	 * Установка поля описания
	 * @param description
	 * @throws JIException
	 */
	public void setDescription(String description) throws JIException {
		put("Description", new JIVariant(description));
	}
	
	/**
	 * Используется для управления обменом данных. С помощью данного свойства настраивается состав узлов-получателей, для которых будут регистрироваться изменения данных, узел-отправитель, из которого получена записываемая информация, а также устанавливается режим Загрузка, указывающий, что выполняется перенос информации. 
	 * @return OCDataExchangeParameters
	 * @throws JIException
	 */
	public OCDataExchangeParameters getDataExchange() throws JIException {
		return new OCDataExchangeParameters(get("DataExchange"));
	}
	
	/**
	 * Содержит признак пометки на удаление элемента справочника.
	 * 
	 * @return Boolean
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException {
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на элемент справочника. Это значение может быть записано
	 * в базу данных для полей соответствующего типа.
	 * 
	 * @return OCCatalogRef
	 * @throws JIException
	 */
	public OCCatalogRef getRef() throws JIException {
		return new OCCatalogRef(super.getRef());
	}
	
	/**
	 * Указывает, что данный элемент справочника является предопределенным
	 * элементом.
	 * 
	 * @return Boolean
	 * @throws JIException
	 */
	public Boolean isPredefined() throws JIException {
		return get("Predefined").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на родителя элемента справочника. 
	 * @return OCCatalogRef
	 * @throws JIException
	 */
	public OCCatalogRef getParent() throws JIException {
		return new OCCatalogRef(get("Parent"));
	}
	
	/**
	 * Установка ссылки на родитеся элемента справочника
	 * 
	 * @param parentRef
	 *            ссылка на элемент справочника
	 * @throws JIException
	 */
	public void setParent(OCCatalogRef parentRef) throws JIException {
		put("Parent", new JIVariant(parentRef.dispatch()));
	}
	
	/**
	 * Позволяет определить является ли элемент справочника группой. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isFolder() throws JIException {
		return get("IsFolder").getObjectAsBoolean();
	}
	
	/**
	 * Содержит сам объект. Предназначено для получения объекта в модуле объекта или модуле формы. 
	 * @return
	 */
	public OCCatalogObject thisObject() {
		return this; // java impl
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных справочника. Другой путь получения того же значения - через свойство глобального контекста Метаданные. Например: Метаданные.Справочники.Номенклатура. 
	 * @return OCCatalogMetadataObject
	 * @throws JIException
	 */
	public OCCatalogMetadataObject getMetadata() throws JIException {
		return new OCCatalogMetadataObject(super.getMetadata());
	}
	
	/**
	 * Определяет, был ли изменен объект после считывания из базы данных.
	 * 
	 * @return Boolean
	 * @throws JIException
	 */
	public Boolean isModified() throws JIException {
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Строка, включающая наименование элемента и наименования всех вышестоящих
	 * элементов. Наименования выводятся слева направо, начиная с самого
	 * верхнего уровня, разделяются символом "/".
	 * 
	 * @return String
	 * @throws JIException
	 */
	public String getFullDescr() throws JIException {
		return callMethodA("FullDescr").getObjectAsString2();
	}
	
	/**
	 * Строка, включающая код элемента и коды всех вышестоящих элементов.
	 * Коды выводятся слева направо, начиная с самого верхнего уровня, разделяются символом "/". 
	 * @return
	 * @throws JIException
	 */
	public String getFullCode() throws JIException {
		return callMethodA("FullCode").getObjectAsString2();
	}
	
	/**
	 * Позволяет для нового (созданного и еще не записанного) объекта получить ранее установленное методом УстановитьСсылкуНового значение ссылки. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getNewObjectRef() throws JIException {
		return new OCCatalogRef(callMethodA("GetNewObjectRef"));
	}
	
	/**
	 * Определяет подчиненность элемента справочника группе с учетом всех
	 * уровней иерархии. Определяет подчиненность группе непосредственно на
	 * момент вызова метода путем последовательного считывания всех вышестоящих
	 * элементов.
	 * 
	 * @param ref
	 *            Тип: СправочникСсылка. Группа (элемент для иерархического
	 *            справочника с иерархией элементов), для которой определяется
	 *            принадлежность элемента.
	 * @return Истина - элемент подчинен группе; Ложь - в противном случае.
	 * @throws JIException
	 */
	public Boolean isBelongsToItem(OCCatalogRef ref) throws JIException {
		return callMethodA("BelongsToItem", new Object[]{new JIVariant(ref.dispatch())})[0].getObjectAsBoolean();
	}
	
	/**
	 * Создает новый элемент справочника копированием существующего.
	 * 
	 * @return OCCatalogObject
	 * @throws JIException
	 */
	public OCCatalogObject copy() throws JIException {
		return new OCCatalogObject(super.copy());
	}
	
	/**
	 * Получает уровень элемента справочника. 
	 * Примечание:
	 * Имеет смысл только для многоуровневых справочников. Следует учитывать, что уровень элемента может меняться, например, при переносе в другую группу (смене родителя).
	 * Для элемента, не имеющего родителя, уровень будет равняться 0. 
	 * @return
	 * @throws JIException
	 */
	public Integer getLevel() throws JIException{
		return callMethodA("Level").getObjectAsInt();
	}
	
	/**
	 * Устанавливает новый код. Если в коде присутствует числовая часть, то
	 * новый код автоматически устанавливается следующим за имеющимся
	 * максимальным. При этом определяется текущий максимальный код среди
	 * элементов данного справочника. Если передан префикс, то новый код
	 * устанавливается следующим образом: выполняется поиск максимального кода
	 * среди кодов, имеющих данный префикс, новый код выбирается как следующий
	 * от найденного кода.
	 * 
	 * @param newCodePref
	 *            Строковое значение префикса. Если префикс указан, то новый код
	 *            будет формироваться с учетом префикса.
	 * @throws JIException
	 */
	public void setNewCode(String newCodePref) throws JIException {
		callMethod("SetNewCode", new Object[]{JIVariant.makeVariant(newCodePref)});
	}
	
	/**
	 * Устанавливает/снимет пометку на удаление. Объект при этом не удаляется из базы данных. Окончательное удаление помеченных элементов справочника происходит при удалении помеченных объектов. 
	 * Если параметр установлен в Истина, то будут помечены на удаление, кроме данного элемента, все его подчиненные элементы в данном справочнике и во всех подчиненных справочниках.
	 * Если параметр установлен в Ложь, то будет помечен на удаление только данный элемент.
	 * Значение по умолчанию: Истина 
	 * @param mark
	 * @throws JIException
	 */
	public void setDeletionMark(Boolean mark) throws JIException {
		callMethod("SetDeletionMark", new Object[]{JIVariant.makeVariant(mark)});
	}
	
	/**
	 * Устанавливает значение для нового (созданного и еще не записанного) объекта, которое будет назначено при записи в качестве ссылки. Значение не может равняться ссылке какого-либо из имеющихся в базе данных объекта данного типа. Уникальность ссылки проверяется при записи объекта. 
	 * @param ref Ссылка, которая будет назначена при записи нового объекта. 
	 * @throws JIException
	 */
	public void setNewObjectRef(OCCatalogRef ref) throws JIException {
		callMethod("SetNewObjectRef", new Object[]{JIVariant.makeVariant(ref.dispatch())});
	}
	
	/**
	 * Содержит ссылку на владельца элемента справочника. ( СправочникСсылка.<Имя справочника>, ПланВидовХарактеристикСсылка.<Имя плана видов характеристик>, ПланСчетовСсылка.<Имя плана счетов>, ПланВидовРасчетаСсылка.<Имя плана видов расчета>, ПланОбменаСсылка.<Имя плана обмена>.)
	 * Имеет смысл только для подчиненных справочников. 
	 * @param <T> тип возвращаемого объекта
	 * @return ссылка на объект-владелец.
	 * @throws JIException
	 */
	public <T> T getOwner() throws JIException {
		JIVariant var = get("Owner");
		if (var.getType() == JIVariant.VT_EMPTY) {
			return null;
		}
		OCVariant oVar = new OCVariant(var);
		return oVar.value();
	}
	
	/**
	 * Установка ссылки на владельца элемента справочника.
	 * @param owner ссылка на объект-владелец.
	 * @throws JIException
	 */
	public void setOwner(_OCCommonRef owner) throws JIException {
		put("Owner", new JIVariant(owner.dispatch()));
	}
}
