package com.zhiyin.lbs.coord.gaode;

import com.zhiyin.lbs.entity.CoordPoint;
import com.zhiyin.lbs.entity.Point;
import com.zhiyin.lbs.util.JZLocationConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从其他坐标系转到高德坐标
 * Created by hg on 2016/1/1.
 */
public class GaodeCoordConvert {
    private static final Logger log = LoggerFactory
            .getLogger(GaodeCoordConvert.class);
    public static CoordPoint convByAPI(double lon,double lat,int fromCoord,int toCoord){

        if(fromCoord == toCoord){
            log.error("fromCoord is equal toCoord,");
            return new CoordPoint(lon,lat,fromCoord,toCoord);
        }

        return null;
    }

    public static CoordPoint conv(double lon,double lat,int fromCoord,int toCoord){

        CoordPoint req = new CoordPoint(lon,lat,fromCoord,toCoord);

        int pri = 1;
        if(pri == 1){
           return  convByCompute(lon,lat,fromCoord,toCoord);
        }

        if(pri == 2){
            CoordPoint apiRet = convByAPI(lon, lat, fromCoord, toCoord);
            if(apiRet == null ){
                log.error("gaode api response is null.");
                return convByCompute(lon, lat, fromCoord, toCoord);
            }
        }
        return null;
    }

    public static CoordPoint convByCompute(double lon,double lat,int fromCoord,int toCoord){


        if(fromCoord == toCoord){
            log.error("fromCoord is equal toCoord,");
            return new CoordPoint(lon,lat,fromCoord,toCoord);
        }
        // 百度 -> 高德
        if(fromCoord == 1){
            JZLocationConverter.LatLng computeRet = JZLocationConverter.bd09ToGcj02(lon,lat);
            CoordPoint ret = new CoordPoint(computeRet.getLongitude(),computeRet.getLatitude(),fromCoord,toCoord);
            return ret;
        }

        // GPS -> 高德
        if(fromCoord == 3){
            JZLocationConverter.LatLng computeRet = JZLocationConverter.wgs84ToGcj02(lon,lat);
            CoordPoint ret = new CoordPoint(computeRet.getLongitude(),computeRet.getLatitude(),fromCoord,toCoord);
            return ret;
        }

        return null;
    }

}
