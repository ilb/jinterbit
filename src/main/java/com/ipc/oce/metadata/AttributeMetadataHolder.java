package com.ipc.oce.metadata;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;

/**
 * Интерфейс для доступа к метаданным реквизитов объектов.
 * @author Konovalov
 *
 */
public interface AttributeMetadataHolder {

	/**
	 * Получение описания атрибутов. 
	 * @return OCMetadataAttributeCollection
	 * @throws JIException - ошибка DCOM
	 */
	OCMetadataAttributeCollection getAttributes() throws JIException;
	
}
