package org.jeecgframework.poi.test.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.handler.inter.IExcelModel;

/**
 * Excel导入校验类
 * @author JueYue
 *   2015年2月24日 下午2:21:07
 */
public class ExcelVerifyEntityOfMode extends ExcelVerifyEntity implements IExcelModel {

    private String errorMsg;

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
