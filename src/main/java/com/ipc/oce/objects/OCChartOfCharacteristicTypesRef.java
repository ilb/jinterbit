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
import com.ipc.oce.metadata.objects.OCChartOfCharacteristicTypeMetadataObject;

/**
 * Используется для указания ссылки на элемент плана видов характеристик в реквизитах других объектов и переменных встроенного языка. Данный объект не содержит средств для модификации элемента плана видов характеристик, однако позволяет обращаться к его реквизитам и другой информации об элементе. При обращении к свойствам объекта будет выполняться считывание всех данных элемента плана видов характеристик из базы данных, но оно будет оптимизировано при многократном обращении к данному элементу как через этот объект, так и через другое равное ему значение.
 * Возможен обмен с сервером. Сериализуется. XML-сериализация. Поддержка отображения в XDTO; пространство имен: {http://v8.1c.ru/8.1/data/enterprise/current-config}. Имя типа XDTO: ChartOfCharacteristicTypesRef.<Имя плана видов характеристик>. 
 * @author Konovalov
 *
 */
public class OCChartOfCharacteristicTypesRef extends _OCCommonRef implements AttributeGetter{

	/**
	 * @param aDispatch
	 */
	public OCChartOfCharacteristicTypesRef(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	/**
	 * @param object
	 */
	public OCChartOfCharacteristicTypesRef(OCObject object) {
		super(object);
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
	 * Содержит код элемента вида характеристики
	 * @return
	 * @throws JIException
	 */
	public String getCode() throws JIException{
		return get("Code").getObjectAsString2();
	}
	
	/**
	 * Содержит наименование элемента вида характеристики
	 * @return
	 * @throws JIException
	 */
	public String getDescription() throws JIException{
		return get("Description").getObjectAsString2();
	}
	
	/**
	 *  Содержит признак пометки на удаление элемента вида характеристики
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 *  Если Истина, то указывает, что данный вид характеристики определен в метаданных и над ним нельзя производить некоторые операции
	 * @return Boolean
	 * @throws JIException
	 */
	public Boolean isPredefined() throws JIException{
		return get("Predefined").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на родителя элемента вида характеристики. Имеет смысл только для многоуровневых видов характеристик. 
	 * @return OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef getParent() throws JIException{
		return new OCChartOfCharacteristicTypesRef(get("Parent"));
	}
	
	/**
	 *  Содержит ссылку на элемент плана вида характеристики. Это значение может быть записано в базу данных для полей соответствующего типа. 
	 */
	public OCChartOfCharacteristicTypesRef getRef() throws JIException{
		return new OCChartOfCharacteristicTypesRef(super.getRef());
	}
	
	/**
	 * Тип значения характеристики
	 * @return OCTypeDescription
	 * @throws JIException
	 */
	public OCTypeDescription getValueType() throws JIException{
		return new OCTypeDescription(get("ValueType"));
	}
	
	/**
	 * Позволяет определить, является ли элемент вида характеристики группой
	 * @return
	 * @throws JIException
	 */
	public Boolean isFolder() throws JIException{
		return get("IsFolder").getObjectAsBoolean();
	}
	
	/**
	 * Предоставляет доступ к объекту описания метаданных плана видов характеристик. Другой путь получения того же значения - через свойство глобального контекста Метаданные. Например: Метаданные.ПланыВидовХарактеристик.СвойстваОбъектов
	 */
	public OCChartOfCharacteristicTypeMetadataObject getMetadata() throws JIException{
		return new OCChartOfCharacteristicTypeMetadataObject(super.getMetadata());
	}
	
	/**
	 * Получает по ссылке объект, предназначенный для модификации плана видов характеристик. 
	 */
	public OCChartOfCharacteristicTypesObject getObject() throws JIException{
		return new OCChartOfCharacteristicTypesObject(super.getObject());
	}
	
	/**
	 * Определяет подчиненность элемента плана видов характеристик группе с учетом всех уровней иерархии
	 * @param Группа (или элемент для иерархического плана видов характеристик с иерархией элементов), для которой определяется принадлежность элемента. 
	 * @return Истина - элемент подчинен группе; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isBelongsToItem(OCChartOfCharacteristicTypesRef ref) throws JIException{
		return callMethodA("BelongsToItem", new Object[]{new JIVariant(ocObject2Dispatch(ref))})[0].getObjectAsBoolean();
	}
	
	/**
	 * Создает новый элемент плана видов характеристик копированием существующего. Использование метода не приводит к записи созданного объекта в базу данных
	 */
	public OCChartOfCharacteristicTypesObject copy() throws JIException{
		return new OCChartOfCharacteristicTypesObject(super.copy());
	}
	
	/**
	 * Получает уровень элемента плана видов характеристик. Имеет смысл только для многоуровневых планов видов характеристик. Следует учитывать, что уровень элемента может меняться, например, при переносе в другую группу (смене родителя). Для элемента, не имеющего родителя, уровень будет равняться 0. Если ссылка пустая, вызов метода вызывает исключение. 
	 * @return номер уровня в иерархии
	 * @throws JIException
	 */
	public Integer getLevel() throws JIException{
		return callMethodA("Level").getObjectAsInt();
	}
	
	
	
}
