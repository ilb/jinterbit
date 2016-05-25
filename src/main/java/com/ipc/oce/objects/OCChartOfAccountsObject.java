package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCChartsOfAccountsMetadataObject;
import com.ipc.oce.varset.OCAccountType;


/**
 * Предназначен для модификации элементов плана счетов. Позволяет изменять значения реквизитов элемента плана счетов и записывать его.
 * Важно! У всех событий этого объекта назначены предопределенные процедуры-обработчики. Имена процедур соответствует именам событий. Процедуры должны располагаться в модуле плана счетов.
 * @author Konovalov
 *
 */

public class OCChartOfAccountsObject extends _OCCommonObject implements AttributeBean{

	public OCChartOfAccountsObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartOfAccountsObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCChartOfAccountsObject(OCObject object) {
		super(object);
	}
	
	/**
	 * Набор свойств содержит значения реквизитов плана счетов. Доступ к значению осуществляется по имени, как оно задано в конфигураторе.  
	 * @param attributeName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * Установка значения атрибута документа
	 * @param attributeName
	 * @param variant
	 * @throws JIException
	 */
	public void setAttributeValue(String attributeName, OCVariant variant) throws JIException{
		put(attributeName, variant);
	}
	
	/**
	 * ПланСчетовТабличнаяЧасть.<Имя плана счетов>.<Имя табличной части>. Набор свойств содержит табличные части плана счетов. Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе. 
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * 
	 * @param accountingSignName
	 * @return Если Истина, то по данному ресурсу ведется учет. 
	 * @throws JIException
	 */
	public Boolean getAccountingSign(String accountingSignName) throws JIException{
		return get(accountingSignName).getObjectAsBoolean();
	}
	
	/**
	 * Содержит код элемента плана счетов. Строка или число в зависимости от настроек плана счетов в конфигураторе. 
	 * @return
	 * @throws JIException
	 */
	public String getCode() throws JIException{
		JIVariant var = get("Code");
		String res = null;
		if (var.getType() != JIVariant.VT_BSTR) {
			res = String.valueOf(var.getObjectAsInt());
		} else {
			res = var.getObjectAsString2();
		}
		return res;
	}
	
	public void setCode(Object code) throws JIException{
		put("Code", JIVariant.makeVariant(code));
	}
	
	/**
	 * Определяет вид счета. На активном счете будет всегда дебетовый остаток, на пассивном кредитовый, на активно пассивном, если остаток больше нуля, то дебетовый. если меньше, то кредитовый. 
	 * @return
	 * @throws JIException
	 */
	public OCAccountType getType() throws JIException{
		return new OCAccountType(get("Type"));
	}
	
	/**
	 * Установка вида счета
	 * @param type
	 * @throws JIException
	 */
	public void setType(OCAccountType type) throws JIException{
		putRef("Type", type);
	}
	
	/**
	 * 
	 * @return Если Истина, то счет не участвует общем балансе. 
	 * @throws JIException
	 */
	public Boolean isOffBalance() throws JIException{
		return get("OffBalance").getObjectAsBoolean();
	}
	
	public void setOffBalance(Boolean balance) throws JIException{
		put("OffBalance", new JIVariant(balance));
	}
	
	/**
	 * Содержит наименование элемента плана счетов. 
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	public void setDescription(String description) throws JIException{
		put("Description", new JIVariant(description));
	}
	
	/**
	 * Содержит признак пометки на удаление счета. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	// TODO: Добавить get.ExtDimensionTypes
	
	/**
	 * Содержит строку с порядком, по которому можно делать упорядочивание выборки. 
	 * @return
	 * @throws JIException
	 */
	public String getOrder() throws JIException{
		return get("Order").getObjectAsString2();
	}
	
	public void setOrder(String order) throws JIException{
		put("Order", new JIVariant(order));
	}
	
	/**
	 * Признак, что данный счет определен в метаданных и над ним нельзя производить некоторые операции. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isPredefined() throws JIException{
		return get("Predefined").getObjectAsBoolean();
	}
	/**
	 * Содержит ссылку на родителя счета. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getParent() throws JIException{
		return new OCChartOfAccountsRef(get("Parent"));
	}
	
	/**
	 * Содержит ссылку на счет. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsRef getRef() throws JIException{
		return new OCChartOfAccountsRef(super.getRef());
	}
	
	/**
	 * Содержит сам объект. Предназначено для получения объекта в модуле объекта или модуле формы. 
	 * @return
	 */
	public OCChartOfAccountsObject thisObject(){
		return this;
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных плана счетов. Другой путь получения того же значения - через свойство глобального контекста Метаданные. Например: Метаданные.ПланыСчетов.Основной. 
	 * @return
	 * @throws JIException
	 */
	public OCChartsOfAccountsMetadataObject getMetadata() throws JIException{
		return new OCChartsOfAccountsMetadataObject(super.getMetadata());
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
	 * Определяет подчиненность элемента плана счетов группе с учетом всех уровней иерархии. 
	 * @param ref Группа (или элемент для иерархического плана счетов с иерархией элементов), для которой определяется принадлежность элемента. 
	 * @return  Истина - элемент подчинен группе; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isBelongsToItem(OCChartOfAccountsRef ref) throws JIException{
		return callMethodA("BelongsToItem", new Object[]{new JIVariant(ref.dispatch()) })[0].getObjectAsBoolean();
	}
	
	/**
	 * Создает новый счет копированием существующего. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogObject copy() throws JIException{
		return new OCCatalogObject(super.copy());
	}
	
	/**
	 * Получает уровень счета. Имеет смысл только для многоуровневых планов счетов. Следует учитывать, что уровень элемента может меняться, например, при переносе в другую группу (смене родителя).
	 * Уровень счета начинается начиная с 0. Для элемента, не имеющего родителя, уровень будет равен 0.
	 * Если ссылка пустая, вызов метода вызывает исключение. 
	 * @return
	 * @throws JIException
	 */
	public Integer getLevel() throws JIException{
		return callMethodA("Level").getObjectAsInt();
	}
	
	/**
	 * Устанавливает/снимет пометку на удаление. Объект при этом не удаляется из базы данных. Окончательное удаление помеченных элементов справочника происходит при удалении помеченных объектов. 
	 * Если параметр установлен в Истина, то будут помечены на удаление, кроме данного элемента, все его подчиненные элементы в данном справочнике и во всех подчиненных справочниках.
	 * Если параметр установлен в Ложь, то будет помечен на удаление только данный элемент.
	 * Значение по умолчанию: Истина 
	 * @param mark
	 * @throws JIException
	 */
	public void setDeletionMark(Boolean mark) throws JIException{
		callMethod("SetDeletionMark", new Object[]{JIVariant.makeVariant(mark)});
	}
	
	/**
	 * Устанавливает значение для нового (созданного и еще не записанного) объекта, которое будет назначено при записи в качестве ссылки. Значение не может равняться ссылке какого-либо из имеющихся в базе данных объекта данного типа. Уникальность ссылки проверяется при записи объекта. 
	 * @param ref Ссылка, которая будет назначена при записи нового объекта. 
	 * @throws JIException
	 */
	public void setNewObjectRef(OCChartOfAccountsRef ref) throws JIException{
		callMethod("SetNewObjectRef", new Object[]{JIVariant.makeVariant(ref.dispatch())});
	}
	
}
