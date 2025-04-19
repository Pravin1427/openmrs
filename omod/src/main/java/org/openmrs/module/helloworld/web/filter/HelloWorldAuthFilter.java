package org.openmrs.module.helloworld.web.filter;

import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HelloWorldAuthFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("✅ HelloWorldAuthFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/ws/rest/v1/helloworld")) {
            log.info("🛡️  HelloWorldAuthFilter triggered for path: {}", path);

            // Check if user is authenticated
            if (!Context.isAuthenticated()) {
                log.warn("❌ Unauthorized access attempt to {}", path);
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication required");
                return;
            }

            // (Optional) Check privilege
            if (!Context.hasPrivilege("Get Hello World")) {
                log.warn("🚫 Forbidden: Missing privilege for path {}", path);
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient privilege");
                return;
            }

            log.info("✅ User is authenticated and has required privilege.");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("🧹 HelloWorldAuthFilter destroyed");
    }
}
