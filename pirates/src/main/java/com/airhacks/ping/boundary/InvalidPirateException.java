
package com.airhacks.ping.boundary;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
public class InvalidPirateException extends WebApplicationException {

    public InvalidPirateException(String message) {
        super(Response.status(400).header("reason", message).build());
    }

}
