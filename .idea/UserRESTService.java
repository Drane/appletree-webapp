package be.appletree.web.rest;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by jochen on 6/30/13.
 */
@Path("/user")
@RequestScoped
pujetty-jndi.jarblic class UserRESTService {

    @Inject
    Logger log;//

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response.ResponseBuilder get(){
        log.trace("Redirecting...");
        return Response.temporaryRedirect(URI.create("signin"));
    }


}
