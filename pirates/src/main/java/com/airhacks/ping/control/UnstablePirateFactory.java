
package com.airhacks.ping.control;

import com.airhacks.ping.entity.Pirate;

/**
 *
 * @author airhacks.com
 */
public class UnstablePirateFactory {

    public Pirate create() {
        throw new RuntimeException("no pirates today");
        //return new Pirate("izydor", 13);
    }


}
