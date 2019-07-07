package com.svcg.StockCustom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(
                        new ApiInfo(
                                "Carniceria",
                                "Carniceria RESTfull API",
                                "1.0",
                                null,
                                new Contact("SVCG", "", "darioegb@gmail.com"),
                                null,
                                null,
                                Collections.emptySet()
                        )
                )
                .securitySchemes(Arrays.asList(authorizationBearer()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference(
                                "apiKey",
                                new AuthorizationScope[]{new AuthorizationScope("global", "Global access")}
                        ))
                )
                .build();
    }

    private ApiKey authorizationBearer() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
}
