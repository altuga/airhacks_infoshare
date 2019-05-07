
package com.airhacks.ping.boundary;

import com.airhacks.ping.entity.Pirate;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

@ApplicationScoped
public class PortRoyal {

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    MetricRegistry registry;

    public void newPirate(Pirate pirate) {
        if (pirate.destroyedShips < 3) {
            throw new InvalidPirateException("Invalid pirate -> a joke " + pirate.destroyedShips);
        }
        registry.counter("created.pirates").inc();
    }


}
