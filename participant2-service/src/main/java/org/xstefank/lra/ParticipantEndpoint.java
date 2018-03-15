package org.xstefank.lra;

import io.narayana.lra.annotation.Compensate;
import io.narayana.lra.annotation.Complete;
import io.narayana.lra.annotation.LRA;
import io.narayana.lra.client.NarayanaLRAClient;
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

    private static final Logger log = Logger.getLogger(ParticipantEndpoint.class);


    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @LRA(value = LRA.Type.REQUIRED)
    public Response requestShipment(@HeaderParam(NarayanaLRAClient.LRA_HTTP_HEADER) String lraUri, Object data) throws InterruptedException {
        String lraId = NarayanaLRAClient.getLRAId(lraUri);
        log.info("processing request for LRA " + lraId);

        log.info("waiting 10s");
        Thread.sleep(10000);
        log.info("done sleep");

        return Response
                .ok()
                .entity(String.format("Participant2 processed %s", data))
                .build();
    }

    @PUT
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    @Complete
    public Response completeWork(@HeaderParam(NarayanaLRAClient.LRA_HTTP_HEADER) String lraUri) {
        String lraId = NarayanaLRAClient.getLRAId(lraUri);
        log.info("completing participant2 for LRA " + lraId);

        return Response.ok().build();
    }

    @PUT
    @Path("/compensate")
    @Produces(MediaType.APPLICATION_JSON)
    @Compensate
    public Response compensateWork(@HeaderParam(NarayanaLRAClient.LRA_HTTP_HEADER) String lraUri) {
        String lraId = NarayanaLRAClient.getLRAId(lraUri);
        log.info("compensating participant2 for LRA " + lraId);

        return Response.ok().build();
    }

    @GET
    @Path("/health")
    @Produces("text/plain")
    public String health() {
        return "I'm ok";
    }

}
