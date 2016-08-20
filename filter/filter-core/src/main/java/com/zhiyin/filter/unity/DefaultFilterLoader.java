package com.zhiyin.filter.unity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangqinghui on 2016/8/18.
 */
public class DefaultFilterLoader implements FilterLoader {

    @Override
    public List<NamedFilter> load() {
        List<NamedFilter> filters = Lists.newArrayList();
        filters.add(new DemoFilter1());
        filters.add(new DemoFilter2());

        return filters;
    }
}
