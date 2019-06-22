package com.dps.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)                                   
				.globalResponseMessage(RequestMethod.GET,
				  Arrays.asList(new ResponseMessageBuilder()   
				    .code(500)
				    .message("500 Internal Server Error")
				    .responseModel(new ModelRef("string"))
				    .build(),
				    new ResponseMessageBuilder() 
				      .code(403)
				      .message("Forbidden!")
				      .build()))
				.apiInfo(apiInfo())
				;
	}
	
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Print Client", 
	      "This is a small printing client installed on users machine.", 
	      "Not for Operation users", 
	      "Terms of service", 
	      new Contact("Sandeep Kumar", "com.sk.hcl", "kumar-sand@hcl.com"), 
	      "License of API", "www.hcl.com", Collections.emptyList());
	}
}
