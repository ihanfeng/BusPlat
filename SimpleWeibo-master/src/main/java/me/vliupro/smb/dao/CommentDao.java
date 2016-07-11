package me.vliupro.smb.dao;

import me.vliupro.smb.po.Comment;

import java.util.List;

/**
 * Created by vliupro on 16-5-23.
 */
public interface CommentDao {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    boolean addComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    boolean deleteComment(int commentId);

    /**
     * 获得微博下所有评论
     * @param weiboId
     * @return
     */
    List<Comment> getCommentsByWeiboId(int weiboId);

    /**
     * 获得微博下评论数量
     * @param weiboId
     * @return
     */
    int getNumOfWeibo(int weiboId);
}
