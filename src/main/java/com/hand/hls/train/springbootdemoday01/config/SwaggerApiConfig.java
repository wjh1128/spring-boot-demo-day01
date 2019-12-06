package com.hand.hls.train.springbootdemoday01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * SwaggerApiConfig的配置类
 * </p>
 *
 * @author jinhua.wu@hand-china.com 2019/12/05 15:43
 */

@Configuration
@EnableSwagger2
public class SwaggerApiConfig {
    public static final String USER_INFO = "User Info";
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(
                        new Tag(USER_INFO, "用户信息管理")
                )
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hand.hls.train.springbootdemoday01.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot 学习")
                .description("简单的基于SSM 搭建的 REST 风格的 增删改查")
                .version("1.0")
                .build();
    }
}
