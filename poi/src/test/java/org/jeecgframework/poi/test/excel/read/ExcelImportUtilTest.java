package org.jeecgframework.poi.test.excel.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.jeecgframework.poi.QiyeInfo;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.test.entity.CourseEntity;
import org.jeecgframework.poi.test.entity.MsgClient;
import org.jeecgframework.poi.test.entity.statistics.StatisticEntity;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.junit.Test;

public class ExcelImportUtilTest {

    ///ExcelExportMsgClient 测试是这个到处的数据

    @Test
    public void test() {
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            long start = new Date().getTime();
            List<StatisticEntity> list = ExcelImportUtil.importExcelBySax(
                new FileInputStream(
                    new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx"))),
                StatisticEntity.class, params);
            //        List<StatisticEntity> list = ExcelImportUtil.importExcelBySax(new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx"),
            //            StatisticEntity.class, params);
            /*for (int i = 0; i < list.size(); i++) {
                System.out.println(ReflectionToStringBuilder.toString(list.get(i)));
            }*/
            System.out.println(list.size() + "-----" + (new Date().getTime() - start));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    public void asdasda() {
    	try {
    		ImportParams params = new ImportParams();
    		long start = new Date().getTime();
    		List<QiyeInfo> list = ExcelImportUtil.importExcel(new File("D:/excel/企业信息模板.xlsx"), QiyeInfo.class, params);
    		//        List<StatisticEntity> list = ExcelImportUtil.importExcelBySax(new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx"),
    		//            StatisticEntity.class, params);
    		/*for (int i = 0; i < list.size(); i++) {
                System.out.println(ReflectionToStringBuilder.toString(list.get(i)));
            }*/
    		System.out.println(list.size() + "-----" + (new Date().getTime() - start));
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @Test
    public void test2() {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();
        List<MsgClient> list = ExcelImportUtil.importExcel(
            new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx")),
            MsgClient.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
    }

    @Test
    public void testMapImport() {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(
            new File(PoiPublicUtil.getWebRootPath("import/ExcelExportMsgClient.xlsx")), Map.class,
            params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(list.get(0));
    }
}
