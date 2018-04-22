package org.xstefank.lra;

import io.narayana.lra.client.NarayanaLRAClient;

import javax.enterprise.inject.Produces;
import java.net.URISyntaxException;

public class BeanConfiguration {

    @Produces
    @CurrentLRAClient
    public NarayanaLRAClient lraClient() {
        try {
            NarayanaLRAClient lraClient = new NarayanaLRAClient("lra-coordinator", 8080);
            return lraClient;
        } catch (URISyntaxException urise) {
            throw new IllegalStateException("Can't initalize a new LRA client", urise);
        }
    }
}
