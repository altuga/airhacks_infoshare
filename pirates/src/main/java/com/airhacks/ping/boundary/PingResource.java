package com.airhacks.ping.boundary;

import com.airhacks.ping.entity.Pirate;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Metered;

/**
 *
 * @author airhacks.com
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("ping")
public class PingResource {

    @Inject
    @ConfigProperty(name = "message")
    String message;    

    @Inject
    @ConfigProperty(name = "shipcount", defaultValue = "13")
    int destroyedShips;

    @Inject
    PortRoyal portRoyal;

    @GET
    public Pirate ping() {
        return new Pirate(this.message + " destruction", this.destroyedShips);
    }

    @POST
    @Metered
    public void newPirate(Pirate pirate) {
        portRoyal.newPirate(pirate);
    }


}
