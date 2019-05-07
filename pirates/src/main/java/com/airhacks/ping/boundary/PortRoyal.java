
package com.airhacks.ping.boundary;

import com.airhacks.ping.control.UnstablePirateFactory;
import com.airhacks.ping.entity.Pirate;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

@ApplicationScoped
public class PortRoyal {

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    MetricRegistry registry;

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    UnstablePirateFactory pirateFactory;

    @Inject
    @ConfigProperty(name = "shipcount", defaultValue = "13")
    int destroyedShips;

    public void newPirate(Pirate pirate) {
        if (pirate.destroyedShips < 3) {
            throw new InvalidPirateException("Invalid pirate -> a joke " + pirate.destroyedShips);
        }
        registry.counter("created.pirates").inc();
    }

    @Fallback(fallbackMethod = "defaultPirate")
    @Retry(maxRetries = 3)
    public Pirate getNext() {
        System.out.println("retrying");
        return this.pirateFactory.create();
    }

    public Pirate defaultPirate() {
        return new Pirate(this.message + " destruction", this.destroyedShips);
    }


}
