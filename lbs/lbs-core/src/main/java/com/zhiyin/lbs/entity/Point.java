package com.zhiyin.lbs.entity;

/**
 * Created by hg on 2015/12/29.
 */
/**
 * 用于构造地图中的坐标点
 * @author lw
 * **/
public class Point {

    private double lat;// 纬度
    private double lng;// 经度

    private int fromCoord;
    private int toCoord;

    public Point() {
    }

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point bmapPoint = (Point) obj;
            return (bmapPoint.getLng() == lng && bmapPoint.getLat() == lat) ? true : false;
        } else {
            return false;
        }
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getFromCoord() {
        return fromCoord;
    }

    public void setFromCoord(int fromCoord) {
        this.fromCoord = fromCoord;
    }

    public int getToCoord() {
        return toCoord;
    }

    public void setToCoord(int toCoord) {
        this.toCoord = toCoord;
    }

    @Override
    public String toString() {
        return "Point [lat=" + lat + ", lng=" + lng + "]";
    }

}