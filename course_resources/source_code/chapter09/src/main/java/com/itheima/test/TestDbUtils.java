package com.itheima.test;

import java.sql.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.util.List;
public class TestDbUtils {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc_demo";
        String username = "root";
        String password = "root";
        try {
            //获取数据库连接对象
            Connection conn = DriverManager.getConnection(url, username, 
                  password);
            String sql = "SELECT * FROM t_space";
            //执行查询操作并获取结果
            QueryRunner queryRunner = new QueryRunner();
            List<Space> spaces = queryRunner.query(conn, sql,
                    new BeanListHandler<>(Space.class));
            //输出查询到的数据
            for (Space s : spaces) {
                System.out.println(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}