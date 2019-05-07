package com.airhacks.ping.boundary;

import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    InterceptPirates pirates;

    @GET
    public void ping(@Suspended AsyncResponse response) {
        response.setTimeout(10, TimeUnit.SECONDS);
        response.setTimeoutHandler(this::handleTimeout);
        pirates.getPirates().thenAccept(response::resume);

    }

    public void handleTimeout(AsyncResponse asyncResponse) {
        asyncResponse.resume(Response.status(503).header("reason", "I like a break").build());
    }

}
