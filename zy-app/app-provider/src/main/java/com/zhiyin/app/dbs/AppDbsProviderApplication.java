package com.zhiyin.app.dbs;

import com.zhiyin.boot.dbs.DruidAutoConfiguration;
import com.zhiyin.boot.dbs.EnableDruidAutoConfiguration;
import com.zhiyin.boot.dubbo.DubboConfigurationApplicationContextInitializer;
import com.zhiyin.boot.dubbo.EnableDubboAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableEurekaClient
@SpringBootApplication
@EnableDruidAutoConfiguration
@EnableDubboAutoConfiguration
//public class AppDbsProviderApplication extends SpringBootServletInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//
//        return application.sources(AppDbsProviderApplication.class).initializers( new DubboConfigurationApplicationContextInitializer() );
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(AppDbsProviderApplication.class, args);
//    }
//
//}
//
public class AppDbsProviderApplication {
    public static void main( String[] args ) {

        SpringApplication.run(AppDbsProviderApplication.class, args);

//        new SpringApplicationBuilder(AppDbsProviderApplication.class)
//                .initializers( new DubboConfigurationApplicationContextInitializer() )
//        .run(args);
    }
}
