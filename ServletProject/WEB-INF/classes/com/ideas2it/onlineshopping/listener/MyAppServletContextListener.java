package com.ideas2it.onlineshopping.listener;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * <p>
 * Listener class which loads each time before web appication is started
 * </p>
 */
public class MyAppServletContextListener implements ServletContextListener{

    /**
     * <p>
     * Method in which the configuration file for logger was initialized once at
     * the start of application
     * </p>
     */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        DOMConfigurator.configure(fullPath);
	}
}
