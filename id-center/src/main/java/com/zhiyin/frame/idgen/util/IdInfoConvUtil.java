package com.zhiyin.frame.idgen.util;

import com.alibaba.fastjson.JSON;
import com.zhiyin.frame.idgen.IdInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/4/28.
 */
@Slf4j
public class IdInfoConvUtil {

    public static IdInfo getInfo(Long l){
        IdInfo idInfo = new IdInfo();

        String bin = Long.toBinaryString(l);
        idInfo.setRealLen(bin.length());

        // 生成的id不一定够64位，高位为0
        int diffLen = 64 - bin.length();
        String newStr = bin;
        for(int i=0; i < diffLen ; i++){
            newStr = "0" + newStr;
        }
        bin = newStr;

        idInfo.setTimestamp( bin.substring(0,41) );
        idInfo.setDatacenter(  bin.substring(42,52) );
        idInfo.setSequence( bin.substring(52) );
        idInfo.setFinalLen( bin.length());

        return idInfo;

    }

    public static void printInfo(Long l){

        IdInfo idInfo = getInfo(l);
        log.debug(JSON.toJSONString(idInfo));
        log.debug("idinfo length:" + " " + idInfo.getTimestamp().length() + " " + idInfo.getDatacenter().length() + " " + idInfo.getSequence().length() );
        log.debug( "idinfo val:" + l + " "+ idInfo.getTimestamp() + " " + idInfo.getDatacenter() + " "+idInfo.getSequence() );

    }
}
