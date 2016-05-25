/**
 * 
 */
package com.ipc.oce.jaxrs;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jinterop.dcom.common.JIException;

import com.ipc.oce.OCApp;
import com.ipc.oce.exceptions.ConfigurationException;
import com.ipc.oce.objects.OCCatalogManager;
import com.ipc.oce.objects.OCCatalogObject;
import com.ipc.oce.objects.OCCatalogSelection;
import com.ipc.oce.xml.oc.OCXDTOSerializer;
import com.sun.jersey.spi.container.servlet.PerSession;

/**
 * @author Konovalov
 *
 */
@PerSession
@Path("/one-rs/catalogs")
public class CatalogResourceHandler extends ResourceSessionAccessor {

	/**
	 * 
	 */
	public CatalogResourceHandler() {
		super();
	}
	
	@GET
	@Produces("text/xml")
	@Path("{catalogName}")
	public String getCatalog(@PathParam("catalogName") String catalogName) throws IOException, ConfigurationException, JIException {
		OCApp app = getApplication();
		System.out.println(catalogName);
		OCCatalogManager manager = null;
		try {
			manager = app.getCatalogManager(catalogName);
		} catch (JIException e) {
			// TODO тут нужно как-то обрабатывать
			throw e;
		}
		OCCatalogSelection selection = manager.select();
		StringBuffer sb = new StringBuffer(4096);
		OCXDTOSerializer serializer = app.getXDTOSerializer();
		while(selection.next()) {
			OCCatalogObject object = selection.getObject();
			sb.append(serializer.writeXML(object));
		}
		surroundContainersTag(sb);
		return sb.toString();
	}
	
	protected void surroundContainersTag(StringBuffer sb) {
		sb.insert(0, "<oct:CatalogContainer xmlns:oct=\"" + SYS_NS + "\">");
		sb.append("</oct:CatalogContainer>");
	}

}
