/**
 * 
 */
package com.ipc.oce.objects;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.metadata.objects._OCCommonMetadataObject;

/**
 * @author Konovalov
 * Интерфейс объектов предоставляющих метаданные
 */
public interface MetadataHolder {
	
	_OCCommonMetadataObject getMetadata() throws JIException;
	
}
