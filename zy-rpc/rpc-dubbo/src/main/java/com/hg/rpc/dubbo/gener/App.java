package com.hg.rpc.dubbo.gener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luo
 */
@RestController
public class App {
        @RequestMapping(value = "/router/", method = RequestMethod.POST)
        public Object getUser(@ModelAttribute RequestDto dto) {
            Map map = new HashMap<>();
            map.put("ParamType", "java.lang.String");  //后端接口参数类型
            map.put("Object", dto.getMethodParams()[0].get("id"));  //用以调用后端接口的实参

            List<Map<String, Object>> paramInfos= new ArrayList<>();
            paramInfos.add(map);

            DubboServiceFactory dubbo = DubboServiceFactory.getInstance();

            return dubbo.genericInvoke(dto.getInterfaceName(), dto.getMethodName(), paramInfos);
        }
}