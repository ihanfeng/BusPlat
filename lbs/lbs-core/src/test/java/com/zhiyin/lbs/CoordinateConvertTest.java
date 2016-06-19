package com.zhiyin.lbs;

import com.alibaba.fastjson.JSON;
import com.zhiyin.lbs.util.CoordinateConvert;
import com.zhiyin.lbs.util.JZLocationConverter;
import org.junit.Test;

/**
 * Created by hg on 2015/12/29.
 */
public class CoordinateConvertTest {

    @Test
    public  void main(){
        double[] ret = CoordinateConvert.wgs2BD09( 39.990650 ,116.360234 );
        CoordinateConvert.gcj2BD09(39.991932,116.366302);
        System.out.println(JSON.toJSONString(ret));


        JZLocationConverter.LatLng ll = new JZLocationConverter.LatLng( 39.990650 , 116.360234 );
        JZLocationConverter.LatLng retLl = JZLocationConverter.wgs84ToBd09(ll);
        System.out.println(JSON.toJSONString(retLl));
    }
}
