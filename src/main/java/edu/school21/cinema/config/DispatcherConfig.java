package edu.school21.cinema.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"edu.school21.cinema"}) // Scans the following packages for classes with @Controller annotations
public class DispatcherConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Provide a view resolver to map views to the correct template files.
	 *
	 * @return
	 */
	@Bean
	public ViewResolver getViewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		System.out.println("Create Bean viewResolver");

		viewResolver.setCache(true);
		viewResolver.setPrefix("/WEB-INF/freemaker/");
		viewResolver.setSuffix(".ftl");
		return viewResolver;

	}
//	@Bean(name = "freemarkerConfig")
//	public FreeMarkerConfigurer getFreemarkerConfig() {
//		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
//
//		// Folder containing FreeMarker templates.
//		config.setResourceLoader("/WEB-INF/"); //setTemplateLoaderPath("/WEB-INF/");
//		return config;
//	}
}