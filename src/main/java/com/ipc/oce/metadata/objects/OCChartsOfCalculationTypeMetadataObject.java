package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;
import com.ipc.oce.varset.OCCodeType;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;


/**
 * Используется для обращения к метаданным объекта конфигурации - план видов расчета. 
 * @author Konovalov
 *
 */
public class OCChartsOfCalculationTypeMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder {
	private OCMetadataTabularSectionCollection tabularCollection = null;
	private OCMetadataAttributeCollection attributeCollection = null;
	
	public OCChartsOfCalculationTypeMetadataObject(OCObject object) {
		super(object);
	}

	public OCChartsOfCalculationTypeMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCChartsOfCalculationTypeMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Максимальная длина кода объекта базы данных (например, справочника, счета). 
	 * @return
	 * @throws JIException
	 */
	public Integer getCodeLength() throws JIException{
		return get("CodeLength").getObjectAsInt();
	}
	
	/**
	 * Максимальная длина наименования объекта базы данных (например, справочника, задачи). 
	 * @return
	 * @throws JIException
	 */
	public Integer getDescriptionLength() throws JIException{
		return get("DescriptionLength").getObjectAsInt();
	}
	
	/**
	 *  Если это свойство установлено в значение Истина, то устанавливается конкурирующий характер взаимного влияния движений данного регистра по периоду действия.
	 * @return
	 * @throws JIException
	 */
	public Boolean isActionPeriodUse() throws JIException{
		return get("ActionPeriodUse").getObjectAsBoolean();
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException{
		if (attributeCollection == null) {
			attributeCollection = new OCMetadataAttributeCollection(get("Attributes"));
		}
		return attributeCollection;
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих табличные части данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataTabularSectionCollection getTabularSections() throws JIException{
		if (tabularCollection == null) {
			tabularCollection = new OCMetadataTabularSectionCollection(get("TabularSections"));
		}
		return tabularCollection;
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым относится данный объект метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCSubsystemMetadataObject getSubsystems() throws JIException{
		return new OCSubsystemMetadataObject(get("Subsystems"));
	}
	
	/**
	 * Определяет режим управления блокировкой данных объекта конфигурации (например, Автоматический, Управляемый, АвтоматическийИУправляемый). 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode() throws JIException{
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}
	
	/**
	 * Тип кода объекта базы данных (например, Число, Строка). 
	 * @return
	 * @throws JIException
	 */
	public OCCodeType getCodeType() throws JIException{
		return new OCCodeType(get("CodeType"));
	}
}
