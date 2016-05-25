package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataDimensionCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.varset.OCDefaultDataLockControlMode;
import com.ipc.oce.varset.OCMoveBoundaryOnPosting;


/**
 * Используется для обращения к метаданным объекта конфигурации - последовательность.
 * @author Konovalov
 *
 */
public class OCSequenceMetadataObject extends _OCCommonMetadataObject {

	public OCSequenceMetadataObject(OCObject object) {
		super(object);
	}

	public OCSequenceMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCSequenceMetadataObject(JIVariant aDispatch) throws JIException {
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
	 * Если это свойство установлено в значение Перемещать, то документ,
	 * зарегистрированный в этой последовательности, при своем проведении будет
	 * пытаться переместить границу этой последовательности. Если это свойство
	 * установлено в значение НеПеремещать, то документ не будет перемещать
	 * границу этой последовательности при своем проведении.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMoveBoundaryOnPosting getMoveBoundaryOnPosting()
			throws JIException {
		return new OCMoveBoundaryOnPosting(get("MoveBoundaryOnPosting"));
	}
	
	/**
	 * Коллекция объектов метаданных, описывающих подсистемы, к которым
	 * относится данный объект метаданных
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataSubsystemCollection getSubsystems() throws JIException {
		return new OCMetadataSubsystemCollection(get("Subsystems"));
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
