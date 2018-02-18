package org.xstefank.lra;

import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;

@Path("/")
public class SagaEndpoint {

    private static final Logger log = Logger.getLogger(SagaEndpoint.class);

    @EJB
    private SagaService sagaService;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/init")
    @Consumes(MediaType.TEXT_PLAIN)
    public String initSaga(String name) throws MalformedURLException {
        return sagaService.startSaga(name, uriInfo.getBaseUri().toString());
    }

    @GET
    @Path("/health")
    @Produces("text/plain")
    public String health() {
        return "I'm ok";
    }

}
