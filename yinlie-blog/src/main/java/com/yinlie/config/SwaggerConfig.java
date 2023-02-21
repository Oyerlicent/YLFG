package com.yinlie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yinlie.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("隐良网址（不存在滴）", "http://www.yl.com", "yl@yl.com");
        return new ApiInfoBuilder()
                .title("博客项目文档")
                .description("非常滴粗糙")
                .contact(contact)   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}