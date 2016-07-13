package com.zhiyin.poi;

import lombok.Getter;
import lombok.Setter;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Created by hg on 2016/7/8.
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

    @Excel(name = "资产类别一级")
    private String clas1;
    @Excel(name = "资产类别二级")
    private String clas2;

//    @Excel(name = "品牌")
//    private String clas1;
    @Excel(name = "型号")
    private String model;




//    资产编号	资产分类	资产名称	制单时间	取得日期	记账日期	规格型号	价值	使用部门	取得方式	使用人	存放地点	库存条码	设备机身编号		资产类别二级		附属配件	存放坐标	资金来源（或购置项目名称）	租用价格	产权归属	器材产地	现版本号	升级情况	维修记录	备注1	备注2(隐藏式备注)

}
