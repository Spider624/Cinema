package edu.school21.cinema.services;

import edu.school21.cinema.config.ApplicationConfig;
import edu.school21.cinema.config.DispatcherConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebInitParam;


public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {


		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfig.class);

//		rootContext.refresh();
		rootContext.setServletContext(container);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Register and map the dispatcher servlet
		DispatcherServlet dv = new DispatcherServlet(new GenericWebApplicationContext());
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", dv);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/cinema/*");
	}

}