package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;


/**
 * Объект этого типа возвращается методами Выбрать и ВыбратьИерархически у объекта типа СправочникМенеджер.<Имя справочника> и представляет собой специализированный способ перебора элементов справочника. Обход элементов выполняется системой динамически. Это означает, что использование выборки не считывает все элементы сразу, а выбирает их порциями из базы данных. Такой подход позволяет достаточно быстро обходить с помощью выборки большие списки справочников и не загружает в память всех элементов выборки. 
 * @author Konovalov
 *
 */
public class OCCatalogSelection extends _OCCommonSelection implements AttributeGetter{

	public OCCatalogSelection(OCObject object) {
		super(object);
	}

	public OCCatalogSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCatalogSelection(JIVariant aDispatch) throws JIException {
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
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * Тип: Число, Строка. Содержит код элемента справочника. Строка или число в зависимости от настроек справочника в конфигураторе. 
	 * @return
	 * @throws JIException
	 */
	public Object getCode() throws JIException{
		JIVariant var = get("Code");
		Object res = null;
		try {
			res = var.getObjectAsInt();
		} catch (java.lang.IllegalStateException cce) {
			res = var.getObjectAsString2();
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
	 * Содержит признак пометки на удаление элемента справочника
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на элемент справочника. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getRef() throws JIException{
		return new OCCatalogRef(get("Ref"));
	}
	
	/**
	 * Получает объект для модификации и записи элемента, на котором в данный момент спозиционирована выборка. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogObject getObject() throws JIException{
		return new OCCatalogObject(callMethodA("GetObject"));
	}
	
	/**
	 * Получает уровень элемента справочника в выборке, полученной с помощью метода ВыбратьИерархически. Уровень выдается начиная с 0. То есть при обходе верхнего уровня выборки уровень будет равняться 0. Выдаваемые значения зависят от того, с каким отбором по родителю выполнялась выборка. Если отбор по родителю не производился, то уровень в выборке будет совпадать с уровнем элемента в справочнике. 
	 * @return
	 * @throws JIException
	 */
	public Integer getLevelInSelection() throws JIException{
		return callMethodA("LevelInSelection").getObjectAsInt();
	}
	
	/**
	 * Позволяет определить является ли элемент справочника группой. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isFolder() throws JIException{
		return get("IsFolder").getObjectAsBoolean();
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
	 * Содержит ссылку на родителя элемента справочника. Имеет смысл только для многоуровневых справочников. 
	 * @return
	 * @throws JIException
	 */
	public OCCatalogRef getParent() throws JIException{
		return new OCCatalogRef(get("Parent"));
	}
}
