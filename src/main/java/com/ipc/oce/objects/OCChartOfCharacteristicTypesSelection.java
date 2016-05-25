/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.OCTypeDescription;

/**
 * @author Konovalov
 *
 */
public class OCChartOfCharacteristicTypesSelection extends _OCCommonSelection implements AttributeGetter{

	/**
	 * @param object
	 */
	public OCChartOfCharacteristicTypesSelection(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCChartOfCharacteristicTypesSelection(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesSelection(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Набор свойств содержит значения реквизитов плана видов характеристик. Доступ к значению осуществляется по имени, как оно задано в конфигураторе
	 * @param имя реквизита
	 * @return OCVariant
	 * @throws JIException
	 */
	public OCVariant getAttributeValue(String attributeName) throws JIException{
		return new OCVariant(get(attributeName));
	}
	
	/**
	 * ПланВидовХарактеристикТабличнаяЧасть.<Имя плана видов характеристик>.<Имя табличной части>. Набор свойств содержит табличные части плана видов характеристик. Доступ к табличной части осуществляется по имени, как оно задано в конфигураторе. 
	 * @param tabularSectionName
	 * @return
	 * @throws JIException
	 */
	public OCTabularSectionManager getTabularSection(String tabularSectionName) throws JIException{
		return new OCTabularSectionManager(get(tabularSectionName));
	}
	
	/**
	 * Содержит код элемента плана видов характеристики
	 * @return
	 * @throws JIException
	 */
	public String getCode() throws JIException{
		return get("Code").getObjectAsString2();
	}
	
	/**
	 * Содержит наименование элемента плана видов характеристик
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	/**
	 * Содержит признак пометки на удаление элемента плана видов характеристик
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 *  Если Истина, то над данным планом видов характеристик нельзя производить некоторые операции
	 * @return
	 * @throws JIException
	 */
	public Boolean isPredefined() throws JIException{
		return get("Predefined").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на родителя элемента плана видов характеристик
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef getParent() throws JIException{
		return new OCChartOfCharacteristicTypesRef(get("Parent"));
	}
	
	/**
	 * Содержит ссылку на элемент плана видов характеристик. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef getRef() throws JIException{
		return new OCChartOfCharacteristicTypesRef(get("Ref"));
	}
	
	/**
	 * Тип значения характеристики
	 * @return
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException{
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Позволяет определить является ли элемент плана видов характеристик группой.
	 * @return
	 * @throws JIException
	 */
	public Boolean isFolder() throws JIException{
		return get("IsFolder").getObjectAsBoolean();
	}
	
	/**
	 * Получает объект, предназначенный для модификации элемента
	 * @return OCChartOfCharacteristicTypesObject
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesObject getObject() throws JIException{
		return new OCChartOfCharacteristicTypesObject(callMethodA("GetObject"));
	}
	
	/**
	 * Получает уровень элемента плана видов характеристик в иерархической выборке
	 * @return
	 * @throws JIException
	 */
	public Integer getLevelInSelection() throws JIException{
		return callMethodA("LevelInSelection").getObjectAsInt();
	}

}
