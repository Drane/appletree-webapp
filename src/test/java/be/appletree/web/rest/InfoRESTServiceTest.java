package be.appletree.web.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

import static org.testng.Assert.assertEquals;

/**
 * Created by jochen on 6/30/13.
 */
public class InfoRESTServiceTest {

    public static final String BASE_URI = "http://localhost:8080";
    private WebTarget target;
    private HttpServer server;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("be.appletree.web.rest");

        // uncomment the following line if you want to enable
        // support for JSON on the service (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml)
        // --
        // rc.addBinder(org.glassfish.jersey.media.json.JsonJaxbBinder);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application atsignin BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        server = startServer();

        Client c = ClientBuilder.newClient();
        target = c.target(BASE_URI);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("info").request().get(String.class);
        assertEquals("version: 0.0.1", responseMsg);
    }
}
