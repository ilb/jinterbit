/**
 * 
 */
package com.ipc.oce.jaxrs;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ipc.oce.OCApp;
import com.ipc.oce.query.OCQueryResultSelection;
import com.ipc.oce.query.QueryTemplate;
import com.ipc.oce.xml.oc.MarshalHelper;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.spi.container.servlet.PerSession;

/**
 * @author Konovalov
 *
 */
@PerSession
@Path("/one-rs/query")
public class QueryResource extends ResourceSessionAccessor {
	
	private static final transient Log LOG = LogFactory.getLog(QueryResource.class);
	
	@GET
	@Produces("text/xml")
	public Response performQuery(@QueryParam("q") String query, @DefaultValue("20") @QueryParam("mode") String mode) {
		try {
			if (query == null) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			OCApp app = getApplication();
			
			QueryTemplate template = new QueryTemplate(app);
			
			if (LOG.isInfoEnabled()) {
				LOG.info("Query: " + query);
			}
			
			OCQueryResultSelection selection = template.queryForSelection(query);
			MarshalHelper marshalHelper = new MarshalHelper(app);
			String xml = marshalHelper.selection2xml(selection, Integer.valueOf(mode));
			return Response.ok(xml).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
