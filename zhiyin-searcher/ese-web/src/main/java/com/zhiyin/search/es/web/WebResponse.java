package com.zhiyin.search.es.web;


public class WebResponse<T> {

    private Meta meta;

    private T data;

    public WebResponse() {

    }

    public WebResponse(Meta meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public WebResponse(Integer code, String message) {
        this.meta = new Meta(code, message);
        this.data = (T) new Object();
    }

    public WebResponse(Integer code, String message, T data) {
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
