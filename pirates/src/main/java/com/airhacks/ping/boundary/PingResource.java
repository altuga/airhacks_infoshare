package com.airhacks.ping.boundary;

import com.airhacks.ping.entity.Pirate;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    PortRoyal portRoyal;

    @GET
    public Pirate ping() {
        return this.portRoyal.getNext();
    }

    @POST
    @Metered
    public void newPirate(Pirate pirate) {
        portRoyal.newPirate(pirate);
    }


}
