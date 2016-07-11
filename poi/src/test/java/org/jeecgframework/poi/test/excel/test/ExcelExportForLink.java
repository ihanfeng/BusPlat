package org.jeecgframework.poi.test.excel.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.jeecgframework.poi.handler.inter.IExcelDataHandler;
import org.jeecgframework.poi.test.entity.HyperLinkEntity;
import org.jeecgframework.poi.test.entity.MsgClient;
import org.jeecgframework.poi.test.entity.MsgClientGroup;
import org.junit.Test;

/**
 * 超链接测试
 * @author JueYue
 *   2015年7月20日 下午10:34:18
 */
public class ExcelExportForLink {

    @Test
    public void test() throws Exception {

        List<HyperLinkEntity> list = new ArrayList<HyperLinkEntity>();
        HyperLinkEntity client = new HyperLinkEntity();
        client.setName("百度");
        client.setUrl("https://www.baidu.com/");
        list.add(client);
        client = new HyperLinkEntity();
        client.setName("新浪");
        client.setUrl("http://www.sina.com.cn/");
        list.add(client);

        Date start = new Date();
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setDataHanlder(new ExcelDataHandlerDefaultImpl() {

            @Override
            public Hyperlink getHyperlink(CreationHelper creationHelper, Object obj, String name, Object value) {
                Hyperlink link = creationHelper.createHyperlink(Hyperlink.LINK_URL);
                HyperLinkEntity e = (HyperLinkEntity) obj;
                link.setAddress(e.getUrl());
                link.setLabel(e.getName());
                return link;
            }

        });
        Workbook workbook = ExcelExportUtil.exportExcel(params, HyperLinkEntity.class, list);
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/tt.xlsx");
        workbook.write(fos);
        fos.close();
    }

}
