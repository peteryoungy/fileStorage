package com.peteryu.filestorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        // note: avoid the logic in getMapping, priority than getMapping?
//        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("login");
        // registry.addViewController("/hello").setViewName("hello");
        //registry.addViewController("/login").setViewName("login");
    }


}