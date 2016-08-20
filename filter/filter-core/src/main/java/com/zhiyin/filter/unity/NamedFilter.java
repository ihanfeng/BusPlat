package com.zhiyin.filter.unity;

import javax.servlet.Filter;

/**
 * Created by wangqinghui on 2016/8/18.
 */
public interface NamedFilter extends Filter {

    public String getName();
}
