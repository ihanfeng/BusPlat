package com.zhiyin.poi;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hg on 2016/7/8.
 */
@Getter
@Setter
public class RentEquipExportEntity implements Serializable {

    /**
     *
     */
    private static final long          serialVersionUID = 1L;

    @Excel(name = "项目\nItem")
    private String                     item;

    @Excel(name = "器材类别一")
    private String                     clas;

    @Excel(name = "器材类别二")
    private String                     clas2;

    @Excel(name = "品牌")
    private String                     brand;

    @Excel(name = "器材型号")
    private String                     model;

    @Excel(name = "机身编号\nSerial No.")
    private String                     serialNo;


    @Excel(name = "条形码\nBar Code")
    private String                     barCode;


    @Excel(name = "数量\nQTY")
    private String                     num;


    @Excel(name = "备注\nRemarks")
    private String remarks;
}
