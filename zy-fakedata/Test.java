package com.zhiyin.rpc.shi.demo;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@Slf4j
public class Test {

    public static void main(String[] args) {

        double diff = 0.001;

        double x1 = 1;
        double x2 = 22;
        double y1 = 1;
        double y2 = 21;

//        x1 = x1 + diff;
//        x2 = x2 - diff;
//        y1 = y1 + diff;
//        y2 = y2 - diff;

        int qu = 4;

         double recWidth = x2 -x1 ;
        double recLength = y2 -y1;

        int lenSize = (int)recLength / qu + 1;

        int widSize = (int)recWidth / qu + 1;

        List<Double> xList = Lists.newArrayList();
        List<Double> yList = Lists.newArrayList();

        for(int i=0 ; i < lenSize ;i++){
            yList.add( y1 + qu * i);
        }
        yList.add(y2);
        for(int i=0 ; i < lenSize ;i++){
            xList.add( x1 + qu * i);
        }
        xList.add(x2);

        for(int i=0; i< xList.size() ;i++){
            for(int j=0; j < yList.size() ; j++){
                System.out.print(xList.get(i)+"," + yList.get(j) + "   ");
            }
            System.out.println();
        }

    }
}
