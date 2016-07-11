package me.vliupro.smb.service;

import me.vliupro.smb.dao.CommentDao;
import me.vliupro.smb.dao.CommentDaoImpl;
import me.vliupro.smb.po.Comment;
import me.vliupro.smb.utils.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vliupro on 16-6-6.
 */
public class CommentServiceImpl implements CommentService {

    private CommentDao cd = new CommentDaoImpl();

    @Override
    public boolean pubComment(Comment comment) {
        return cd.addComment(comment);
    }

    @Override
    public boolean delComment(int commentId) {
        return cd.deleteComment(commentId);
    }

    @Override
    public Page<Comment> getCommentsByWeibo(int weiboId, int pageNum, int total) {
        Page<Comment> page = new Page<>();
        page.setCurrentPage(pageNum);
        page.setEveryPage(total);
        page.setTotalCount(cd.getNumOfWeibo(weiboId));
        page.setTotalPage(page.getTotalCount() % total == 0 ?
                page.getTotalCount() / total : page.getTotalCount() / total + 1);
        page.setHasNextPage(page.getCurrentPage() < page.getTotalPage());
        page.setHasPrePage(page.getCurrentPage() > 1);
        List<Comment> comments = cd.getCommentsByWeiboId(weiboId);
        if (comments != null) {
            page.setItems(comments);
        } else {
            page.setItems(new ArrayList<>());
        }
        return page;
    }

    @Override
    public int commentNumOfWeibo(int weiboId) {
        return cd.getNumOfWeibo(weiboId);
    }
}
