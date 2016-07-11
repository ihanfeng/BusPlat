/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jeecgframework.poi.test.excel.read.check;

import java.io.File;
import java.util.List;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.test.entity.CourseEntity;
import org.jeecgframework.poi.test.entity.CourseEntityNoAnn;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.junit.Assert;
import org.junit.Test;
/**
 * 输入模板校验
 * 双行版本
 * @author JueYue
 *   2015年11月13日 下午8:40:05
 */
public class ImportCheckTestTwoLine {

    @Test
    public void testTwoLine() {
        boolean isOK = true;
        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(2);
            List<CourseEntity> list = ExcelImportUtil.importExcel(
                new File(PoiPublicUtil.getWebRootPath("import/checkTwo.xls")),
                CourseEntity.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            isOK = false;
        }
        Assert.assertTrue(isOK);
    }

    @Test
    public void testTwoLineError() {
        boolean isOK = true;
        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(2);
            params.setStartSheetIndex(1);
            List<CourseEntity> list = ExcelImportUtil.importExcel(
                new File(PoiPublicUtil.getWebRootPath("import/checkTwo.xls")),
                CourseEntity.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            isOK = false;
        }
        Assert.assertTrue(!isOK);
    }
    
    @Test
    public void testTwoLineByParams() {
        boolean isOK = true;
        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(2);
            params.setImportFields(new String[]{"课程名称","老师姓名","学生_学生姓名","学生_学生性别","学生_出生日期"});
            List<CourseEntityNoAnn> list = ExcelImportUtil.importExcel(
                new File(PoiPublicUtil.getWebRootPath("import/checkTwo.xls")),
                CourseEntityNoAnn.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            isOK = false;
        }
        Assert.assertTrue(isOK);
    }
    
    @Test
    public void testTwoLineErrorByParams() {
        boolean isOK = true;
        try {
            ImportParams params = new ImportParams();
            params.setHeadRows(2);
            params.setStartSheetIndex(1);
            params.setImportFields(new String[]{"课程名称","老师姓名","学生_学生姓名","学生_学生性别","学生_出生日期","学生_进校日期"});
            List<CourseEntityNoAnn> list = ExcelImportUtil.importExcel(
                new File(PoiPublicUtil.getWebRootPath("import/checkTwo.xls")),
                CourseEntityNoAnn.class, params);
        } catch (Exception e) {
            e.printStackTrace();
            isOK = false;
        }
        Assert.assertTrue(!isOK);
    }

}
