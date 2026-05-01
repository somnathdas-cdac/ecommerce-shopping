package com.app.ecommerce.shopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Expose your external folder to the web at the path "/product-images/"
        registry.addResourceHandler("/product-images/**")
                .addResourceLocations("file:ItemImages/");
    }
}
