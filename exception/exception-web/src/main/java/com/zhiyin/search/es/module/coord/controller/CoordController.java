package com.zhiyin.search.es.module.coord.controller;

import com.zhiyin.exception.entity.CoordPoint;
import com.zhiyin.exception.factory.CoordConvFactory;
import com.zhiyin.search.es.module.common.controller.BaseController;
import com.zhiyin.search.es.module.coord.model.CoordConvS2c;
import com.zhiyin.search.es.web.S2cObj;
import com.zhiyin.search.es.web.WebResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/coord")
public class CoordController extends BaseController {

    @RequestMapping("/conv")
    public WebResp<S2cObj> conv(
            @RequestParam(value = "lon", required = false) Double lon,
            @RequestParam(value = "lat", required = false) Double lat,
            @RequestParam(value = "from", required = false) Integer from,
            @RequestParam(value = "to", required = false) Integer to
            ) {

        CoordPoint conv = CoordConvFactory.convTo(lon, lat, from, to);

        CoordConvS2c s2c = new CoordConvS2c();
        s2c.setLat(conv.getLat());
        s2c.setLon(conv.getLng());

        return succRet(s2c);
    }

}
