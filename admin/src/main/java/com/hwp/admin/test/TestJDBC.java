package com.hwp.admin.test;

import org.apache.log4j.Logger;

import java.sql.*;

public class TestJDBC {
    // 连接驱动
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // 连接路径
    private static final String URL = "jdbc:sqlserver://116.228.50.26:1433;databaseName=JYDB";
    // 用户名
    private static final String USERNAME = "wjk";
    // 密码
    private static final String PASSWORD = "Wjk2016";
    // 使用log4j记录日志
    private static Logger logger = Logger.getLogger(TestJDBC.class);

    // 静态代码块
    static {
        try {
            // 加载驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        logger.debug("开始连接数据库");
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.debug("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("数据库连接失败！");
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }

    /*
     * 关闭数据库连接，注意关闭的顺序
     */
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭ResultSet失败");
            }
        }
        if (ps != null) {
            try {
                ps.close();
                ps = null;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭PreparedStatement失败");
            }
        }
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭Connection失败");
            }
        }
    }
}