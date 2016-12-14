package com.zhiyin.logger.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.Map;

/**
 * Created by wangqinghui on 2016/11/16.
 */
public class JsonLayout extends ch.qos.logback.contrib.json.classic.JsonLayout {

    public JsonLayout() {
        super();
    }

    @Override
    protected Map toJsonMap(ILoggingEvent event) {
        Map map = super.toJsonMap(event);

        if (this.includeMDC) {
            Map<String, String> mdc = event.getMDCPropertyMap();
            if ((mdc != null) && !mdc.isEmpty()) {
                for (Map.Entry<String, String> entry : mdc.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return map;
    }

}
