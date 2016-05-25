package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCChartsOfAccountsMetadataObject;
import com.ipc.oce.varset.OCAccountType;


public class OCChartOfAccountsRef extends _OCCommonRef implements AttributeGetter{

	public OCChartOfAccountsRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartOfAccountsRef(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	public OCChartOfAccountsRef(OCObject object) {
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
	public String getCode() throws JIException {
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
		return new OCChartOfAccountsRef(super.getRef());
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных счета. Другой путь получения того же значения - через свойство глобального контекста Метаданные. Например: Метаданные.ПланыСчетов.Основной. 
	 */
	public OCChartsOfAccountsMetadataObject getMetadata() throws JIException{
		return new OCChartsOfAccountsMetadataObject(super.getMetadata());
	}
	
	/**
	 * Получает объект для модификации и записи элемента, на котором в данный момент спозиционирована выборка. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfAccountsObject getObject() throws JIException{
		return new OCChartOfAccountsObject(super.getObject());
	}
	
	@Override
	public OCChartOfAccountsObject copy() throws JIException {
		return new OCChartOfAccountsObject(super.copy());
	}

	/**
	 * Преобразует код счета в соответствии с маской.
	 * Если маска представлена в виде "@@@.@@@.@@", то для счета "41.1" метод возвратит строку " 41. 1". При этом к символам счета будут добавлены пробелы, а числовые фрагменты кода прижимаются вправо.
	 * Для масок, которые используют другие символы, возвращается собственно значение кода счета.  
	 * @return
	 * @throws JIException
	 */
	public String getCodeOrder() throws JIException{
		return callMethodA("GetCodeOrder").getObjectAsString2();
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
	 * Получает уровень счета. Имеет смысл только для многоуровневых планов счетов. Следует учитывать, что уровень элемента может меняться, например, при переносе в другую группу (смене родителя).
	 * Уровень счета начинается начиная с 0. Для элемента, не имеющего родителя, уровень будет равен 0.
	 * Если ссылка пустая, вызов метода вызывает исключение. 
	 * @return
	 * @throws JIException
	 */
	public Integer getLevel() throws JIException{
		return callMethodA("Level").getObjectAsInt();
	}

}
