package com.itheima.service;

import com.itheima.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void saveUser(){
        System.out.println("UserService成功执行saveUser()方法！");
        userDao.saveUser();
    }
}