/* *  Copyright 2015 Adobe Systems Incorporated * *  Licensed under the Apache License, Version 2.0 (the "License"); *  you may not use this file except in compliance with the License. *  You may obtain a copy of the License at * *      http://www.apache.org/licenses/LICENSE-2.0 * *  Unless required by applicable law or agreed to in writing, software *  distributed under the License is distributed on an "AS IS" BASIS, *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. *  See the License for the specific language governing permissions and *  limitations under the License. */
package com.adobe.aem.guides.rnd.core.servlets;

import com.adobe.aem.guides.rnd.core.services.SampleService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class}, property = {ServletResolverConstants.SLING_SERVLET_PATHS + "=" + ServiceCallingServlet.SERVLET_PATH, ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET, ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=notext"})
@ServiceDescription("Simple Demo Servlet")
public class ServiceCallingServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    static final String SERVLET_PATH = "/bin/ServiceCalling";
    @Reference
    private SampleService sampleService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Title222 Taken Will work Fine now the next level = \n");
        resp.getWriter().write("Sample Service Calling Activities " + sampleService.getRandomActivity());
        resp.getWriter().write("\nResource  " + resource.getPath());
    }
}