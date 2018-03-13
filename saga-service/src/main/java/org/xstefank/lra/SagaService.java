package org.xstefank.lra;

import io.narayana.lra.client.NarayanaLRAClient;
import org.xstefank.lra.definition.rest.RESTAction;
import org.xstefank.lra.definition.rest.RESTLra;
import org.xstefank.lra.definition.rest.RESTLraBuilder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;

@Stateless
public class SagaService {

    @Inject
    @CurrentLRAClient
    private NarayanaLRAClient lraClient;

    public String startSaga(String name, String uri) throws MalformedURLException {
        RESTLra lra = RESTLraBuilder.lra()
                .name("testing saga")
                .data(42)
                .withAction(RESTAction.post(new URL("http://participant1-service:8080/api/request")))
                .callback(uri)
                .build();

        URL lraId = lraClient.startLRA(lra);

        return "Invocation saga-sevice: " + lraId.toString();
    }

}
