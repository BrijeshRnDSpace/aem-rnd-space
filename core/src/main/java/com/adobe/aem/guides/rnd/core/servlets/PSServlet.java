package com.adobe.aem.guides.rnd.core.servlets;

import com.drew.lang.annotations.NotNull;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletName;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletName(servletName = "PSServlet Navigation Api")
@SlingServletResourceTypes(resourceTypes = {ServletResolverConstants.DEFAULT_RESOURCE_TYPE, "/apps/rnd/components/page"}, selectors = "data", extensions = "json", methods = HttpConstants.METHOD_GET)
public class PSServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(@NotNull SlingHttpServletRequest request, @NotNull SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello MR PSSerssss22222222222vletsss Dashing fine either thek h Personalities addsa");
    }
}