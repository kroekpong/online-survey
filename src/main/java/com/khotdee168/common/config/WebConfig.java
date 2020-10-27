package com.khotdee168.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	 @Value("${spring.web.config.prefix}") 
	 private String VIEW_PREFIX;
	 
	 @Value("${spring.web.config.suffix}") 
	 private String VIEW_SUFFIX;
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(60); // reload messages every 10 seconds
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
//		   SessionLocaleResolver slr = new SessionLocaleResolver();
//		    slr.setDefaultLocale(Locale.US);
//		    return slr;
		 return new CookieLocaleResolver();
	}

	@Bean
	public LocaleChangeInterceptor localeInterceptor() {
	    LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
	    localeInterceptor.setParamName("lang");
	    return localeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_PREFIX);
		viewResolver.setSuffix(VIEW_SUFFIX);

		return viewResolver;
	}

	@Bean
	public View jsonTemplate() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		return view;
	}
	
	@Bean
    public ObjectMapper customJson() {
        return new Jackson2ObjectMapperBuilder()
            .indentOutput(true)
            .propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
            .build();
    }
	
	
	 

}
