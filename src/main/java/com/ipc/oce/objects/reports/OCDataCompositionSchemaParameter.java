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
 * @author Konovalov
 *
 */
public class OCDataCompositionSchemaParameter extends OCObject {

	/**
	 * @param object
	 */
	public OCDataCompositionSchemaParameter(OCObject object) {
		super(object);

	}

	/**
	 * @param aDispatch
	 */
	public OCDataCompositionSchemaParameter(IJIDispatch aDispatch) {
		super(aDispatch);

	}

	/**
	 * @param aDispatch
	 * @throws JIException
	 */
	public OCDataCompositionSchemaParameter(JIVariant aDispatch)
			throws JIException {
		super(aDispatch);

	}
	
	/**
	 * Содержит имя параметра. 
	 * @return
	 * @throws JIException
	 */
	public String getName() throws JIException {
		return get("Name").getObjectAsString2();
	}

	/**
	 * Содержит имя параметра. 
	 * @param name
	 * @throws JIException
	 */
	public void setName(String name) throws JIException {
		put("Name", new JIVariant(name));
	}

	@Override
	public String toString() {
		try {
			return super.toString() + "." + getName();
		} catch (JIException e) {
			return super.toString();
		}
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
