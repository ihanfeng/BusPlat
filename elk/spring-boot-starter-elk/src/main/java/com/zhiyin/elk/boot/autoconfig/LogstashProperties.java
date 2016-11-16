package com.zhiyin.elk.boot.autoconfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "logstash")
@Getter
@Setter
public class LogstashProperties {
    private String host;
    private int    port;
    private String level;
}