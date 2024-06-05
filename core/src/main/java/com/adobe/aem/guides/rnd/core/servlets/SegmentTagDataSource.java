package com.adobe.aem.guides.rnd.core.servlets;

import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component(
        service = {Servlet.class},
        property = {
                ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + SegmentTagDataSource.RESOURCE_TYPE,
                // ServletResolverConstants.SLING_SERVLET_PATHS + "=" + "/bin/mytags/lists",
                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
                ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=html"
        }
)
public class SegmentTagDataSource extends SlingSafeMethodsServlet {

    protected static final String RESOURCE_TYPE = "email/components/datasource/tags";
    protected static final String SEGMENT_TAG_NAMESPACE = "/content/cq:tags/segment";
    private static final String DATASOURCE_TEXT = "text";
    private static final String DATASOURCE_VALUE = "value";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            List<Tag> segmentTags = getSegmentTags(resourceResolver);
            List<Resource> segmentTagDatasourceElements = convertObjectsToResources(resourceResolver, segmentTags);
            SimpleDataSource segmentTagsDatasource = new SimpleDataSource(segmentTagDatasourceElements.iterator());
            request.setAttribute(DataSource.class.getName(), segmentTagsDatasource);
     /*      response.setContentType("text/plain");
            for (Tag tag : segmentTags) {
                response.getWriter().write("\n SegmentTagDataSource List to populate all tags :  " + tag.getName());
            }*/

            log.info(" \nSegmentTagDataSource Servlet to populate all tags :  ");
        } catch (Exception e) {
            log.error("Exception while creating Segment Tags DataSource", e);
        }
    }

    protected List<Resource> convertObjectsToResources(ResourceResolver resourceResolver, List<Tag> tags) {
        List<Resource> dataSource = new ArrayList<>();
        for (Tag tag : tags) {
            Resource dataSourceElement = createDataSourceElement(resourceResolver, tag.getName(), tag.getName());
            dataSource.add(dataSourceElement);
        }
        return dataSource;
    }

    private List<Tag> getSegmentTags(ResourceResolver resourceResolver) {
        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
        List<Tag> tagList = tagManager != null
                ? IteratorUtils.toList(tagManager.resolve(SEGMENT_TAG_NAMESPACE).listAllSubTags())
                : Collections.emptyList();
        Collections.reverse(tagList);
        return tagList;
    }

    private Resource createDataSourceElement(ResourceResolver resolver, String key, String value) {
        ValueMap valueMap = new ValueMapDecorator(new HashMap<>());
        valueMap.put(DATASOURCE_VALUE, key);
        valueMap.put(DATASOURCE_TEXT, value);
        return new ValueMapResource(resolver, StringUtils.EMPTY, StringUtils.EMPTY, valueMap);
    }
}
