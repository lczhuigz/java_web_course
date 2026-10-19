package com.itheima.test;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
public class TestDruid {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        Properties prop = new Properties();
        FileInputStream propIn = new 
                FileInputStream("src/main/resources/druid.properties");
        prop.load(propIn);        
        //创建数据库连接池对象
        DataSource dataSource = 
                DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接对象
        Connection conn = dataSource.getConnection();
        System.out.println("获取到的数据库连接对象：\n" + conn);
    }
}