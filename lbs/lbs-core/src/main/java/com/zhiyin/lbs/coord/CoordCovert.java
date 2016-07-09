package com.zhiyin.lbs.coord;

import com.alibaba.fastjson.JSON;
import com.zhiyin.lbs.conf.CoordinateSystem;
import com.zhiyin.lbs.coord.baidu.BaiduCoordConvert;
import com.zhiyin.lbs.coord.gaode.GaodeCoordConvert;
import com.zhiyin.lbs.coord.gps.GpsCoordConvert;
import com.zhiyin.lbs.entity.CoordPoint;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hg on 2016/1/1.
 */
@Slf4j
public class CoordCovert {

    public static String BaiduPri = "API";

    public static CoordPoint convTo(double lon, double lat, int fromCoord, int toCoord) {

        if (fromCoord == toCoord) {
            log.debug(" req coord is equal target coord.");
            return new CoordPoint(lon, lat, fromCoord, toCoord);
        }
        String key = lon + "_" + lat + "_" + fromCoord + "_" + toCoord;
        CoordPoint requestRet = conv(lon, lat, fromCoord, toCoord);
        return requestRet;
    }

    public static CoordPoint conv(double lon, double lat, int fromCoord, int toCoord){

        // 转到百度系
        if(toCoord == 1){
           return BaiduCoordConvert.conv(lon,lat,fromCoord,toCoord);
        }

        if(toCoord == 2){
            return GaodeCoordConvert.conv(lon,lat,fromCoord,toCoord);
        }
        if(toCoord == 3){
            return GpsCoordConvert.conv(lon,lat,fromCoord,toCoord);
        }

        log.error("should not be here.");
        return null;
    }


    public static void main(String[] args){
        System.out.println(JSON.toJSONString(conv(1,1,2,1)));
    }

}
