package br.com.williangiovanni.springbootrestapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Content Negotiation with query params
    // @Override
    // public void configureContentNegotiation(ContentNegotiationConfigurer
    // configurer) {
    // configurer.favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
    // .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
    // .mediaType("json", MediaType.APPLICATION_JSON)
    // .mediaType("xml", MediaType.APPLICATION_XML);
    // }

    // Content Negotiation with header param
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false).ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }

}
