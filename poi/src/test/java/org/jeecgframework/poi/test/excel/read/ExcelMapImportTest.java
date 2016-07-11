package org.jeecgframework.poi.test.excel.read;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.junit.Test;

/**
 * Map导入测试
 * @author JueYue
 *   2015年6月21日 下午10:56:03
 */
public class ExcelMapImportTest {

    @Test
    public void mapTest() {

        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setDataHanlder(new MapImportHanlder());
        long start = new Date().getTime();
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(
            new File(PoiPublicUtil.getWebRootPath("import/check.xls")), Map.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(list.get(0));

    }

}
