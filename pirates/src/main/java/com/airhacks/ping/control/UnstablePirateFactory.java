
package com.airhacks.ping.control;

import com.airhacks.ping.entity.Pirate;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author airhacks.com
 */
public class UnstablePirateFactory {

    @Inject
    @ConfigProperty(name = "MESSAGE")
    String message;


    public Pirate create() {
        //throw new RuntimeException("no pirates today");
        return new Pirate(this.message, 13);
    }


}
