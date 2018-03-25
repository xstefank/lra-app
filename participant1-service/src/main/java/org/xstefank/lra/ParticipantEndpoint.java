package org.xstefank.lra;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/")
public class ParticipantEndpoint {

    private static final String LRA_HTTP_HEADER = "Long-Running-Action";

    private static final Logger log = Logger.getLogger(ParticipantEndpoint.class);

    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response request(@HeaderParam(LRA_HTTP_HEADER) String lraUri, Object data) throws InterruptedException {
        String lraId = lraUri;
        log.info("processing request for LRA " + lraId);

        log.info("waiting 5s");
        Thread.sleep(5000);
        log.info("done sleep");

        return Response
                .ok()
                .entity(String.format("Participant1 processed %s", data))
                .build();
    }

    @PUT
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeWork(@HeaderParam(LRA_HTTP_HEADER) String lraUri) {
        String lraId = lraUri;
        log.info("completing participant1 for LRA " + lraId);

        return Response.ok().build();
    }

    @PUT
    @Path("/compensate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response compensateWork(@HeaderParam(LRA_HTTP_HEADER) String lraUri) {
        String lraId = lraUri;
        log.info("compensating participant1 for LRA " + lraId);

        return Response.ok().build();
    }

    @GET
    @Path("/health")
    @Produces("text/plain")
    public String health() {
        return "I'm ok";
    }

}
