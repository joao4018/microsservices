package com.microsservices.course.docs;

import com.microsservices.core.docs.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author joao4018
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig() {
        super("academy.devdojo.youtube.course.endpoint.controller");
    }
}
