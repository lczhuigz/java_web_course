package com.itheima;

import com.itheima.dao.UserMapper;
import com.itheima.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import java.io.*;
public class Test {
    public static void main(String[] args) {
        try {
            //读取mybatis-config.xml文件内容到Reader对象中
            Reader reader =
                    Resources.getResourceAsReader("mybatis-config.xml");
            //创建SqlSessionFactory类的实例
            SqlSessionFactory sqlMapper = new
                    SqlSessionFactoryBuilder().build(reader);
            //创建SqlSession对象
            SqlSession session = sqlMapper.openSession();
            //获得接口代理对象
            UserMapper userMapper = session.getMapper(UserMapper.class);
            //传入参数查询，返回结果
            User user = userMapper.getUserById(2);
            //输出结果
            System.out.println(user);
            //关闭会话
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}