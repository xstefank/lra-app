package org.xstefank.lra;

import io.narayana.lra.client.NarayanaLRAClient;
import org.xstefank.lra.definition.rest.RESTAction;
import org.xstefank.lra.definition.rest.RESTLra;
import org.xstefank.lra.definition.rest.RESTLraBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;

@ApplicationScoped
public class SagaService {

    @Inject
    private NarayanaLRAClient lraClient;

    public String startSaga(String name) throws MalformedURLException {
        RESTLra lra = RESTLraBuilder.lra()
                .name("testing saga")
                .data(42)
                .withAction(RESTAction.post(new URL("http://participant1-service:8080")))
                .subscrbeCallerAsParticipant()
                .build();

        URL lraId = lraClient.startLRAAsync(lra);

        return lraId.toString();
    }

}