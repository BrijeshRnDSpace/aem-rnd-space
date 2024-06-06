package com.adobe.aem.guides.rnd.core.models;


import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

public interface SegmentDataSourceModel {
    @ValueMapValue
    String getName();

    @ValueMapValue
    String getSegmentTags();
    boolean isEmpty();
}
