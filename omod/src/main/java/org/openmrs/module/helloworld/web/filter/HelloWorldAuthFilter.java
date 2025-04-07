package org.openmrs.module.helloworld.web.filter;

import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HelloWorldAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if this is for our endpoint
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (path.equals("/rest/" + RestConstants.VERSION_1 + "/helloworld")) {
            // Verify authentication
            if (!Context.isAuthenticated()) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "You must be authenticated to access this endpoint");
                return;
            }

            // Verify privilege (optional)
            if (!Context.hasPrivilege("Get Hello World")) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "You don't have permission to access this endpoint");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}