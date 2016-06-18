package com.zhiyin.fms.config.swagger;

/**
 * Created by hg on 2016/4/2.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // Book API
    @Bean
    public Docket smsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SmsApi")
                .genericModelSubstitutes(DeferredResult.class)
//                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/sms/.*")))//过滤的接口
                .build()
                .apiInfo(smsApiInfo());

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

    private ApiInfo smsApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Sms Index Api",//大标题
                "Sms Index Api, for rest invoke.",//小标题
                "1.0",//版本
                "NO terms of service",
                "hg",//作者
                "The Apache License, Version 2.0",//链接显示文字
                "http://www.apache.org/licenses/LICENSE-2.0.html"//网站链接
        );

        return apiInfo;
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