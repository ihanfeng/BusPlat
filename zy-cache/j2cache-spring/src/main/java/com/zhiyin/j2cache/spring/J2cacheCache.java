package com.zhiyin.j2cache.spring;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * 功能说明：自定义spring的cache的实现，参考cache包实现
 *
 */
public class J2cacheCache implements Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(J2cacheCache.class);

    /**
     * 缓存的别名
     */
    private String name;
    /**
     * memcached客户端
     */
    private J2cacheClient client = new J2cacheClient();
    /**
     * 缓存过期时间，默认是1小时
     * 自定义的属性
     */
    private int exp = 3600;
    /**
     * 是否对key进行base64加密
     */
    private boolean base64Key = false;
    /**
     * 前缀名
     */
    private String prefix;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return this.client;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object object = null;
        object = client.get( name, genKey(key) );
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object object = this.client.get(name, genKey(key) );
        return (T) object;
    }

    @Override
    public void put(Object key, Object value) {
        if(key == null){
            return;
        }
        client.set(name, genKey(key) ,value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        this.put(key, value);
        return this.get(key);
    }

    @Override
    public void evict(Object key) {
        client.evict(name, genKey(key));
    }

    @Override
    public void clear() {

        client.clear(name);
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setBase64Key(boolean base64Key) {
        this.base64Key = base64Key;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 处理key
     * @param key
     * @return
     */
    private String handleKey(final String key) {
//        if (base64Key) {
//            return Joiner.on(EMPTY_SEPARATOR).skipNulls().join(this.prefix, DyFunctions.base64Encode(key));
//        }

        return Joiner.on(EMPTY_SEPARATOR).skipNulls().join(this.prefix, key);
    }

    /**
     * 转换key，去掉空格
     * @param object
     * @return
     */
    private String objectToString(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof String) {
            return ((String) object).replace(""," ");
        } else {
            return object.toString();
        }
    }

    public String genKey(Object object){
        return handleKey(objectToString(object));
    }

    private static final String EMPTY_SEPARATOR = "";

}