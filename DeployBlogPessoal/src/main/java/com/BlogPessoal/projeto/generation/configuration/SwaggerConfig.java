package com.BlogPessoal.projeto.generation.configuration;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.BlogPessoal.projeto.generation.controladores"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responseMessageForGET());
    }

    public static ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("API - BLOG Pessoal")
                .description("Projeto API Spring - blog pessoal")
                .version("1.0.0")
                .license("Apache Lincese Version 2.0")
                .licenseUrl("http://localhost:8080/swagger-ui/")
                .contact(contact())
                .build();
    }

    public static Contact contact() {
        return new Contact("Jonathan TLB", "https://github.com/jonathantlb", "jonathan_tlb@hotmail.com");
    }

    private static List<Response> responseMessageForGET() {
        return new ArrayList<Response>() {
            private static final long serialVersionUID = 1L;

            {

                add(new ResponseBuilder().code("200").description("SUCESSO!🙌").build());
                add(new ResponseBuilder().code("201").description("OBJETO CRIADO!👌").build());
                add(new ResponseBuilder().code("401").description("NÃO AUTORIZAD0!👤‍♂️").build());
                add(new ResponseBuilder().code("403").description("PROIBIDO!🤐").build());
                add(new ResponseBuilder().code("404").description("NÃO ENCONTROU!👀").build());
                add(new ResponseBuilder().code("500").description("ERRO!💀").build());
            }
        };
    }

}
