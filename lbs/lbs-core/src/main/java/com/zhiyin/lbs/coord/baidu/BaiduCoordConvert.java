package com.zhiyin.lbs.coord.baidu;

import com.zhiyin.lbs.conf.CoordinateSystem;
import com.zhiyin.lbs.coord.CoordCovert;
import com.zhiyin.lbs.entity.CoordPoint;
import com.zhiyin.lbs.util.JZLocationConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从其他坐标系转到百度坐标系
 * Created by hg on 2016/1/1.
 */
public class BaiduCoordConvert {
    private static final Logger log = LoggerFactory
            .getLogger(BaiduCoordConvert.class);
//    1：GPS设备获取的角度坐标，wgs84坐标;
//    2：GPS获取的米制坐标、sogou地图所用坐标;
//    3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局坐标;
//    4：3中列表地图坐标对应的米制坐标;
//    5：百度地图采用的经纬度坐标;
//    6：百度地图采用的米制坐标;
//    7：mapbar地图坐标;
//    8：51地图坐标


//    有两种可供选择：5、6。
//            5：bd09ll(百度经纬度坐标),
//    6：bd09mc(百度米制经纬度坐标);



    public static CoordPoint conv(double lon,double lat,int fromCoord,int toCoord){


        if(!CoordCovert.BaiduPri.equals("API")){
            return convByCompute(lon,lat,fromCoord,toCoord);
        }

        if(CoordCovert.BaiduPri.equals("API")){
            CoordPoint apiRet = convByAPI(lon, lat, fromCoord, toCoord);
            if(apiRet == null ){
                log.error("baidu api response is null.");
                return convByCompute(lon, lat, fromCoord, toCoord);
            }else{
                return apiRet;
            }
        }
        return null;
    }


    public static CoordPoint convByAPI(double lon,double lat,int fromCoord,int toCoord){

        if(fromCoord == toCoord){
            log.error("fromCoord is equal toCoord,");
            return new CoordPoint(lon,lat,fromCoord,toCoord);
        }

        int baiduFrom = 1;

        if(fromCoord == 2){
            baiduFrom = 3;
        }
        if(fromCoord == 1){
            baiduFrom = 1;
        }

        int baiduTo = 5;

        String ak = "";

        String url = "http://api.map.baidu.com/geoconv/v1/?coords="+lon+","+lat+"&from="+baiduFrom+"&to="+baiduTo+"&ak="+ak;
        log.info("request url:{}",url);

        return null;
    }


    public static CoordPoint convByCompute(double lon,double lat,int fromCoord,int toCoord){

        if(fromCoord == toCoord){
            log.error("fromCoord is equal toCoord,");
            return new CoordPoint(lon,lat,fromCoord,toCoord);
        }

        // from gps --> to baidu
        if(fromCoord == CoordinateSystem.WGS84.getType()){
            JZLocationConverter.LatLng computeRet = JZLocationConverter.wgs84ToBd09(lon,lat);
            CoordPoint ret = new CoordPoint(computeRet.getLongitude(),computeRet.getLatitude(),fromCoord,toCoord);
            return ret;
        }

        JZLocationConverter.LatLng computeRet = JZLocationConverter.gcj02ToBd09(lon,lat);
        CoordPoint ret = new CoordPoint(computeRet.getLongitude(),computeRet.getLatitude(),fromCoord,toCoord);
        return ret;
    }


}
