package be.appletree.web.rest;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jochen on 6/30/13.
 */
@Path("/signin")
@RequestScoped
public class SignInRESTService {

    @Inject
    Logger log;//= LoggerFactory.getLogger(this.getClass());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(){
        log.debug("in GET:");


        return "hierzie";
    }

}
