package org.xstefank.lra;

import io.narayana.lra.client.NarayanaLRAClient;
import io.narayana.lra.rest.RESTAction;
import io.narayana.lra.rest.RESTLra;
import io.narayana.lra.rest.RESTLraBuilder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Stateless
public class SagaService {

    private static final String PARTICIPANT2_URL = "http://participant2-service:8080/api";
    private static final String PARTICIPANT1_URL = "http://participant1-service:8080/api";

    @Inject
    @CurrentLRAClient
    private NarayanaLRAClient lraClient;

    public String startSaga(String name) throws MalformedURLException, URISyntaxException {

        URL participant2Url = new URL(PARTICIPANT2_URL);

        RESTLra lra = RESTLraBuilder.lra()
                .name("testing saga")
                .data(42)
                .withAction(RESTAction.post(new URL(PARTICIPANT1_URL)).build())
                .withAction(RESTAction
                        .post(UriBuilder.fromUri(participant2Url.toURI()).path("/request").build().toURL())
                        .callbackUrl(participant2Url)
                        .build())
                .callback("http://saga-service:8080/api")
                .build();

        lraClient.startLRA(lra);

        return "Saga invocation is being processed";
    }

}
