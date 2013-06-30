package be.appletree.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * Created by jochen on 6/28/13.
 * Source: http://smokeandice.blogspot.be/2010_01_01_archive.html
 */
@WebFilter("/*")
@WebListener
public class RequestLoggingContext implements Filter, HttpSessionListener {
    private static final String REQUEST_CONTEXT = "request-context";
    private static final String SESSION_CONTEXT = "session-context";

    private Logger log = LoggerFactory.getLogger(RequestLoggingContext.class);

    @Inject
    private ContextGenerator contextGenerator;

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("contextGenerator:"+contextGenerator);
        System.out.println("log:"+log);
//        MDC.put(REQUEST_CONTEXT, contextGenerator.generateContextId());

        StringBuilder msg = new StringBuilder();
        if(req instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest)req;
            HttpSession session = httpRequest.getSession(false);

            if(session != null)
                MDC.put(SESSION_CONTEXT, session.getId());

            //Build Detailed Message
            msg.append("Starting ");
            msg.append(httpRequest.getMethod());
            msg.append(" request for URL '");
            msg.append(httpRequest.getRequestURL());
            if(httpRequest.getMethod().equalsIgnoreCase("get") && httpRequest.getQueryString() != null) {
                msg.append('?');
                msg.append(httpRequest.getQueryString());
            }
            msg.append("'.");
        }

        if(msg.length() == 0) {
            msg.append("Starting new request for Server '");
            msg.append(req.getScheme());
            msg.append(":\\");
            msg.append(req.getServerName());
            msg.append(':');
            msg.append(req.getServerPort());
            msg.append('/');
        }

        log.debug(msg.toString());
        long startTime = System.currentTimeMillis();

        chain.doFilter(req, resp);

        msg.setLength(0);
        msg.append("Request processing complete.  Time Elapsed -- ");
        msg.append(System.currentTimeMillis() - startTime);
        msg.append(" ms.");
        log.debug(msg.toString());

        if(((HttpServletRequest)req).getSession(false) != null) {
            MDC.remove(SESSION_CONTEXT);
        }
        MDC.remove(REQUEST_CONTEXT);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        MDC.put(SESSION_CONTEXT, hse.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
    }
}