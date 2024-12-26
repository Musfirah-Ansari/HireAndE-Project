package com.bank.alfalah.config;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig   {

    @Value("${enable.swagger.plugin:true}")
    private boolean enableSwaggerPlugin;

    @Bean
    public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bank.alfalah.feature"))
                .paths(PathSelectors.any()).build()
                .enable(enableSwaggerPlugin)
                .apiInfo(apiInfo()).globalResponseMessage(RequestMethod.POST, ImmutableList.of(new ResponseMessageBuilder()
                .code(400)
                .message("Bad Request")
                .responseModel(new ModelRef("Error")).build(), new ResponseMessageBuilder()
                .code(500)
                .message("Internal Server Error")
                .responseModel(new ModelRef("Error")).build()));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Hire @ E Web API")
                .description("Core Hire @ E Web API\n"+
                        "Deen Muhammad Khan\n" +
                        " Manager-Software Development-Engineer | Digital Department\n" +
                        "X-Loop Pvt Ltd.\n")
                .termsOfServiceUrl("http://www.bankalfalah.com")
                .licenseUrl("deen.muhammad@bankalfalah.com").version("1.0").build();
    }
}