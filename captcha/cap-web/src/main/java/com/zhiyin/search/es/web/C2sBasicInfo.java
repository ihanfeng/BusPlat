package com.zhiyin.search.es.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class C2sBasicInfo {
     String access_token;
     String subjectId; // 用户id
     int subjectType; //用户类型
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    public int getSubjectType() {
        return subjectType;
    }
    public void setSubjectType(int subjectType) {
        this.subjectType = subjectType;
    }
}