package com.zhiyin.device.dbs.entity;

import com.zhiyin.dbs.module.common.entity.BaseEntity;

public class DeviceMovInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;



    private Long id;

    private Long deviceId;

    private String locLat;

    private String locLon;

    private String locCoord;

    private Integer delStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocLat() {
        return locLat;
    }

    public void setLocLat(String locLat) {
        this.locLat = locLat == null ? null : locLat.trim();
    }

    public String getLocLon() {
        return locLon;
    }

    public void setLocLon(String locLon) {
        this.locLon = locLon == null ? null : locLon.trim();
    }

    public String getLocCoord() {
        return locCoord;
    }

    public void setLocCoord(String locCoord) {
        this.locCoord = locCoord == null ? null : locCoord.trim();
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

}