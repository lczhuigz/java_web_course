package com.itheima.test;

import java.sql.*;

public class TestJDBC2 {
    public static void main(String[] args) throws SQLException  {
        Connection conn =null;
        Statement stmt =null;
        ResultSet rs =null;
        try {
            // 加载并注册驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 通过DriverManager获取数据库连接
            String url = "jdbc:mysql://localhost:3306/jdbc_demo";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url,username, password);
            // 通过Connection对象获取Statement对象
            stmt = conn.createStatement();
            // 执行SQL语句
            String sql = "SELECT * FROM t_space";
            rs = stmt.executeQuery(sql);
            // 操作ResultSet结果集
            System.out.println("编号  名称      类型      发射时间");
            while (rs.next()) {
                int id = rs.getInt("id");    // 通过列名获取指定字段的值
                String name = rs.getString("name");
                String type = rs.getString("type");
                Date launchtime= rs.getDate("launchtime");
                System.out.println(id + "   " + name + "   "
                        + type + "   " + launchtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接，释放资源
            if(rs !=null){ rs.close(); }
            if(stmt !=null){ stmt.close(); }
            if(conn !=null){ conn.close(); }
        }
    }
}