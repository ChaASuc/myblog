package com.deschen.myblog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author deschen
 * @Create 2019/4/28
 * @Description  前后端分离后台提供api接口
 * @Since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Api {

    @Bean
    public Docket createRestApi() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("个人博客项目接口文档")
                .description("项目使用RESTful API 风格")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.deschen.myblog.modules.system.controller")) //以扫描包的方式
                .paths(PathSelectors.any())
                .build();
    }
}
