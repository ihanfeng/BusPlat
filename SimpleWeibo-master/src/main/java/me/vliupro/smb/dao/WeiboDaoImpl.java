package me.vliupro.smb.dao;

import me.vliupro.smb.po.Weibo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vliupro on 16-5-23.
 */
public class WeiboDaoImpl extends BaseImpl implements WeiboDao {

    public boolean addWeibo(Weibo weibo) {
        String sql = "insert into db_weibo (w_content, user_id, w_ctime, is_original) " +
                "values(?,?,?,?)";
        int count = this.db.update(sql, weibo.getwContent(), weibo.getUserId(),
                weibo.getwCtime(), weibo.isOriginal());
        return count > 0;
    }

    @Override
    public boolean addForwardWeibo(Weibo weibo) {
        String sql = "insert into db_weibo (w_content, user_id, w_ctime, is_original, remark, w_ftime, origin_id) " +
                "values(?,?,?,?,?,?,?)";
        int count = this.db.update(sql, weibo.getwContent(), weibo.getUserId(),
                weibo.getwCtime(), weibo.isOriginal(), weibo.getRemark(),
                weibo.getwFtime(), weibo.getOriginId());
        return count > 0;
    }

    public boolean deleteWeibo(int weiboId) {
        String sql = "delete from db_weibo where id=?";
        int count = this.db.update(sql, weiboId);
        return count > 0;
    }

    public Weibo getWeiboById(int weiboId) {
        String sql = "select * from db_weibo where id=?";
        Map<String, Object> weiboMap = this.db.query(sql, weiboId);
        if (weiboMap != null) {
            return (Weibo) this.generate(weiboMap);
        } else {
            return null;
        }
    }

    public List<Weibo> getWeibosLimited(int begin, int total) {
        String sql = "select * from db_weibo ORDER BY w_ctime DESC limit ?,?";
        List<Weibo> weibos = new ArrayList<Weibo>();
        List<Map<String, Object>> weiboMaps = this.db.queryList(sql, begin, total);
        for (Map<String, Object> weiboMap : weiboMaps) {
            weibos.add((Weibo) this.generate(weiboMap));
        }
        return weibos;
    }

    public List<Weibo> getWeibosByUserId(int userId) {
        List<Weibo> weibos = new ArrayList<Weibo>();
        String sql = "select * from db_weibo where user_id=? ORDER BY w_ctime DESC";
        List<Map<String, Object>> weiboMaps = this.db.queryList(sql, userId);
        for (Map<String, Object> weiboMap : weiboMaps) {
            weibos.add((Weibo) this.generate(weiboMap));
        }
        return weibos;
    }

    @Override
    public int getTotalNum() {
        String sql = "select count(*) as num from db_weibo";
        Map<String, Object> map = db.query(sql);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    public int getUserWeiboNum(int userId) {
        String sql = "select count(*) as num from db_weibo where user_id=?";
        Map<String, Object> map = db.query(sql, userId);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    public int getForwardWeiboNum(int weiboId) {
        String sql = "select count(*) as num from db_weibo where origin_id=?";
        Map<String, Object> map = db.query(sql, weiboId);
        String num = map.get("num").toString();
        if (num != null) {
            return Integer.parseInt(num);
        } else {
            return 0;
        }
    }

    @Override
    public List<Weibo> searchWeibos(String content, int begin, int total) {
        List<Weibo> weibos = new ArrayList<>();
        String sql = "SELECT * FROM db_weibo WHERE UPPER(w_content) LIKE BINARY CONCAT('%',UPPER(?),'%')";
        List<Map<String, Object>> weiboMaps = this.db.queryList(sql, content);
        for (Map<String, Object> weiboMap : weiboMaps) {
            weibos.add((Weibo) this.generate(weiboMap));
        }
        return weibos;
    }

    @Override
    protected Object generate(Map<String, Object> map) {
        Weibo weibo = new Weibo();
        weibo.setWeiboId(Integer.parseInt(map.get("id").toString()));
        weibo.setUserId(Integer.parseInt(map.get("user_id").toString()));
        weibo.setwContent(map.get("w_content").toString());
        weibo.setwCtime((new java.util.Date(((Timestamp) map.get("w_ctime")).getTime())));
        weibo.setOriginal((Boolean) map.get("is_original"));
        weibo.setRemark(map.get("remark").toString());
        if (weibo.isOriginal()) {
            weibo.setwFtime(null);
        } else {
            weibo.setwFtime((new java.util.Date(((Timestamp) map.get("w_ftime")).getTime())));
        }
        weibo.setOriginId(Integer.parseInt(map.get("origin_id").toString()));
        return weibo;
    }

    public static void main(String[] args) {
        WeiboDaoImpl wd = new WeiboDaoImpl();
        List<Weibo> weibos = wd.searchWeibos("哈", 1, 10);
        System.out.println("weibos: " + weibos);
    }
}
