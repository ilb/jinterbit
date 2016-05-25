/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.OCStructure;
import com.ipc.oce.OCVariant;
import com.ipc.oce.metadata.OCTypeDescription;
import com.ipc.oce.metadata.objects.OCChartOfCharacteristicTypeMetadataObject;

/**
 * Предназначен для изменения видов характеристик. Позволяет изменять атрибуты видов характеристик и записывать их.
 * XML-сериализация. Поддержка отображения в XDTO; пространство имен: {http://v8.1c.ru/8.1/data/enterprise/current-config}. Имя типа XDTO: ChartOfCharacteristicTypesObject.<Имя плана видов характеристик>. 
 * @author Konovalov
 *
 */
public class OCChartOfCharacteristicTypesObject extends _OCCommonObject implements AttributeGetter{

	/**
	 * @param object
	 */
	public OCChartOfCharacteristicTypesObject(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCChartOfCharacteristicTypesObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesObject(JIVariant aDispatch)
			throws JIException {
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
	 * Может использоваться в тех случаях, когда необходимо хранить некоторые значения, связанные с объектом, на время выполнения некоторых операций, без изменения объекта. Например, при обработке событий в подписке на события.
	 * @return
	 * @throws JIException
	 */
	public OCStructure getAdditionalProperties() throws JIException{
		return new OCStructure(get("AdditionalProperties"));
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
	 * Установка кода элемента вида характеристики
	 * @param code
	 * @throws JIException
	 */
	public void setCode(String code) throws JIException{
		put("Code", new JIVariant(code));
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
	 * Установка наименования элемента вида характеристики
	 * @param description
	 * @throws JIException
	 */
	public void setDescription(String description) throws JIException{
		put("Description", new JIVariant(description) );
	}
	
	/**
	 * Используется для управления обменом данных. С помощью данного свойства настраивается состав узлов-получателей, для которых будут регистрироваться изменения данных, узел-отправитель, из которого получена записываемая информация, а также устанавливается режим Загрузка, указывающий, что выполняется перенос информации. 
	 * @return
	 * @throws JIException
	 */
	public OCDataExchangeParameters getDataExchange() throws JIException{
		return new OCDataExchangeParameters(get("DataExchange"));
	}
	
	/**
	 * Содержит признак пометки на удаление элемента вида характеристики
	 * @return
	 * @throws JIException
	 */
	public Boolean isDeletionMark() throws JIException{
		return get("DeletionMark").getObjectAsBoolean();
	}
	
	/**
	 * Установка признака пометки на удаление элемента вида характеристики
	 * @param delMark
	 * @throws JIException
	 *//*
	public void setDeletionMark(Boolean delMark) throws JIException{
		put("DeletionMark", new JIVariant(delMark));
	}
	*/
	/**
	 * Если Истина, то над данным планом видов характеристик нельзя производить некоторые операции
	 * @return
	 * @throws JIException
	 */
	public Boolean isPredefined() throws JIException{
		return get("Predefined").getObjectAsBoolean();
	}
	
	/**
	 * Содержит ссылку на родителя элемента вида характеристики
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef getParent() throws JIException{
		return new OCChartOfCharacteristicTypesRef(get("Parent"));
	}
	
	/**
	 * Установка ссылки на родителя элемента вида характеристики
	 * @param OCChartOfCharacteristicTypesRef
	 * @throws JIException
	 */
	public void setParent(OCChartOfCharacteristicTypesRef parent) throws JIException{
		put("Parent", new JIVariant(ocObject2Dispatch(parent)));
	}
	
	/**
	 *  Содержит ссылку на элемент вида характеристик. Это значение может быть записано в базу данных для полей соответствующего типа
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
	 * Позволяет определить является ли элемент вида характеристики группой
	 * @return Истина - элемент является группой; Ложь - элемент не является группой. 
	 * @throws JIException 
	 */
	public Boolean isFolder() throws JIException{
		return get("IsFolder").getObjectAsBoolean();
	}
	
	/**
	 * Используется для доступа к объекту в собственном модуле. 
	 * @return
	 */
	public OCChartOfCharacteristicTypesObject thisObject(){
		return this;
	}
	
	public OCChartOfCharacteristicTypeMetadataObject getMetadata() throws JIException{
		return new OCChartOfCharacteristicTypeMetadataObject(super.getMetadata());
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
	 * Определяет, был ли изменен объект после считывания из базы данных. Примечание: Метод не позволяет определить, был ли изменен объект другими пользователями
	 * @return Истина - объект изменен; Ложь - в противном случае. 
	 * @throws JIException
	 */
	public Boolean isModified() throws JIException{
		return callMethodA("Modified").getObjectAsBoolean();
	}
	
	/**
	 * Позволяет для нового (созданного и еще не записанного) объекта получить ранее установленное методом УстановитьСсылкуНового значение ссылки. 
	 * @return
	 * @throws JIException
	 */
	public OCChartOfCharacteristicTypesRef getNewObjectRef() throws JIException{
		return new OCChartOfCharacteristicTypesRef(callMethodA("GetNewObjectRef"));
	}
	
	public OCChartOfCharacteristicTypesObject copy() throws JIException{
		return new OCChartOfCharacteristicTypesObject(super.copy());
	}
	
	/**
	 * Получает уровень элемента плана видов характеристик. Имеет смысл только для многоуровневых планов видов характеристик. Следует учитывать, что уровень элемента может меняться, например, при переносе в другую группу (смене родителя). Для элемента, не имеющего родителя, уровень будет равняться 0. 
	 * @return номер уровня
	 * @throws JIException
	 */
	public Integer getLevel() throws JIException{
		return callMethodA("Level").getObjectAsInt();
	}
	
	/**
	 * Устанавливает новый код. Если в коде присутствует числовая часть, то новый код автоматически устанавливается следующим за имеющимся максимальным. При этом определяется текущий максимальный код среди элементов данного вида характеристики. 
	 * Если передан префикс, то новый код устанавливается следующим образом: выполняется поиск максимального кода среди кодов, имеющих данный префикс, новый код выбирается как следующий от найденного кода. 
	 * @param preffix
	 * @throws JIException
	 */
	public void setNewCode(String preffix) throws JIException{
		callMethod("SetNewCode", new Object[]{new JIVariant(preffix)});
	}

	/**
	 * Устанавливает/снимает пометку на удаление на план видов характеристик.
	 * Объект при этом не удаляется из базы данных. Окончательное удаление
	 * помеченных счетов происходит при удалении помеченных объектов.
	 * Примечание: Применение метода влечет инициирование события ПередЗаписью,
	 * т.к. происходит сохранение измененного свойства ПометкаУдаления.
	 * 
	 * @param Признак
	 *            установки / снятие пометки на удаление. Истина - пометка будет
	 *            установлена; Ложь - пометка будет снята.
	 * @param Если
	 *            параметр установлен в Истина, то будут помечены на удаление,
	 *            кроме данного элемента, все его подчиненные элементы в данном
	 *            счете и во всех подчиненных счетах. Если параметр установлен в
	 *            Ложь, то будет помечен на удаление только данный элемент.
	 * @throws JIException
	 */
	public void setDeletionMark(Boolean mark, Boolean incSubElement)
			throws JIException {
		callMethod("SetDeletionMark", new Object[] { new JIVariant(mark),
				incSubElement != null ? new JIVariant(incSubElement) : null });
	}
	
	/**
	 * Устанавливает/снимает пометку на удаление на план видов характеристик. Объект при этом не удаляется из базы данных. Окончательное удаление помеченных счетов происходит при удалении помеченных объектов. 
	 * Примечание: Применение метода влечет инициирование события ПередЗаписью, т.к. происходит сохранение измененного свойства ПометкаУдаления.
	 * @param Признак установки / снятие пометки на удаление. Истина - пометка будет установлена; Ложь - пометка будет снята.
	 * @throws JIException
	 */
	public void setDeletionMark(Boolean mark) throws JIException{
		setDeletionMark(mark, null);
	}
	
	/**
	 * Устанавливает значение для нового (созданного и еще не записанного) объекта, которое будет назначено при записи в качестве ссылки. Значение не может равняться ссылке какого-либо из имеющихся в базе данных объекта данного типа. Уникальность ссылки проверяется при записи объекта. 
	 * @param Ссылка, которая будет назначена при записи нового объекта. 
	 * @throws JIException
	 */
	public void setNewObjectRef(OCChartOfCharacteristicTypesRef ref) throws JIException{
		callMethod("SetNewObjectRef", new Object[] { new JIVariant(
				ocObject2Dispatch(ref)) });
	}
}
