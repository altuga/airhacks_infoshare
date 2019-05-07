
package com.airhacks.ping.boundary;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author airhacks.com
 */
public class InterceptPirates {

    private Client client;
    private WebTarget pirateTarget;

    @PostConstruct
    public void initClient() {
        this.client = ClientBuilder.newBuilder().
                connectTimeout(1, TimeUnit.DAYS).
                readTimeout(1, TimeUnit.DAYS).
                build();
        this.pirateTarget = this.client.target("http://localhost:9080/pirates/resources/ping");

    }

    public CompletionStage<JsonObject> getPirates() {
        return this.pirateTarget.request().rx().get(JsonObject.class);
    }


}
