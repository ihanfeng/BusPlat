package com.zhiyin.community.api.client;

/**
 * Created by hg on 2016/8/5.
 */
public class CommunityClientApiInfo {

    public static final String WebProj = "zhiyin-community" ;
    public static final String ScanPack2 = CommunityClientApiInfo.class.getPackage().getName();

    public static final String ScanPack = "com.zhiyin.community.api.client";

    public static void main(String[] args){
        System.out.println( ScanPack );
    }
}
