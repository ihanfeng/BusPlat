package com.zhiyin.filter.unity;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

@Slf4j
public class FilterLoaderFactory {

    public static FilterLoader get() {

        FilterLoader fl = null;

        ServiceLoader<FilterLoader> filters = ServiceLoader.load(FilterLoader.class);
        Iterator<FilterLoader> fls = filters.iterator();
        if (fls.hasNext()) {
            fl = fls.next();
        }
        if (fl == null) {
            fl = new DefaultFilterLoader();
        }
        return fl;

    }

    public static void main(String[] args) {
        FilterLoader fl = FilterLoaderFactory.get();
        List<NamedFilter> filters = fl.load();

        log.info(JSON.toJSONString(filters));
    }
}  