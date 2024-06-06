package com.adobe.aem.guides.rnd.core.models.impl;

import com.adobe.aem.guides.rnd.core.models.SegmentDataSourceModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {SegmentDataSourceModel.class},
        resourceType = {SegmentDataSourceModelimpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class SegmentDataSourceModelimpl implements SegmentDataSourceModel {
    protected static final String RESOURCE_TYPE = "rnd/components/segmentDataSource";
    @ValueMapValue
    private String name;
    @ValueMapValue
    private String segmentTags;

    @PostConstruct
    private void init() {
        // set the image object
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSegmentTags() {
        return segmentTags;
    }

    @Override
    public boolean isEmpty() {
        if (StringUtils.isBlank(name)) {
            // Name is missing, but required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
}

