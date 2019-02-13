package net.sp.referenceapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "net.sp.referenceapp.rest")
public class SwaggerConfig {

  private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);


  @Bean
  public Docket customDocket() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any()).build().apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {

    ApiInfo apiInfo = new ApiInfo("Ref App Api Documentation",
        "This is REST API documentation for the Reference App.", "1.0.0",
        "Ref app backend terms of service", ApiInfo.DEFAULT_CONTACT, "", "", new ArrayList<>());

    return apiInfo;
  }

}
