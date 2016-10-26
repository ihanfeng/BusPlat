package com.zhiyin.community.config;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/9/27.
 */
@Slf4j
public enum CommentType {

    ReplyTopic(1,"回复话题"), ReplyComment(2,"回复评论") ;

    private int code;
    private String remark;

    private CommentType(int code,String remark ){
        this.code = code;
        this.remark= remark;
    }

    public int getCode(){
        return code;
    }

    public static void main(String[] args) {
        log.info(ReplyComment.getCode()+"");
    }
}
