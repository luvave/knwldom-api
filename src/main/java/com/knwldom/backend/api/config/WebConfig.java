package com.knwldom.backend.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${client.url}")
    private String allowedClient;

    @Override
    public void addCorsMappings(CorsRegistry reg) {
        reg.addMapping("/api/**").allowedOrigins(allowedClient).allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
    }
}