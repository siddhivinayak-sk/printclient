package com.dps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/").addResourceLocations("/scripts/**");
    }    
	
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver irvr = new InternalResourceViewResolver();
        irvr.setPrefix("/WEB-INF/jsps/");
        irvr.setSuffix(".jsp");
        irvr.setOrder(0);
        return irvr;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
    	ReloadableResourceBundleMessageSource s = new ReloadableResourceBundleMessageSource();
    	s.setBasename("classpath:message");
    	s.setDefaultEncoding("UTF-8");
    	return s;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry
    	.addMapping("/**")
    	.allowedOrigins("*")
    	.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
    	.maxAge(3600);
    }
}
