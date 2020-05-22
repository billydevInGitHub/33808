package com.spring.boot.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Basant on 2017-06-04.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON)
				.favorPathExtension(true);
	}


	@Bean
	public InternalResourceViewResolver internalViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewNames("hello");
		return resolver;
	}

	/*
	 * Configure ContentNegotiatingViewResolver
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(
			ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<>();
//		resolvers.add(csvViewResolver());
//		resolvers.add(excelViewResolver());
		resolvers.add(pdfViewResolver());
		resolvers.add(internalViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/*
	 * Configure View resolver to provide XLS output using Apache POI library to
	 * generate XLS output for an object content
	 */
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	/*
	 * Configure View resolver to provide Csv output using Super Csv library to
	 * generate Csv output for an object content
	 */
	@Bean
	public ViewResolver csvViewResolver() {
		return new CsvViewResolver();
	}

	/*
	 * Configure View resolver to provide Pdf output using iText library to
	 * generate pdf output for an object content
	 */
	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}

}