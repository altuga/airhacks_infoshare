/*
 */
package com.airhacks.pirates.boundary;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class PiratesResourceIT {

    private Client client;
    private WebTarget tut;

    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.tut = this.client.target("http://localhost:9080/pirates/resources/ping");

    }

    @Test
    public void getPirates() {
        JsonObject pirate = this.tut.request().get(JsonObject.class);
        System.out.println("pirate = " + pirate);
    }

}
