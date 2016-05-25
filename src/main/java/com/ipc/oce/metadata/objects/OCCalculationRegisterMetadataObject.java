package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataDimensionCollection;
import com.ipc.oce.metadata.collection.OCMetadataRecalculationCollection;
import com.ipc.oce.metadata.collection.OCMetadataResourceCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.objects.OCCalculationRegisterPeriodicity;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;


/**
 * Используется для обращения к метаданным объекта конфигурации - регистр расчета. 
 * @author Konovalov
 *
 */
public class OCCalculationRegisterMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder{

	public OCCalculationRegisterMetadataObject(OCObject object) {
		super(object);
	}

	public OCCalculationRegisterMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCCalculationRegisterMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то для записей регистра расчета возможно использование механизма зависимости по базовому периоду и получение значения базы. 
	 * Если это свойство установлено в значение Ложь, то механизм зависимости по базовому периоду не используется. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isBasePeriod() throws JIException{
		return get("BasePeriod").getObjectAsBoolean();
	}
	
	/**
	 * Описывает непериодический регистр сведений, в структуре которого есть хотя бы одно измерение типа Дата и хотя бы один ресурс типа Число. 
	 * @return
	 * @throws JIException
	 */
	public OCInformationRegisterMetadataObject getSchedule() throws JIException{
		return new OCInformationRegisterMetadataObject(get("Schedule"));
	}
	
	/**
	 * Тип: ОбъектМетаданных: Измерение. 
	 * @return
	 * @throws JIException
	 */
	public OCDimensionMetadataObject getScheduleDate() throws JIException{
		return new OCDimensionMetadataObject(get("ScheduleDate"));
	}
	
	/**
	 * Тип: ОбъектМетаданных: Ресурс. 
	 * @return
	 * @throws JIException
	 */
	public OCResourceMetadataObject getScheduleValue() throws JIException{
		return new OCResourceMetadataObject(get("ScheduleValue"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих измерения данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDimensionCollection getDimensions() throws JIException{
		return new OCMetadataDimensionCollection(get("Dimensions"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих перерасчеты, подчиненные данному регистру расчета. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataRecalculationCollection getRecalculations() throws JIException{
		return new OCMetadataRecalculationCollection(get("Recalculations"));
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то в данном регистре можно учитывать записи, протяженные во времени. 
	 * @return
	 * @throws JIException
	 */
	public Boolean isActionPeriod() throws JIException{
		return get("ActionPeriod").getObjectAsBoolean();
	}
	
	/**
	 * Определяет вид периода регистрации записей (например, Год, Квартал, Месяц, День). 
	 * @return
	 * @throws JIException
	 */
	public OCCalculationRegisterPeriodicity getPeriodicity() throws JIException{
		return new OCCalculationRegisterPeriodicity(get("Periodicity"));
	}
	
	/**
	 * Объект метаданных, описывающий план видов расчета, соответствующий данному регистру расчета. 
	 * @return
	 * @throws JIException
	 */
	public OCChartsOfCalculationTypeMetadataObject getChartOfCalculationTypes() throws JIException{
		return new OCChartsOfCalculationTypeMetadataObject(
				get("ChartOfCalculationTypes"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым относится данный объект метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException{
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}
	
	/**
	 *  Определяет режим управления блокировкой данных объекта конфигурации (например, Автоматический, Управляемый, АвтоматическийИУправляемый). 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode() throws JIException{
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException{
		return new OCMetadataAttributeCollection(get("Attributes"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих ресурсы данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataResourceCollection getResources() throws JIException{
		return new OCMetadataResourceCollection(get("Resources"));
	}
}
