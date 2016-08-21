package com.zhiyin.ese.api.client;

/**
 * Created by hg on 2016/8/5.
 */
public class SearcherClientApiInfo {

    public static final String WebProj = "zhiyin-searcher" ;
    public static final String ScanPack2 = SearcherClientApiInfo.class.getPackage().getName();

    public static final String ScanPack = "com.zhiyin.ese.api.client";


    public static void main(String[] args){
        System.out.println( ScanPack );
    }
}
