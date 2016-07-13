package com.zhiyin.poi;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by wangqinghui on 2016/7/8.
 */
@Getter
@Setter
public class ReadEquipEntity implements java.io.Serializable {

    @Excel(name = "资产编号")
    private String id;

    @Excel(name = "资产分类")
    private String clas;

    @Excel(name = "资产名称")
    private String name;

    @Excel(name = "制单时间")
    private String orderTime;


    @Excel(name = "取得日期")
    private String getTime;

    @Excel(name = "资产分类")
    private String clas1;
}
