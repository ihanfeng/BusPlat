package com.zhiyin.elk.boot.autoconfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Data
@ConfigurationProperties(prefix = "logstash")
public class LogstashProperties {
    private String host;
    private int    port;
    private String level;
}