package com.zhiyin.search.es.web;


public class WebResp<T> {

    private Meta meta;

    private T data;

    public WebResp() {

    }

    public WebResp(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public WebResp(Integer code, String message) {
        this.meta = new Meta(code, message);
        this.data = (T) new Object();
    }

    public WebResp(Integer code, String message, T data) {
        this.meta = new Meta(code, message);
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
