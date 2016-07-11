package org.jeecgframework.poi.test.excel.read;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.test.entity.CourseEntity;
import org.jeecgframework.poi.test.entity.MsgClient;
import org.jeecgframework.poi.test.entity.StudentEntity;
import org.jeecgframework.poi.test.entity.samename.ClassName;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.junit.Test;

/**
 * 同名列导入测试
 * @author JueYue
 *   2015年5月2日 上午11:19:40
 */
public class ExcelImportSameNameTest {

    @Test
    public void exportTest() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId("11231");
        studentEntity.setName("撒旦法司法局");
        studentEntity.setBirthday(new Date());
        studentEntity.setRegistrationDate(new java.sql.Time(new Date().getTime()));
        studentEntity.setSex(1);
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();
        studentList.add(studentEntity);
        studentList.add(studentEntity);

        List<ClassName> list = new ArrayList<ClassName>();
        ClassName classes = new ClassName();
        classes.setName("班级1");
        classes.setArrA(studentList);
        classes.setArrB(studentList);
        list.add(classes);
        classes = new ClassName();
        classes.setName("班级2");
        classes.setArrA(studentList);
        classes.setArrB(studentList);
        list.add(classes);
        ExportParams params = new ExportParams();
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(params, ClassName.class, list);
            FileOutputStream fos = new FileOutputStream(
                PoiPublicUtil.getWebRootPath("import/sameName.xls"));
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void importTest() {
        ImportParams params = new ImportParams();
        params.setHeadRows(2);
        long start = new Date().getTime();
        List<ClassName> list = ExcelImportUtil.importExcel(
            new File(PoiPublicUtil.getWebRootPath("import/sameName.xls")), ClassName.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
        System.out.println(ReflectionToStringBuilder.toString(list.get(0).getArrA().get(0)));

    }

}
