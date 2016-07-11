package me.vliupro.smb.service;

import me.vliupro.smb.po.Comment;
import me.vliupro.smb.utils.Page;

/**
 * Created by vliupro on 16-6-6.
 */
public interface CommentService {

    /**
     * 发表评论
     * @param comment
     * @return
     */
    boolean pubComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    boolean delComment(int commentId);

    /**
     * 根据微博Id分页查找评论
     * @param weiboId
     * @param begin
     * @param total
     * @return
     */
    Page<Comment> getCommentsByWeibo(int weiboId, int pageNum, int total);

    /**
     * 获得微博下评论数目
     * @param weiboId
     * @return
     */
    int commentNumOfWeibo(int weiboId);
}
