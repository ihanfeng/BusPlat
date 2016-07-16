package com.zhiyin.poi;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.util.PoiPublicUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 读取器材录入表
 * Created by hg on 2016/7/8.
 */
@Slf4j
public class ExcelRead {

    public static void main(String[] args) throws Exception {

        Set<String> clas1Set = Sets.newHashSet();
        Map<String,HashSet<String>> clasMap = Maps.newHashMap();

        try {
            ImportParams params = new ImportParams();
//            params.setTitleRows(1);
            long start = new Date().getTime();
            List<ReadEquipEntity> list = ExcelImportUtil.importExcel(
                    new FileInputStream(
                            new File(PoiPublicUtil.getWebRootPath("doc/bb.xls"))),
                    ReadEquipEntity.class, params);
            //        List<StatisticEntity> list = ExcelImportUtil.importExcelBySax(new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx"),
            //            StatisticEntity.class, params);
            for (int i = 0; i < list.size(); i++) {

                ReadEquipEntity tmp = list.get(i);
                log.info(JSON.toJSONString(tmp));
                String clas1 =  tmp.getClas1().trim();
                clas1Set.add( clas1 );
                if( !clasMap.containsKey( clas1 )){
                    HashSet<String> ts = Sets.newHashSet();
                    clasMap.put(clas1, ts);
                }
                clasMap.get(clas1).add(tmp.getClas2());
            }

            for(String clas1 : clas1Set){
                log.info(clas1 +":" + JSON.toJSONString(clasMap.get(clas1)));
            }

            log.info(JSON.toJSONString(list));
            System.out.println(list.size() + "-----" + (new Date().getTime() - start));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
