package org.xstefank.lra;

import io.narayana.lra.annotation.LRA;
import io.narayana.lra.client.NarayanaLRAClient;
import org.jboss.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ParticipantEndpoint {

    private static final Logger log = Logger.getLogger(ParticipantEndpoint.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @LRA(LRA.Type.REQUIRED)
    public void invokeParticipant(@HeaderParam(NarayanaLRAClient.LRA_HTTP_HEADER) String lraUri, Object data) {
        log.info("Participant endpoint invoked -- " + lraUri);
    }

    @GET
    @Path("/health")
    @Produces("text/plain")
    public String health() {
        return "I'm ok";
    }

}
