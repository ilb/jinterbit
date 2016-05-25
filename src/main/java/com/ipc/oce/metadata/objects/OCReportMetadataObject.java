package com.ipc.oce.metadata.objects;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCObject;
import com.ipc.oce.metadata.collection.OCMetadataAttributeCollection;
import com.ipc.oce.metadata.collection.OCMetadataSubsystemCollection;
import com.ipc.oce.metadata.collection.OCMetadataTabularSectionCollection;


public class OCReportMetadataObject extends _OCCommonMetadataObject {

	public OCReportMetadataObject(OCObject object) {
		super(object);
	}

	public OCReportMetadataObject(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	public OCReportMetadataObject(JIVariant aDispatch) throws JIException {
		super(aDispatch);
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
	 * объекта метаданных.
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCMetadataTabularSectionCollection getTabularSections()
			throws JIException {
		return new OCMetadataTabularSectionCollection(get("TabularSections"));
	}

}
