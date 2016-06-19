package com.zhiyin.lbs.entity;

/**
 * Created by hg on 2016/1/1.
 */
public class CoordPoint {

    private double lng;// 经度
    private double lat;// 纬度
    private int fromCoord;
    private int toCoord;
public   CoordPoint(){

    }
    public CoordPoint(double lng, double lat, int fromCoord, int toCoord) {
        this.lng = lng;
        this.lat = lat;
        this.fromCoord = fromCoord;
        this.toCoord = toCoord;
    }
    public String toString(){
       return this.getLng()+","+this.getLat();
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


}
