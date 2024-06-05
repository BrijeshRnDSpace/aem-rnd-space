package com.adobe.aem.guides.rnd.core.servlets;

import com.adobe.aem.guides.rnd.core.services.AzureServiceBusUploaderService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class}, property = {ServletResolverConstants.SLING_SERVLET_PATHS + "=" + AzureServlet.SERVLET_PATH, ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET, ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=txt"})
@ServiceDescription("Simple Demo Servlet")
public class AzureServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    static final String SERVLET_PATH = "/bin/azureservicecalling";
    @Reference
    AzureServiceBusUploaderService azureServiceBusUploaderService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Title Taken Will work Fine now the next level = ");
        resp.getWriter().write("File name from azure sevice " + azureServiceBusUploaderService.processPayloadUpload("My New File"));
    }
}