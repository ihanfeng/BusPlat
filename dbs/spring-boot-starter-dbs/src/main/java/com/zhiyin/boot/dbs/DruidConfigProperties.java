package com.zhiyin.boot.dbs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.druid")
public class DruidConfigProperties {

    private String urlMappings = "/druid/*" ;
    //reg.addInitParameter("allow", "127.0.0.1"); //白名单
    //reg.addInitParameter("deny",""); //黑名单
    private String loginUsername ;

    private String loginPassword;

}
