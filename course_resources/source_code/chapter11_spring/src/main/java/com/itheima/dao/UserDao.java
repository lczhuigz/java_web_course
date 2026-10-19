package com.itheima.dao;

import org.springframework.stereotype.Repository;
@Repository
public class UserDao {
    public void saveUser(){
        System.out.println("UserDao成功执行saveUser()方法！");
    }
}