package br.com.williangiovanni.springbootrestapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.williangiovanni.springbootrestapi.utils.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**").allowedMethods("*")
                .allowedOrigins(allowedOrigins).allowCredentials(true);
    }

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
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
    }

}
