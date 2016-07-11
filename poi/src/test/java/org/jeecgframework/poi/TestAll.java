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
package org.jeecgframework.poi;

import static org.junit.Assert.*;

import org.jeecgframework.poi.cache.manager.POICacheManagerTest;
import org.jeecgframework.poi.html.ExcelToHtmlUtilTest;
import org.jeecgframework.poi.test.UtilTest;
import org.jeecgframework.poi.test.excel.read.CustomerEntityTest;
import org.jeecgframework.poi.test.excel.read.ExcelImportSameNameTest;
import org.jeecgframework.poi.test.excel.read.ExcelImportUtilTest;
import org.jeecgframework.poi.test.excel.read.ExcelMapImportTest;
import org.jeecgframework.poi.test.excel.read.ExcelVerifyTest;
import org.jeecgframework.poi.test.excel.read.check.ImportCheckTestOneLine;
import org.jeecgframework.poi.test.excel.read.check.ImportCheckTestTwoLine;
import org.jeecgframework.poi.test.excel.template.ExcelExportTemplateTest;
import org.jeecgframework.poi.test.excel.template.TemplateExcelExportTest;
import org.jeecgframework.poi.test.excel.template.TemplateExcelManySheet;
import org.jeecgframework.poi.test.excel.template.TemplateExcelSheetTest;
import org.jeecgframework.poi.test.excel.template.TemplateForEachTest;
import org.jeecgframework.poi.test.excel.template.TemplateForEachTest2;
import org.jeecgframework.poi.test.excel.test.ExcelExportForLink;
import org.jeecgframework.poi.test.excel.test.ExcelExportForMap;
import org.jeecgframework.poi.test.excel.test.ExcelExportMsgClient;
import org.jeecgframework.poi.test.excel.test.ExcelExportStatisticTest;
import org.jeecgframework.poi.test.excel.test.ExcelExportUtilDataHandlerTest;
import org.jeecgframework.poi.test.excel.test.ExcelExportUtilIdTest;
import org.jeecgframework.poi.test.excel.test.ExcelExportUtilTest;
import org.jeecgframework.poi.test.pdf.MultiLineHeadersTest;
import org.jeecgframework.poi.test.pdf.PdfExportUtilTest;
import org.jeecgframework.poi.test.pdf.PdfImageTest;
import org.jeecgframework.poi.test.word.ContractDemoTest;
import org.jeecgframework.poi.test.word.TaxManagementTest;
import org.jeecgframework.poi.test.word.WordExportUtilAnnExcelTest;
import org.jeecgframework.poi.test.word.WordExportUtilBaseExcelTest;
import org.jeecgframework.poi.test.word.WordExportUtilTest;
import org.jeecgframework.poi.util.PoiElUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PoiElUtilTest.class, WordExportUtilTest.class, UtilTest.class,
                WordExportUtilBaseExcelTest.class, WordExportUtilAnnExcelTest.class,
                ExcelExportForLink.class, ExcelExportUtilIdTest.class,
                ExcelExportUtilDataHandlerTest.class, ExcelExportStatisticTest.class,
                ExcelExportMsgClient.class, ExcelExportForMap.class, ExcelExportForLink.class,
                TemplateForEachTest2.class, TemplateForEachTest.class, TemplateExcelSheetTest.class,
                TemplateExcelManySheet.class, TemplateExcelExportTest.class,
                ExcelExportTemplateTest.class, ExcelToHtmlUtilTest.class, ExcelVerifyTest.class,
                ExcelMapImportTest.class, ExcelImportUtilTest.class, ExcelImportSameNameTest.class,
                CustomerEntityTest.class, ImportCheckTestOneLine.class, TaxManagementTest.class,
                ContractDemoTest.class, MultiLineHeadersTest.class, PdfExportUtilTest.class,
                PdfImageTest.class, ExcelExportUtilTest.class, TaxManagementTest.class,
                POICacheManagerTest.class,ImportCheckTestTwoLine.class })
public class TestAll {

}
