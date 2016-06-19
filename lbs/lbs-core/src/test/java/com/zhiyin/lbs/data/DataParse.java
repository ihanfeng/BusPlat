package com.zhiyin.lbs.data;

import com.google.common.collect.Lists;
import com.zhiyin.lbs.entity.CoordPoint;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hg on 2016/3/5.
 */
public class DataParse {

    public static void main(String[] args) throws IOException {

        String str = FileUtils.readFileToString(new File(DataParse.class.getResource("/").getPath()+"loc.txt"));

//        System.out.println(str);

        String pattern = "(（.*?）)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher( str );
//        System.out.println( m.groupCount() );

        List<CoordPoint> list = Lists.newArrayList();

        while (m.find()){
            String mat = m.group(0);
            mat = mat.substring(1,mat.length()-1);
            String[] arr = mat.split("，");
                System.out.println( arr[1] +","+arr[0] );
            CoordPoint cr = new CoordPoint();
            cr.setLng( Double.valueOf(arr[1]) );
            cr.setLat(Double.valueOf(arr[0]));
            list.add( cr );
        }

        List<Double> lonList = Lists.newArrayList();
        List<Double> latList = Lists.newArrayList();
        for( CoordPoint tmp : list){
            lonList.add(tmp.getLng());
            latList.add(tmp.getLat());
//            System.out.print( "[" + tmp.getLng() + ","+tmp.getLat() + "]," );
        }
        System.out.println(  );

        Collections.sort(lonList);
        Collections.sort(latList);

        System.out.println( lonList );
        System.out.println( latList );

        CoordPoint min = new CoordPoint();
        min.setLng( lonList.get(0) );
        min.setLat( latList.get(0) );
        list.add( min );

        CoordPoint max = new CoordPoint();
        max.setLng( lonList.get( lonList.size()-1 ) );
        max.setLat( latList.get( latList.size()-1 ) );
        list.add( max );

//        String pri
        for( CoordPoint tmp : list){
            System.out.print( "[" + tmp.getLng() + ","+tmp.getLat() + "]," );
        }

        System.out.println();

        System.out.println( "rectangle_x1="+ min.getLng()+",rectangle_y1="+min.getLat() + ",rectangle_x2=" + max.getLng()+",rectangle_y2="+max.getLat());

    }
}
