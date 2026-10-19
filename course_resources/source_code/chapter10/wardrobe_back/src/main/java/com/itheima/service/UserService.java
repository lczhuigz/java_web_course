package com.itheima.service;

import com.itheima.dao.UserDao;
import com.itheima.model.User;
import com.itheima.model.UserVo;
import com.itheima.utils.TokenUtils;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();
    //根据条件查询用户信息
    public List<User>  getUserByParam(String queryStr){
        List<User> list = null;
        try {
             list = userDao.getUserByParam(queryStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //删除用户
    public String delUser(int id) {
        try {
            int count =  userDao.delUser(id);
            return count > 0 ? "删除成功！" : "删除失败！";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取当前登录的用户信息
    public User getCurrentUser(int id) {
        try {
            return userDao.getUserById(id);
        } catch (Exception e) {
            return null;
        }
    }
    //更新用户信息
    public String updateUser(UserVo userVo) {
        try {
            User currentUser = this.getCurrentUser(userVo.getId());
            //如果原始密码输出错误，终止用户信息更新
            if(!currentUser.getPassword().equals(userVo.getPassword())){
                return "原始密码输入错误，请重新输入！";
            }
            User userNameExist = userDao.isUserNameExist(userVo.getUserName());
            User phoneExits = userDao.isPhoneExist(userVo.getPhone());
            //判断用户名是否存在
            if(userNameExist != null&&
                    !userNameExist.getUserName().equals(userVo.getUserName())) {
              return "用户名已存在，请输入其他用户名！";
            }
            //判断手机号是否存在
            if(phoneExits != null&&
                    !phoneExits.getPhone().equals(userVo.getPhone())) {
                return "手机号已存在，请确认！";
            }
            //用户名和手机号不存在，更新用户信息
            userVo.setPassword(userVo.getNewpsw());
            int updateCount = userDao.updateUser(userVo);
            return updateCount > 0 ? "修改成功！" : "修改失败！";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "修改失败！";
    }
    //用户注册
    public String register(User user){
        try {
            if(userDao.isUserNameExist(user.getUserName()) != null) {
                return "用户名已存在，请更换！";
            }
            if(userDao.isPhoneExist(user.getPhone()) != null) {
                return "手机号已存在，请确认！";
            }
            //设置用户的角色
            user.setRole(2);
            userDao.addUser(user);
            return "注册成功，请登录！";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "注册失败！";
    }
    //用户登录
    public User login(String userInfo,String password){
        User user = null;
        try {
            //用户登录
            user = userDao.login(userInfo, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //如果登录成功，生成token并添加在user对象中
        if(user != null){
            String token = TokenUtils.generateToken(user.getId(),
                    user.getPassword());
            user.setToken(token);
        }
        return user;
    }
}