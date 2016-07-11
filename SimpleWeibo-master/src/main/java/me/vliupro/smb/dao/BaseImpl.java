package me.vliupro.smb.dao;

import java.util.Map;

/**
 * Created by vliupro on 16-5-23.
 */
public class BaseImpl {

    DBUtil db = null;

    public BaseImpl(){
        db = new DBUtil();
    }

    /**
     * 由map生成对象
     * @param map
     * @return
     */
    protected Object generate(Map<String, Object> map){
        return null;
    }

}
