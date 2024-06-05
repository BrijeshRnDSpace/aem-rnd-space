package com.adobe.aem.guides.rnd.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.PageManagerFactory;
import com.day.cq.wcm.api.WCMException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(
        service = {Servlet.class},
        property = {
                Constants.SERVICE_DESCRIPTION + "= This servlet is developed to delete pages",
                ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/deletepages",
                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET
        }
)
public class DeletePagesServlet extends SlingSafeMethodsServlet {
/*
    @Reference
    private PageManagerFactory pageManagerFactory;*/

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String directoryPath = request.getParameter("directoryPath");
        ResourceResolver resourceResolver = request.getResourceResolver();

        if ((directoryPath == null) || directoryPath.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(" Directory Path is missing in the query parameter");
            return;
        }

        Resource directoryResource = resourceResolver.getResource(directoryPath);
        if (directoryResource == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Given directory not found in AEMs");
            return;
        }
        //PageManager pageManager = pageManagerFactory.getPageManager(resourceResolver);

        for (Resource child : directoryResource.getChildren()) {
            resourceResolver.delete(child);
        }
        resourceResolver.delete(directoryResource);
        resourceResolver.commit();
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Pages deleted successfully == " + directoryPath);
    }
}
