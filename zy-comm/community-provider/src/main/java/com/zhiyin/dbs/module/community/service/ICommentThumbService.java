package com.zhiyin.dbs.module.community.service;

import com.zhiyin.dbs.module.common.service.IBaseService;
import com.zhiyin.dbs.module.community.entity.CommentThumb;

/**
 * Created by hg on 2016/7/11.
 */
public interface ICommentThumbService extends IBaseService<CommentThumb> {

    //TODO 并发更新有问题，需要采用redis
    Integer updateThumb(Long commentId, Long userId);

    CommentThumb selectByCommentUser(Long commentId, Long userId);
}
