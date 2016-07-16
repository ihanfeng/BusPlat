package com.zhiyin.poi;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelStyleType;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 器材租赁清单打印
 * Created by hg on 2016/7/8.
 */
public class RentListProcessor {

    public static void main(String[] args) throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "com/zhiyin/poi/doc/equip/aa.xlsx", true);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("userName", "admin");

        List<RentEquipExportEntity> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            RentEquipExportEntity t = new RentEquipExportEntity();
            t.setItem(i+1+"");
            t.setClas(i+"");
            t.setClas2(i+"");
            t.setRemarks("remaks");

            list.add(t);
        }
        map.put("entitylist", list);


        Workbook book = ExcelExportUtil.
                exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/aa.xlsx");
        book.write(fos);
        fos.close();

    }



}
