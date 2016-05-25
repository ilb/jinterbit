package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.varset.OCAccountType;


public class OCChartOfAccountsSelection extends _OCCommonSelection implements AttributeGetter{

	public OCChartOfAccountsSelection(OCObject object) {
		super(object);
	}

	public OCChartOfAccountsSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartOfAccountsSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
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
	 * ПланСчетовТабличнаяЧасть.<Имя плана счетов>.<Имя табличной части>. Набор свойств содержит табличные части плана счетов. Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе. 
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
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
	
	/**
	 * Определяет вид счета. На активном счете будет всегда дебетовый остаток, на пассивном кредитовый, на активно пассивном, если остаток больше нуля, то дебетовый. если меньше, то кредитовый. 
	 * @return
	 * @throws JIException
	 */
	public OCAccountType getType() throws JIException{
		return new OCAccountType(get("Type"));
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
	 * 
	 * @return Если Истина, то счет не участвует общем балансе. 
	 * @throws JIException
	 */
	public Boolean isOffBalance() throws JIException{
		return get("OffBalance").getObjectAsBoolean();
	}
	
	/**
	 * Содержит наименование элемента плана счетов. 
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	/**
	 * Содержит признак пометки на удаление счета. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * Содержит строку с порядком, по которому можно делать упорядочивание выборки. 
	 * @return
	 * @throws JIException
	 */
	public String getOrder() throws JIException{
		return get("Order").getObjectAsString2();
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
		return new OCChartOfAccountsRef(get("Ref"));
	}
	
	/**
	 * Получает объект для модификации и записи элемента, на котором в данный момент спозиционирована выборка. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsObject getObject() throws JIException{
		return new OCChartOfAccountsObject(callMethodA("GetObject"));
	}
	
	/**
	 * Получает уровень элемента плана счетов в выборке, полученной с помощью метода ВыбратьИерархически. Уровень выдается начиная с 0. То есть при обходе верхнего уровня выборки уровень будет равняться 0. Выдаваемые значения зависят от того, с каким отбором по родителю выполнялась выборка. Если отбор по родителю не производился, то уровень в выборке будет совпадать с уровнем элемента в плане счетов. 
	 * @return
	 * @throws JIException
	 */
	public Integer getLevelInSelection() throws JIException{
		return callMethodA("LevelInSelection").getObjectAsInt();
	}
}
