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
 * Объект, содержащий информацию о расшифровке. XML-сериализация. Поддержка
 * отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data-composition-system/details}. Имя типа XDTO:
 * DetailsInformation.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionDetailsData extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionDetailsData(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionDetailsData(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionDetailsData(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Настройки, для которых были построены данные расшифровки.
	 * XML-сериализация.
	 * 
	 * @return OCDataCompositionSettings
	 * @throws JIException
	 */
	public OCDataCompositionSettings getSettings() throws JIException {
		return new OCDataCompositionSettings(get("Settings"));
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
