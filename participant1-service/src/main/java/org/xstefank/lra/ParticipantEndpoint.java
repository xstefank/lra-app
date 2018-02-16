package org.xstefank.lra;

import org.jboss.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/")
public class ParticipantEndpoint {

    private static final Logger log = Logger.getLogger(ParticipantEndpoint.class);

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void invokeParticipant() {
        log.info("Participant endpoint invoked");
    }

}
