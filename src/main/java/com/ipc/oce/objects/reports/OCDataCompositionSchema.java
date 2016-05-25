/**
 * 
 */
package com.ipc.oce.objects.reports;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.jinterop.dcom.impls.automation.IJIDispatch;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLWriter;

/**
 * Схема компоновки данных. XML-сериализация. Поддержка отображения в XDTO;
 * пространство имен: {http://v8.1c.ru/8.1/data-composition-system/schema}. Имя
 * типа XDTO: DataCompositionSchema.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionSchema extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionSchema(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchema(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchema(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Получение схемы композиции как XML.
	 * @return строковое представление XML.
	 * @throws JIException
	 */
	public String showXML() throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		OCXDTOSerializer ocxdtoSerializer = app.getXDTOSerializer();
		OCXMLWriter ocxmlWriter = app.newXMLWriter();
		ocxmlWriter.setString("UTF-8");
		ocxdtoSerializer.writeXML(ocxmlWriter, this);
		
		return ocxmlWriter.close();
	}
	
	/**
	 * 
	 * @return
	 * @throws JIException
	 */
	public OCDataCompositionSettings getDefaultSettings() throws JIException {
		return new OCDataCompositionSettings(get("DefaultSettings"));
	}
	
	/**
	 * Содержит источники, описанные в схеме.
	 * @return OCDataCompositionSchemaDataSources
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSources getDataSources() throws JIException {
		return new OCDataCompositionSchemaDataSources(get("DataSources"));
	}
	
	/**
	 * Содержит наборы данных, описанные в схеме. 
	 * @return OCDataCompositionSchemaDataSets
	 * @throws JIException
	 */
	public OCDataCompositionSchemaDataSets getDataSets() throws JIException {
		return new OCDataCompositionSchemaDataSets(get("DataSets"));
	}
	
	/**
	 * Содержит описания параметров схемы. 
	 * @return
	 * @throws JIException
	 */
	public OCDataCompositionSchemaParameters getParameters() throws JIException {
		return new OCDataCompositionSchemaParameters(get("Parameters"));
	}

}
