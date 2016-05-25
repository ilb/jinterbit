package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataDimensionCollection;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;

/**
 * Используется для обращения к метаданным объекта конфигурации - перерасчет.
 * 
 * @author Konovalov
 * 
 */
public class OCRecalculationMetadataObject extends _OCCommonMetadataObject {

	public OCRecalculationMetadataObject(OCObject object) {
		super(object);
	}

	public OCRecalculationMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCRecalculationMetadataObject(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);
	}

	/**
	 * Коллекция объектов метаданных, описывающих измерения данного объекта
	 * метаданных.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataDimensionCollection getDimensions() throws JIException {
		return new OCMetadataDimensionCollection(get("Dimensions"));
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

}
