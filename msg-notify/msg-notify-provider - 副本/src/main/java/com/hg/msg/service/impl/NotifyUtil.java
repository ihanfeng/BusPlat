package com.hg.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hg.msg.entity.MsgSubscriptionConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hg on 2016/3/29.
 */
public class NotifyUtil {

    public static MsgSubscriptionConfig defaultSubConfig() {

        MsgSubscriptionConfig sub = new MsgSubscriptionConfig();


        Map<String, String> map = Maps.newHashMap();
        map.put("comment", "true");
        map.put("like", "true");

        sub.setAction(JSON.toJSONString(map));

        return sub;
    }


    public static List<String> getSubAction(String action) {

        JSONObject obj = JSON.parseObject(action);

        Set<String> keys = obj.keySet();

        List<String> actionList = Lists.newArrayList();
        for (String key : keys) {
            String val = (String) obj.get(key);
            if ("true".equals(val)) {
                actionList.add(key);
            }
        }

        return actionList;
//        JSON.parseObject()

    }


    public static void main(String[] args) {

        Map<String, String> map = Maps.newHashMap();

        map.put("comment", "true");
        map.put("like", "true");

        String actionStr = JSON.toJSONString(map);
        System.out.println(actionStr);


        System.out.print(getSubAction(actionStr));
    }
}
