package com.app.config;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket configDocket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(basePackage("com.app.controller.rest"))
				.paths(regex("/rest.*"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("SATHYA BOOT SWAGGER")
				.description("WELCOME TO SATHYA")
				.contact(new Contact("sathyatech", "https://www.sathyatech.com", "abcd@gmail.com"))
					.license("Apache 2.0")
					.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
					.version("1.0.0")
					.build();
	}

}
