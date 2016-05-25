/**
 * 
 */
package com.ipc.oce.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLWriter;

/**
 * @author Konovalov
 *
 */
public class DataObjectLookupProcessor implements Processor {

	public static final String UUID_FILED = "LOOKUP_UUID";
	public static final String OBJECT_TYPE = "OBJECT_TYPE";
	
	public void process(Exchange exchange) throws Exception {
		
		// uuid
		String uuid = (String) exchange.getProperty(UUID_FILED);
		
		// full object name
		String objectTypePair = (String) exchange.getProperty(OBJECT_TYPE);
		
		if (uuid == null || uuid.trim().length() == 0 || objectTypePair == null || objectTypePair.trim().length() == 0) {
			throw new IllegalStateException("Wrong uuid and object type pair. UUID = " + uuid + ", type = " + objectTypePair );
		}
		
		OCXEEndpoint endpoint = (OCXEEndpoint) exchange.getFromEndpoint();
		
		OCApp app = endpoint.getApp_instance();
		
		OCObject dataObject = app.findDataObject(objectTypePair, uuid);
		
		Message out = exchange.getOut();
		
		OCXDTOSerializer serializer = app.getXDTOSerializer();
		OCXMLWriter writer = app.newXMLWriter();
		writer.setString("UTF-8");
		serializer.writeXML(writer, dataObject);
		out.setBody(writer.close(), String.class);
		
	}

}
