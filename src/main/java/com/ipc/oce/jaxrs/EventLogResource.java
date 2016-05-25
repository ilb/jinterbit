/**
 * 
 */
package com.ipc.oce.jaxrs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import org.jinterop.dcom.common.JIException;
import org.w3c.dom.Document;

import com.ipc.oce.OCApp;
import com.ipc.oce.events.EventManager;
import com.ipc.oce.exceptions.ConfigurationException;
import com.ipc.oce.metadata.objects._OCCommonMetadataObject;
import com.ipc.oce.objects._OCAbstractManager;
import com.ipc.oce.varset.EEventLogLevel;
import com.sun.jersey.spi.container.servlet.PerSession;

/**
 * @author Konovalov
 *
 */
@PerSession
@Path("/one-rs/events")
public class EventLogResource extends ResourceSessionAccessor {
	
	/**
	 * Формат даты и времени 1С.
	 */
	private static final String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	
	private SimpleDateFormat dateFormat = null;
	
	public EventLogResource() throws IOException, ConfigurationException, JIException {
		super();
		dateFormat = new SimpleDateFormat(DATETIME_PATTERN); 
	}
	
	private EventManager getEventManager() throws IOException, ConfigurationException, JIException {
		OCApp app = getApplication();
		EventManager manager = new EventManager(app, servletConfig.getInitParameter("tempLogDirectory"));
		return manager;
	}
	
	/**
	 * Получение всего EventLog или с фильтрацией по параметру тип события (в URL это eventType)
	 * @param eventType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@GET @Produces("text/xml")
	public Document getLog(@DefaultValue("NULL") @QueryParam("eventType") String eventType, @QueryParam("start") String startDate, @QueryParam("end") String endDate) {
		
		if (eventType.equals("NULL")) {
			eventType = EventManager.EVENT_ALL;
		}
		
		try {
			Date sDate = null; 
			if (startDate != null) {
				sDate = dateFormat.parse(startDate);
			}
			
			Date eDate = null;
			if (endDate != null) {
				eDate = dateFormat.parse(endDate);
			}
			
			EventManager manager = getEventManager();
			
			Document doc = manager.getEventLog(sDate, eDate, eventType);
			
			return doc;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
	
	@GET @Path("{metaType}") @Produces("text/xml")
	public Document getLog(@DefaultValue("NULL") @QueryParam("eventType") String eventType, @DefaultValue("INFORMATION") @QueryParam("level")String eventLevel, @PathParam("metaType") String metaType,  @QueryParam("start") String startDate, @QueryParam("end") String endDate) {
		if (eventType.equals("NULL")) {
			eventType = EventManager.EVENT_ALL;
		}
		try {
			OCApp app = getApplication();
			_OCAbstractManager abstractManager = app.findManager(metaType);
			_OCCommonMetadataObject commonMetadataObject = abstractManager.getMetadata();
			
			EventManager manager = getEventManager();
			
			Date sDate = null; 
			if (startDate != null) {
				sDate = dateFormat.parse(startDate);
			}
			
			Date eDate = null;
			if (endDate != null) {
				eDate = dateFormat.parse(endDate);
			}
			
			EEventLogLevel logLevel  = app.findVarset(
					EEventLogLevel.class.getSimpleName()
					+ "." 
					+ eventLevel.toUpperCase()
					);
			
			Document res = manager.getEventLog(sDate, eDate, new EEventLogLevel[]{logLevel}, new String[]{eventType}, new _OCCommonMetadataObject[]{ commonMetadataObject });
			
			return res;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
