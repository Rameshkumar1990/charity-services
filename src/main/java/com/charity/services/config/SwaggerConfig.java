package com.charity.services.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class used to configure swagger.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

  private static final String BASIC_AUTH = "basic-auth";

  @Value("${swagger.enabled:true}")
  private boolean swaggerEnabled;

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  /**
   * Initialize swagger docket including security and authentication information.
   *
   * @return The swagger docket.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .build()
        .enable(swaggerEnabled)
        .apiInfo(apiInfo())
        .securitySchemes(Lists.newArrayList(new BasicAuth(BASIC_AUTH)))
        .securityContexts(Lists.newArrayList(securityContext()));
  }

  /**
   * Create the generic security context with authentication and default authorization.
   *
   * @return the SecurityReference pointing to the authentication mode.
   */
  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(Lists.newArrayList(new SecurityReference(BASIC_AUTH, new AuthorizationScope[0])))
        .build();
  }

  /**
   * Initialize api info.
   *
   * @return the swagger api info.
   */
  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .description("A swagger UI to test the DAP REST API operations.")
        .title("DAP REST API")
        .build();
  }
}