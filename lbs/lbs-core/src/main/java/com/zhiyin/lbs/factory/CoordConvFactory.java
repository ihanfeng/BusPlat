package com.zhiyin.lbs.factory;

import com.alibaba.fastjson.JSON;
import com.zhiyin.lbs.conf.CoordinateSystem;
import com.zhiyin.lbs.coord.CoordCovert;
import com.zhiyin.lbs.entity.CoordPoint;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/1/1.
 */
@Slf4j
public class CoordConvFactory {

    /**
     * 默认转到GPS
     */
    public static CoordPoint convTo(Double lon, Double lat, Integer fromCoord ){
        return convTo(lon,lat,fromCoord,CoordinateSystem.WGS84.getType());
    }

    public static CoordPoint convToDefault(Double lon, Double lat, Integer fromCoord ) {
       return convToDefault(lon,lat,fromCoord,CoordinateSystem.WGS84.getType());
    }
        /**
         * 带默认值转化
         */
    public static CoordPoint convToDefault(Double lon, Double lat, Integer fromCoord,Integer toCoord ){
        if( lon == null ){
            lon = 0D;
        }
        if( lat == null ){
            lat = 0D;
        }
        if( fromCoord == null ){
            fromCoord = CoordinateSystem.WGS84.getType();
        }
        if( toCoord == null ){
            toCoord = CoordinateSystem.WGS84.getType();
        }
        return convTo(lon,lat,fromCoord,toCoord);
    }

    public static CoordPoint convTo(Double lon, Double lat, Integer fromCoord,Integer toCoord){
        if(lon == null || lat == null || fromCoord==null || toCoord==null){
            log.error("coord conv error. {} {} {} {}",lon,lat,fromCoord,toCoord);
            throw new RuntimeException("coord conv error.");
        }

        if (fromCoord <= 0 || toCoord<=0) {
            log.error("fromCoord {} not valid  toCoord {}", fromCoord,toCoord);
            throw new RuntimeException("coord conv error.");
        }

        return  CoordCovert.convTo( lon,  lat,  fromCoord, toCoord);
    }


    /**
     * 判断是否是GPS坐标系
     */
    public static boolean isWgs84(Integer fromCoord){
        if(fromCoord == null){
            return false;
        }
        if(fromCoord != CoordinateSystem.WGS84.getType()){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(isWgs84(1));
        System.out.println(isWgs84(3));
        System.out.println(JSON.toJSONString(convTo( 116.35415476015766,39.98910141343689	,1,3)));

//        116.359774	39.992587	116.372566	39.999441
        CoordPoint cord = convTo(116.34712843050139,39.985070958403305 , 3, 1);
        
        System.out.println( cord.getLng()+","+cord.getLat());


    }
}
