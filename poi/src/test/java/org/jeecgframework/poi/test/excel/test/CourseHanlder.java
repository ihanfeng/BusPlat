package org.jeecgframework.poi.test.excel.test;

import org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.jeecgframework.poi.test.entity.CourseEntity;

public class CourseHanlder extends ExcelDataHandlerDefaultImpl<CourseEntity> {

    @Override
    public Object exportHandler(CourseEntity obj, String name, Object value) {
        if (name.equals("课程名称")) {
            return String.valueOf(value) + "课程";
        }
        return super.exportHandler(obj, name, value);
    }

    @Override
    public Object importHandler(CourseEntity obj, String name, Object value) {
        return super.importHandler(obj, name, value);
    }

}
