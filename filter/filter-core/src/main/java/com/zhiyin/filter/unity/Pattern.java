package com.zhiyin.filter.unity;

public class Pattern {

    private int position;
    private String url;

    public Pattern(String url) {
        this.position = url.startsWith("*") ? 1
                : url.endsWith("*") ? -1
                : 0;
        this.url = url.replaceAll("/?\\*", "");
    }

    public boolean matches(String path) {
        return (position == -1) ? path.startsWith(url)
                : (position == 1) ? path.endsWith(url)
                : path.equals(url);
    }

}