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
 * Макет компоновки данных. Поддержка отображения в XDTO; пространство имен:
 * {http://v8.1c.ru/8.1/data-composition-system/composition-template}. Имя типа
 * XDTO: DataCompositionTemplate.
 * 
 * @author Konovalov
 * 
 */
public class OCDataCompositionTemplate extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionTemplate(OCObject object) {
		super(object);
	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionTemplate(IJIDispatch aDispatch) {
		super(aDispatch);
	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionTemplate(JIVariant aDispatch) throws JIException {
		super(aDispatch);
	}
	
	/**
	 * Описание параметров. 
	 * @return
	 * @throws JIException
	 */
	public OCDataCompositionTemplateParameterValues getParameterValues() throws JIException {
		return new OCDataCompositionTemplateParameterValues(get("ParameterValues"));
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
