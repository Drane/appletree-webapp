package be.appletree.cdi;

import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;
//org.jboss.weld.environment.servlet.Listener<
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by jochen on 6/30/13.
 */
@Singleton
//@Application
@ApplicationScoped
public class BootstrapSingleton {
    @PostConstruct
    public void strapTheBoot(@Observes ContainerInitialized event, @Parameters List<String> parameters) {

        System.out.println("Bootstrapping with parameters: " + parameters.toString());

    }
}
