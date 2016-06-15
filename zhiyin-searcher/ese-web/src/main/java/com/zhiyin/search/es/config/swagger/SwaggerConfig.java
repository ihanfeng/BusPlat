package com.zhiyin.search.es.config.swagger;

/**
 * Created by hg on 2016/4/2.
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by wangqinghui on 2016/2/4.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 内容API Doc
    @Bean
    public Docket contentsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("内容API")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/contents.*"))
                .build();
    }

    // 测试API Doc
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("测试API")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/test.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()

                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("Niklas Heidloff")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

}