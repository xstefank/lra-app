package org.xstefank.lra;

import io.narayana.lra.rest.ParticipantCallback;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class SagaEndpoint implements ParticipantCallback {

    private static final Logger log = Logger.getLogger(SagaEndpoint.class);

    @EJB
    private SagaService sagaService;

    @POST
    @Path("/init")
    @Consumes(MediaType.TEXT_PLAIN)
    public String initSaga(String name) throws Exception {
        log.info("starting saga " + name);

        return sagaService.startSaga(name);
    }

    @GET
    @Path("/health")
    @Produces("text/plain")
    public String health() {
        return "I'm ok";
    }

    @Override
    public Response complete(String lraUri) {
        log.infof("Lra %s executed successfully", lraUri);

        return Response.ok().build();
    }

    @Override
    public Response compensate(String lraUri) {
        log.infof("Lra %s execution failed", lraUri);

        return Response.ok().build();
    }

}
