package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.AttributeMetadataHolder;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataDimensionCollection;
import com.ipc.oce.metadata.collection.OCMetadataResourceCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.varset.OCAccumulationRegisterType;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;


/**
 * Используется для обращения к метаданным объекта конфигурации - регистр накопления.
 * @author Konovalov
 *
 */
public class OCAccumulationRegisterMetadataObject extends _OCCommonMetadataObject implements AttributeMetadataHolder {

	public OCAccumulationRegisterMetadataObject(OCObject object) {
		super(object);
	}

	public OCAccumulationRegisterMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCAccumulationRegisterMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Вид регистра накопления (например, Остатки, Обороты).
	 * @return
	 * @throws JIException
	 */
	public OCAccumulationRegisterType getRegisterType() throws JIException{
		return new OCAccumulationRegisterType(get("RegisterType"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих измерения данного объекта метаданных
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDimensionCollection getDimensions() throws JIException{
		return new OCMetadataDimensionCollection(get("Dimensions"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым относится данный объект метаданных
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException{
		return new OCMetadataSubsystemCollection(get("Subsystems"));
	}
	
	/**
	 * Если это свойство установлено в значение Истина, то для хранения итогов регистров будет задействован механизм разделения итогов.
	 * @return
	 * @throws JIException
	 */
	public Boolean isEnableTotalsSplitting() throws JIException{
		return get("EnableTotalsSplitting").getObjectAsBoolean();
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
	 * Коллекция объектов метаданных, описывающих реквизиты данного объекта метаданных
	 * @return
	 * @throws JIException
	 */
	public OCMetadataAttributeCollection getAttributes() throws JIException{
		return new OCMetadataAttributeCollection(get("Attributes"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих ресурсы данного объекта метаданных
	 * @return
	 * @throws JIException
	 */
	public OCMetadataResourceCollection getResources() throws JIException{
		return new OCMetadataResourceCollection(get("Resources"));
	}
}
