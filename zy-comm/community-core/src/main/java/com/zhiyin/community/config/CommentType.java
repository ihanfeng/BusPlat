package com.zhiyin.community.config;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/9/27.
 */
@Slf4j
public enum CommentType {

    ReplyTopic(1), ReplyComment(2) ;

    private int code;

    private CommentType(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static void main(String[] args) {
        log.info(ReplyComment.getCode()+"");
    }
}
