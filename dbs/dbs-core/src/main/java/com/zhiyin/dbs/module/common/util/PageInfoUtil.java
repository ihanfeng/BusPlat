package com.zhiyin.dbs.module.common.util;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
/**
 * Created by hg on 2016/8/18.
 */
public class PageInfoUtil {
    public static String defaultOrderBy = "id desc";
    public static String defaultOrderBy(PageInfo pageInfo){
        if(pageInfo == null ){
            return defaultOrderBy;
        }
        return Optional.fromNullable(pageInfo.getOrderBy()).or(defaultOrderBy);
    }
}