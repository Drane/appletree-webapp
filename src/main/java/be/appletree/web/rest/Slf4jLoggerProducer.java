package be.appletree.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;
import javax.ws.rs.Produces;

/**
 * Created by jochen on 6/26/13.
 * Pattern: %-5p [%d{HH:mm:ss.SSS}] [%s%E] {%X{session-context}} @[%l]%n
 */
@Singleton
public class Slf4jLoggerProducer {

    @Produces
    private Logger createLog(final InjectionPoint injectionPoint){
        Logger log = LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
        /*
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
         */
        return log;
    }

}