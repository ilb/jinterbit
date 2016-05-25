package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;

/**
 * Используется для обращения к метаданным объекта конфигурации - план обмена.
 * 
 * @author Konovalov
 * 
 */
public class OCExchangePlanMetadataObject extends _OCCommonMetadataObject
		implements AttributeMetadataHolder {

	public OCExchangePlanMetadataObject(OCObject object) {
		super(object);
	}

	public OCExchangePlanMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCExchangePlanMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}

	/**
	 * Максимальная длина кода объекта базы данных (например, справочника,
	 * счета).
	 * 
	 * @return
	 * @throws JIException
	 */
	public Integer getCodeLength() throws JIException {
		return get("CodeLength").getObjectAsInt();
	}

	/**
	 * Максимальная длина наименования объекта базы данных (например,
	 * справочника, задачи).
	 * 
	 * @return
	 * @throws JIException
	 */
	public Integer getDescriptionLength() throws JIException {
		return get("DescriptionLength").getObjectAsInt();
	}

	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым
	 * относится данный объект метаданных.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException {
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}

	/**
	 * Если это свойство установлено в значение Истина, то данный план обмена
	 * может быть задействован для организации распределенной информационной
	 * базы.
	 * 
	 * @return
	 * @throws JIException
	 */
	public Boolean isDistributedInfoBase() throws JIException {
		return get("DistributedInfoBase").getObjectAsBoolean();
	}

	/**
	 * Определяет режим управления блокировкой данных объекта конфигурации
	 * (например, Автоматический, Управляемый, АвтоматическийИУправляемый).
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode()
			throws JIException {
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}

	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта
	 * метаданных.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException {
		return new OCMetadataAttributeCollection(get("Attributes"));
	}

	/**
	 * Коллекция объектов метаданных, описывающих табличные части данного
	 * объекта метаданных
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataTabularSectionCollection getTabularSections()
			throws JIException {
		return new OCMetadataTabularSectionCollection(get("TabularSections"));
	}

}
