package org.inria.ns.tp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/welcome.txt")
public class ZooService {

    @GET
    @Produces("text/plain")
    public String welcome() {
        return "Bienvenue au Zoo !";
    }

}