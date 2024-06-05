package com.adobe.aem.guides.rnd.core.services.config;

import org.osgi.service.metatype.annotations.*;

@ObjectClassDefinition(name = "Sample Services Configuration", description = "This is sample configuration")
public @interface SampleServiceConfig {

    @AttributeDefinition(name = "Blog name", description = "Name of the blog")
    String blog_name();

    @AttributeDefinition(name = "Blog Url")
    String blog_URL();

    // Multi-values property
    @AttributeDefinition(name = "Blog Topics")
    String[] blog_Topics();

    @AttributeDefinition(name = "Blog Count", description = "Total number of blogs", type = AttributeType.INTEGER)
    int blogCount();

    // Password
    @AttributeDefinition(name = "password", type = AttributeType.PASSWORD)
    String password();

    // Checkbox
    @AttributeDefinition(name = "Blog is active?")
    boolean blogIsActive();

    // Dropdown
    @AttributeDefinition(name = "Blog is hosted at?", options = {@Option(label = "WordPress", value = "wordpress"),
            @Option(label = "Blogspot", value = "blogspot")})
    String hostedAt() default "";
}

