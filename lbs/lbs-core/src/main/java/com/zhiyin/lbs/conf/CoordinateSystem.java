package com.zhiyin.lbs.conf;

/**
 * Created by hg on 2016/1/1.
 */

/**
 * 坐标系枚举
 * 坐标的类型，目前支持的坐标类型包括：bd09ll（百度经纬度坐标）、gcj02ll（国测局经纬度坐标）、wgs84ll（ GPS经纬度）
 * 除了百度的是bd09,其他的都是gcj02
 * @author JueYue
 * @date 2015年1月26日
 */
public enum CoordinateSystem {

    BD09 (1,"bd09ll" , "百度经纬度坐标") , GCJ02 (2,"gcj02ll" , "国测局经纬度坐标") , WGS84 (3,"wgs84ll" , "GPS经纬度");

    private Integer type;
    private String value;
    private String name;

    CoordinateSystem(Integer type,String value, String name) {
        this.type = type;

        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public static void main(String[] args){
        System.out.println(WGS84.getType());
    }

}
