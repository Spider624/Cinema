package edu.school21.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "edu.school21.cinema")
public class WebAppConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setCache(true);
		viewResolver.setSuffix(".ftl");

		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
		return configurer;
	}
}