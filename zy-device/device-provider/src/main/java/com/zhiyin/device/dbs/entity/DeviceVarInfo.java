package com.zhiyin.device.dbs.entity;

import com.zhiyin.dbs.module.common.entity.BaseEntity;

public class DeviceVarInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String systemName;

    private String systemVersion;

    private String appName;

    private String appVersion;

    private String appBuildVersion;

    private Integer delStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getAppBuildVersion() {
        return appBuildVersion;
    }

    public void setAppBuildVersion(String appBuildVersion) {
        this.appBuildVersion = appBuildVersion == null ? null : appBuildVersion.trim();
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
}
