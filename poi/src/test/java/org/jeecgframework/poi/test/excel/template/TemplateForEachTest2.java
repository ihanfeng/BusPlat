package org.jeecgframework.poi.test.excel.template;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.export.styler.ExcelExportStylerColorImpl;
import org.jeecgframework.poi.test.entity.temp.BudgetAccountsEntity;
import org.jeecgframework.poi.test.entity.temp.PayeeEntity;
import org.jeecgframework.poi.test.entity.temp.TemplateExcelExportEntity;
import org.jeecgframework.poi.util.PoiMergeCellUtil;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TemplateForEachTest2 {

    @Test
    public void test() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
            "org/jeecgframework/poi/test/excel/doc/foreach_many.xlsx");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 400; i++) {
            Map<String, Object> testMap = new HashMap<String, Object>();

            testMap.put("id", "080101" + i);
            testMap.put("name", "大学" + i + "班");
            testMap.put("a1", getDeatil());
            testMap.put("a2", getDeatil());
            testMap.put("a3", getDeatil());
            testMap.put("sum1", "30" + i);
            testMap.put("sum2", "40" + i);
            mapList.add(testMap);
        }
        map.put("list", mapList);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/foreach_many_exprot.xlsx");
        workbook.write(fos);
        fos.close();
    }

    private Map<String, Object> getDeatil() {
        Map<String, Object> testMap = new HashMap<String, Object>();
        testMap.put("zero", (int) (Math.random() * 100));
        testMap.put("sixty", (int) (Math.random() * 100));
        testMap.put("eighty", (int) (Math.random() * 100));
        return testMap;
    }

}
