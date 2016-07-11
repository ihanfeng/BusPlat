package me.vliupro.smb.dao;

import me.vliupro.smb.po.Comment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vliupro on 16-5-23.
 */
public class CommentDaoImpl extends BaseImpl implements CommentDao {

    public boolean addComment(Comment comment) {
        String sql = "insert into db_comment (c_content, user_id, weibo_id, c_ctime) values(?,?,?,?)";
        int count = this.db.update(sql, comment.getcContent(), comment.getUserId(),
                comment.getWeiboId(), comment.getcCtime());
        return count > 0;
    }

    public boolean deleteComment(int commentId) {
        String sql = "delete from db_comment where id=?";
        int count = this.db.update(sql, commentId);
        return count > 0;
    }

    public List<Comment> getCommentsByWeiboId(int weiboId) {
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "select * from db_comment where weibo_id=? order by c_ctime desc";
        List<Map<String, Object>> commentMaps = this.db.queryList(sql, weiboId);
        for (Map<String, Object> commentMap : commentMaps) {
            comments.add((Comment) this.generate(commentMap));
        }
        return comments;
    }

    @Override
    public int getNumOfWeibo(int weiboId) {
        String sql = "select count(*) as num from db_comment where weibo_id=?";
        Map<String, Object> map = db.query(sql, weiboId);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    protected Object generate(Map<String, Object> map) {
        Comment comment = new Comment();
        comment.setCommentId(Integer.parseInt(map.get("id").toString()));
        comment.setcContent(map.get("c_content").toString());
        comment.setUserId(Integer.parseInt(map.get("user_id").toString()));
        comment.setWeiboId(Integer.parseInt(map.get("weibo_id").toString()));
        comment.setcCtime(new java.util.Date(((Timestamp) map.get("c_ctime")).getTime()));
        return comment;
    }
}
