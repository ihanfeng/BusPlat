package com.hg.msg.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqinghui on 2016/3/31.
 */
public class NotifyConfig {

    public static String reasonAction = "\n" +
            " {\n" +
            "  'create_product'  : ['comment', 'like']\n" +
            "  'like_product'    : ['comment'],\n" +
            "  'like_post'       : ['comment'],\n" +
            "}";


    public static List<String> action(String reason) {
//        JSONObject reasonJson = JSON.parseObject(NotifyConfig.reasonAction);
//
//        String[] actions = (String[]) reasonJson.get(reason);

        Map<String, ArrayList<String>> map = Maps.newHashMap();


        ArrayList<String> actions = Lists.newArrayList();
        actions.add("comment");
        actions.add("like");

        map.put("create_product", actions);

        ArrayList<String> actions2 = Lists.newArrayList();
        actions2.add("comment");
        map.put("like_product", actions2);


        List<String> ret = Lists.newArrayList();
        for (String tmp : map.get(reason)) {
            ret.add(tmp);
        }
        return ret;
    }
}
