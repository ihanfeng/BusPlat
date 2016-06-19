package com.zhiyin.lbs.coord;

import com.alibaba.fastjson.JSON;
import com.zhiyin.lbs.entity.CoordPoint;
import com.zhiyin.lbs.util.CoordinateConvert;
import com.zhiyin.lbs.util.JZLocationConverter;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hg on 2015/12/29.
 */
public class CoordinateConvertTest {

    @Test
    public void test(){
        CoordPoint p1 = CoordCovert.conv( 116.372844, 39.997966, 1, 3);
//        System.out.println(p1);

        CoordPoint p2 = CoordCovert.conv(p1.getLng(), p1.getLat(), 3, 1);
//        CoordPoint p3 = CoordCovert.conv( p2.getLng(),p2.getLat(), 3, 1);
//        System.out.println(p2);


        p1 = CoordCovert.conv( 116.27757299999999, 39.9906905, 2, 3);
        System.out.println(p1);

        p1 = CoordCovert.conv( 116.274593, 39.9851375, 2, 3);
        System.out.println(p1);

        p1 = CoordCovert.conv( 116.2795375 , 39.990717000000004 , 2, 3);
        System.out.println(p1);

        p1 = CoordCovert.conv( 116.2793345 , 39.996700000000004 , 2, 3);
        System.out.println(p1);



    }

    @Test
    public void tes0t() throws IOException {

        List<String> lines = FileUtils.readLines(new File(CoordinateConvertTest.class.getResource("/").getPath() + "testaddr.txt"));
        for(String line : lines){
            String[] info = line.split("\\s+");
            CoordPoint p1 = CoordCovert.conv( Double.valueOf( info[1] ), Double.valueOf(info[2]), 2, 3);
            System.out.println(info[0] + "   " + p1);

        }
    }

    @Test
    public void test3() {
        JZLocationConverter.LatLng p1 = JZLocationConverter.bd09ToWgs84(116.372844, 39.997966);
        JZLocationConverter.LatLng p2 = JZLocationConverter.wgs84ToBd09( p1.getLongitude(), p1.getLatitude() );
        System.out.println(p2.getLongitude()+","+p2.getLatitude());

    }



    @Test
    public void test2() {
        double[] p1 = CoordinateConvert.bd092WGS(39.997966, 116.372844);
        double[] p2 = CoordinateConvert.wgs2BD09(p1[0], p1[1]);
        System.out.println(p2[1]+","+p2[0]);

    }
}
