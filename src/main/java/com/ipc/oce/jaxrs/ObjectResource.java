/**
 * 
 */
package com.ipc.oce.jaxrs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.OCObject;
import com.ipc.oce.exceptions.ConfigurationException;
import com.ipc.oce.objects.OCDocumentManager;
import com.ipc.oce.objects.OCDocumentRef;
import com.ipc.oce.objects._OCCommonObject;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.ipc.oce.xml.oc.OCXMLReader;
import com.ipc.oce.xml.oc.OCXMLWriter;

/**
 * @author Konovalov 
 * Create = PUT 
 * Retrieve = GET 
 * Update = POST 
 * Delete = DELETE
 * 
 */
@Path("/one-rsx/objects")
public class ObjectResource extends ResourceSessionAccessor {

	/**
	 * 
	 */
	public ObjectResource() {
		super();
	}
	
	/**
	 * Получение сериализованого объекта по его типу и UUID
	 * @param metaType тип объекта 
	 * @param uuid объекта
	 * @return w3c Document 
	 */
	@GET 
	@Produces("text/xml")
	public String getDataObject(@QueryParam("metaType") String metaType, @QueryParam("uuid") String uuid) {
		if (metaType == null || metaType.trim().length() == 0 
				|| uuid == null || uuid.trim().length() == 0) {
			throw new WebApplicationException(Status.NOT_ACCEPTABLE);
		}
		try {
			OCApp app = getApplication();
			OCObject obj = app.findDataObject(metaType, uuid);
			
			OCXDTOSerializer serializer = app.getXDTOSerializer();
			OCXMLWriter writer = app.newXMLWriter();
			writer.setString("UTF-8");
			
			serializer.writeXML(writer, obj);
			return writer.close();
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET 
	@Produces("text/xml")
	@Path("doc")
	public Response findDocumentByNumberAndDate(@QueryParam("name")String docName, @QueryParam("num") String number, @QueryParam("date") String sDate, @QueryParam("dateformat") @DefaultValue("dd.MM.yyyy HH:mm:ss") String pattern) throws ParseException, JIException, IOException, ConfigurationException {
		Date date = (new SimpleDateFormat(pattern)).parse(sDate);
		OCApp app = getApplication();
		OCDocumentManager manager = app.getDocumentManager(docName);
		OCDocumentRef ref = manager.findByNumber(number, date);
		if (ref.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			OCXDTOSerializer serializer = app.getXDTOSerializer();
			
			return Response.ok(serializer.writeXML(ref.getObject())
					).build();
		}
	}
	
	@POST
	public Response updateDataObject(String body) {
		try {
			OCApp app = getApplication();
			OCXDTOSerializer serializer = app.getXDTOSerializer();
			OCXMLReader reader = app.newXMLReader();
			reader.setString(body);
			OCObject object = serializer.readXML(reader);
			_OCCommonObject commonObject = new _OCCommonObject(object);
			/*if (commonObject.isNew()) {
				
				 * The request could not be understood by the server due to
				 * malformed syntax. The client SHOULD NOT repeat the request
				 * without modifications.
				 
				return Response.status(Status.BAD_REQUEST).build();
			}*/
			commonObject.write();
			System.out.println("Object writed " + commonObject);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@PUT
	public Response createDataObject(String body) {
		try {
			OCApp app = getApplication();
			OCXDTOSerializer serializer = app.getXDTOSerializer();
			OCXMLReader reader = app.newXMLReader();
			reader.setString(body);
			OCObject object = serializer.readXML(reader);
			_OCCommonObject commonObject = new _OCCommonObject(object);
			commonObject.write();
			
			OCXMLWriter writer = app.newXMLWriter();
			writer.setString("UTF-8");
			serializer.writeXML(writer, commonObject);
			String xmlResp = writer.close();
			
			return Response.ok(xmlResp).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@DELETE
	public Response deleteDataObject(String body) {
		try {
			OCApp app = getApplication();
			OCXDTOSerializer serializer = app.getXDTOSerializer();
			OCObject object = serializer.readXML(body);
			_OCCommonObject commonObject = new _OCCommonObject(object);
			if (commonObject.isNew()) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			commonObject.read(); // exactly same object
			commonObject.delete();
			
			return Response.ok().build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

}
