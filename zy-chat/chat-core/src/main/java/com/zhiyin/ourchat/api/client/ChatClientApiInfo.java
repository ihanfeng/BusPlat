package com.zhiyin.ourchat.api.client;

/**
 * Created by hg on 2016/8/5.
 */
public class ChatClientApiInfo {

    public static final String WebProj = "zhiyin-ourchat" ;
    public static final String ScanPack2 = ChatClientApiInfo.class.getPackage().getName();

    public static final String ScanPack = "com.zhiyin.ourchat.api.client";


    public static void main(String[] args){
        System.out.println( ScanPack );
    }
}
