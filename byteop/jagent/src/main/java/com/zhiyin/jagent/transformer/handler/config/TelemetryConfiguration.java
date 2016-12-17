package com.zhiyin.jagent.transformer.handler.config;


import java.util.Collections;
import java.util.List;

public class TelemetryConfiguration {
    private List<String> handlers = Collections.emptyList();
    private SinkConfiguration sinks = new SinkConfiguration();

    public List<String> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<String> handlers) {
        this.handlers = handlers;
    }
}
