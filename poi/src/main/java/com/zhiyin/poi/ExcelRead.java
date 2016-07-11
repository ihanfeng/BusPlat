package com.zhiyin.poi;

import com.alibaba.fastjson.JSON;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.util.PoiPublicUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * Created by wangqinghui on 2016/7/8.
 */
public class ExcelRead {

    public static void main(String[] args) throws Exception {
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            long start = new Date().getTime();
            List<ReadEquipEntity> list = ExcelImportUtil.importExcel(
                    new FileInputStream(
                            new File(PoiPublicUtil.getWebRootPath("bb.xls"))),
                    ReadEquipEntity.class, params);
            //        List<StatisticEntity> list = ExcelImportUtil.importExcelBySax(new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx"),
            //            StatisticEntity.class, params);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(JSON.toJSONString( list.get(i) ));
            }
            System.out.println(list.size() + "-----" + (new Date().getTime() - start));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
