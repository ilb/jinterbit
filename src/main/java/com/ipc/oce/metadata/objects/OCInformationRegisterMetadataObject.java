package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataDimensionCollection;
import com.ipc.oce.metadata.collection.OCMetadataResourceCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;
import com.ipc.oce.varset.OCInformationRegisterPeriodicity;
import com.ipc.oce.varset.OCRegisterWriteMode;


/**
 * Используется для обращения к метаданным объекта конфигурации - регистр сведений. 
 * @author Konovalov
 *
 */
public class OCInformationRegisterMetadataObject extends _OCCommonMetadataObject {

	public OCInformationRegisterMetadataObject(OCObject object) {
		super(object);
	}

	public OCInformationRegisterMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCInformationRegisterMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих измерения данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDimensionCollection getDimensions() throws JIException {
		return new OCMetadataDimensionCollection(get("Dimensions"));
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то поле Периодвходит в основной отбор. В противном случае поле период не включается в основной отбор.
	 * @return
	 * @throws JIException
	 */
	public Boolean isMainFilterOnPeriod() throws JIException {
		return get("MainFilterOnPeriod").getObjectAsBoolean();
	}
	
	/**
	 * Указывает, с какой периодичностью регистр сохраняет значения ресурсов (например, Месяц, День, Секунда). 
	 * @return
	 * @throws JIException
	 */
	public OCInformationRegisterPeriodicity getInformationRegisterPeriodicity() throws JIException {
		return new OCInformationRegisterPeriodicity(
				get("InformationRegisterPeriodicity"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым относится данный объект метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException {
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}
	
	/**
	 * Определяет режим добавления записей в регистр сведений (например, Независимый, ПодчинениеРегистратору). 
	 * @return
	 * @throws JIException
	 */
	public OCRegisterWriteMode getWriteMode() throws JIException {
		return new OCRegisterWriteMode(get("WriteMode"));
	}
	
	/**
	 *  Определяет режим управления блокировкой данных объекта конфигурации (например, Автоматический, Управляемый, АвтоматическийИУправляемый). 
	 * @return
	 * @throws JIException
	 */
	public OCDefaultDataLockControlMode getDataLockControlMode() throws JIException {
		return new OCDefaultDataLockControlMode(get("DataLockControlMode"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException {
		return new OCMetadataAttributeCollection(get("Attributes"));
	}
	
	/**
	 *  Коллекция объектов метаданных, описывающих ресурсы данного объекта метаданных. 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataResourceCollection getResources() throws JIException {
		return new OCMetadataResourceCollection(get("Resources"));
	}

}
