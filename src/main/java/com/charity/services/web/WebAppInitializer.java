package com.charity.services.web;

import com.charity.services.config.AppConfig;
import com.charity.services.config.SwaggerConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes the web app. Servlet 3.0 will pick up this class automatically.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  private static final String ROOT_PATH = "/";

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{AppConfig.class, SwaggerConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{ROOT_PATH};
  }
}