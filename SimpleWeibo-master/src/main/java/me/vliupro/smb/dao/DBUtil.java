package me.vliupro.smb.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vliupro on 16-5-20.
 */
public class DBUtil {

    private static Logger logger = LogManager.getLogger(DBUtil.class);

    private String DATABASE = "simp_mb";

    /**
     * 注册 jdbc 驱动
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("JDBC驱动加载失败");
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    public Connection open() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL_BASE + DATABASE + URL_PARAM, USER, PASSWD);
        } catch (SQLException e) {
            logger.error("获取数据库连接失败");
        }
        return conn;
    }

    /**
     * 关闭数据库的结果集、语句对象和连接
     *
     * @param conn
     * @param st
     * @param rs
     */
    public void close(Connection conn, PreparedStatement st, ResultSet rs) {
        //后打开的先关闭
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                logger.error("关闭 ResultSet 失败");
            }
        }
        if (st != null) {
            try {
                st.close();
                st = null;
            } catch (SQLException e) {
                logger.error("关闭 PreparedStatement 失败");
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                logger.error("关闭 Connection 失败");
            }
        }
    }

    /**
     * 查询多个对象的方法
     *
     * @param sql
     * @param obj
     * @return
     */
    public List<Map<String, Object>> queryList(String sql, Object... obj) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Connection conn = open();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            setParameter(ps, obj);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            Map<String, Object> rowMap = null;
            while (rs.next()) {
                rowMap = new HashMap<String, Object>();
                for (int i = 0; i < colCount; i++) {
                    String colLabel = rsmd.getColumnLabel(i + 1);
                    rowMap.put(colLabel, rs.getObject(colLabel));
                }
                data.add(rowMap);
            }

        } catch (SQLException e) {
            logger.error("查询多个对象失败，exception: " + e.getMessage());
        } finally {
            close(conn, ps, rs);
        }
        return data;
    }

    /**
     * 查询单个对象的方法
     *
     * @param sql
     * @param obj
     * @return
     */
    public Map<String, Object> query(String sql, Object... obj) {
        Map<String, Object> data = null;
        Connection connection = open();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(sql);
            setParameter(ps, obj);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (rs.next()) {
                data = new HashMap<String, Object>();
                for (int i = 0; i < colCount; i++) {
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    data.put(columnLabel, rs.getObject(columnLabel));
                }
            }
        } catch (SQLException e) {
            logger.error("查询单个对象失败，exception: " + e.getMessage());
        } finally {
            close(connection, ps, rs);
        }
        return data;
    }

    /**
     * 增加、修改、删除
     *
     * @param sql
     * @param obj
     * @return
     */
    public int update(String sql, Object... obj) {
        Connection conn = open();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = conn.prepareStatement(sql);
            setParameter(ps, obj);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("增加、修改、删除 - 失败，exception: " + e.getMessage());
        } finally {
            close(conn, ps, null);
        }
        return rows;
    }

    /**
     * 设置参数
     *
     * @param ps
     * @param obj
     * @throws SQLException
     */
    public void setParameter(PreparedStatement ps, Object... obj) throws SQLException {
        if (obj != null && obj.length > 0) {
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
        }
    }

    final private String URL_BASE = "jdbc:mysql://localhost:3306/";
    final private String URL_PARAM = "?useUnicode=true&&characterEncoding=UTF-8";
    final private String USER = "root";
    final private String PASSWD = "liujida";
}
