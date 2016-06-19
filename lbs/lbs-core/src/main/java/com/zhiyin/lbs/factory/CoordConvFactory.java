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
    public static CoordPoint convTo(Double lon, Double lat, Integer fromCoord){
        if(lon == null || lat == null || fromCoord==null){
            return  new CoordPoint();
        }
        return  convTo( lon,  lat,  fromCoord, CoordinateSystem.WGS84.getType());
    }

    /**
     * 从其他坐标系转目标坐标系
     */
    // TODO add cache
    public static CoordPoint convTo(double lon, double lat, int fromCoord, int toCoord){

        if(fromCoord <=0){
            log.error("fromCoord {} not valid , set default value.",fromCoord);
            fromCoord = CoordinateSystem.WGS84.getType();
        }

        if(fromCoord == toCoord){
            log.debug(" req coord is equal target coord.");
            return new CoordPoint(lon,  lat,  fromCoord,toCoord);
        }

        String key = lon+"_"+lat+"_"+fromCoord+"_"+toCoord;
        CoordPoint requestRet = CoordCovert.conv(lon, lat, fromCoord, toCoord);
        return requestRet;
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
