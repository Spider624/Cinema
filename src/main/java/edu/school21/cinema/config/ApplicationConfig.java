package edu.school21.cinema.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "edu.school21.cinema")

public class ApplicationConfig {
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
//		configurer.enable();
//	}

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		System.out.println("Create Bean viewResolver");

		viewResolver.setCache(true);
		viewResolver.setPrefix("/WEB-INF/freemaker/");
		viewResolver.setSuffix(".ftl");
		return viewResolver;

	}
}