package be.appletree.web.rest;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by jochen on 6/30/13.
 */
@Path("info")
@RequestScoped
public class InfoRESTService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GET
    public String get(){
        log.trace("Returning info...");

        return "version: 0.0.1";
    }

}
