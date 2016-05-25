package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.objects.OCCatalogMetadataObject;


/**
 * Используется для указания ссылки на элемент справочника в реквизитах других объектов и переменных встроенного языка. Данный объект не содержит средств для модификации элемента справочника, однако позволяет обращаться к его реквизитам и другой информации об элементе. При обращении к свойствам объекта будет выполняться считывание всех данных элемента справочника из базы данных, но оно будет оптимизировано при многократном обращении к данному элементу как через этот объект, так и через другое равное ему значение.
 * @author Konovalov
 *
 */
public class OCCatalogRef extends _OCCommonRef implements AttributeGetter{

	public OCCatalogRef(OCObject object) {
		super(object);
	}

	public OCCatalogRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCatalogRef(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	
	/**
	 * Набор свойств содержит значения реквизитов справочника. Доступ к значению осуществляется по имени, как оно задано в конфигураторе. 
	 * @param attributeName
	 * @return
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
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
	 * Тип: Число, Строка. Содержит код элемента справочника. 
	 * Строка или число в зависимости от настроек справочника в конфигураторе. 
	 * @return Object - это инстанс Integer или String
	 * @throws JIException ошибка работы с DCOM
	 */
	public Object getCode() throws JIException {
		JIVariant var = get("Code");
		Object res = null;
		try {
			res = var.getObjectAsString2();
		} catch (java.lang.IllegalStateException cce) {
			res = var.getObjectAsInt();
			if (res != null) {
				res = ((String) res).trim();
			}
		}
		return res;
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
	 * Содержит признак пометки на удаление элемента справочника.
	 * @return true - элемент помечен как удаленный, false - иначе.
	 * @throws JIException ошибка DCOM.
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * Указывает, что данный элемент справочника является предопределенным элементом. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isPredefined() throws JIException{
		return get("Predefined").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на родителя элемента справочника. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getParent() throws JIException{
		return new OCCatalogRef(get("Parent"));
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
	 * Содержит ссылку на элемент справочника. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getRef() throws JIException{
		return new OCCatalogRef(super.getRef());
	}
	
	/**
	 * Получает объект для модификации и записи элемента, на котором в данный момент спозиционирована выборка. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogObject getObject() throws JIException{
		return new OCCatalogObject(super.getObject());
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных справочника.
	 * @return
	 * @throws JIException
	 */
	public OCCatalogMetadataObject getMetadata() throws JIException{
		return new OCCatalogMetadataObject(super.getMetadata());
	}
	
	/**
	 * Строка, включающая наименование элемента и наименования всех вышестоящих элементов.
	 * Наименования выводятся слева направо, начиная с самого верхнего уровня, разделяются символом "/". 
	 * @return
	 * @throws JIException
	 */
	public String getFullDescr() throws JIException{
		return callMethodA("FullDescr").getObjectAsString2();
	}
	
	/**
	 * Строка, включающая код элемента и коды всех вышестоящих элементов.
	 * Коды выводятся слева направо, начиная с самого верхнего уровня, разделяются символом "/". 
	 * @return
	 * @throws JIException
	 */
	public String getFullCode() throws JIException{
		return callMethodA("FullCode").getObjectAsString2();
	}
	
	/**
	 * Определяет подчиненность элемента справочника группе с учетом всех уровней иерархии. 
	 * @param ref СправочникСсылка. Группа (или элемент для иерархического справочника с иерархией элементов), для которой определяется принадлежность элемента. 
	 * @return Истина - элемент подчинен группе; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isBelongsToItem(OCCatalogRef ref) throws JIException{
		return callMethodA("BelongsToItem", new Object[]{new JIVariant(ref.dispatch())})[0].getObjectAsBoolean();
	}
	

	/**
	 * Создает новый элемент справочника копированием существующего. 
	 * Примечание:
	 * Использование метода не приводит к записи созданного объекта в базу данных. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogObject copy() throws JIException{
		return new OCCatalogObject(super.copy());
	}
	
	/**
	 * Получает уровень элемента справочника. 
	 * Примечание:
	 * Имеет смысл только для многоуровневых справочников. Следует учитывать, что уровень элемента может меняться, например, при переносе в другую группу (смене родителя). Для элемента, не имеющего родителя, уровень будет равняться 0.
	 * Если ссылка пустая, вызов метода вызывает исключение. 
	 * @return Уровень элемента справочника.
	 * @throws JIException
	 */
	public Integer getLevel() throws JIException{
		return callMethodA("Level").getObjectAsInt();
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
	
	
}
