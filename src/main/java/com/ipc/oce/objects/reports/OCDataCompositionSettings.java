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
 * Настройка компоновки данных в целом. XML-сериализация. Поддержка отображения
 * в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data-composition-system/settings}. Имя типа XDTO:
 * Settings.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionSettings extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionSettings(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSettings(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSettings(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Содержит значения параметров получения данных.
	 * @return OCDataCompositionDataParameterValues
	 * @throws JIException
	 */
	public OCDataCompositionDataParameterValues getDataParameters() throws JIException {
		return new OCDataCompositionDataParameterValues(get("DataParameters"));
	}
	
	public String showXML() throws JIException {
		OCApp app = OCApp.getInstance(getAssociatedSessionID());
		OCXDTOSerializer ocxdtoSerializer = app.getXDTOSerializer();
		OCXMLWriter ocxmlWriter = app.newXMLWriter();
		ocxmlWriter.setString("UTF-8");
		ocxdtoSerializer.writeXML(ocxmlWriter, this);
		
		return ocxmlWriter.close();
	}

}
