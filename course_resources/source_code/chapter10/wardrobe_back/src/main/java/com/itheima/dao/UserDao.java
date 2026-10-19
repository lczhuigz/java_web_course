package com.itheima.dao;

import com.itheima.model.User;
import org.apache.commons.dbutils.QueryRunner;
import com.itheima.utils.DruidUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    //查询用户信息
    public List<User> getUserByParam(String queryStr) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        StringBuilder sql= new StringBuilder().append(
                "SELECT id, user_name AS userName, password, phone, " +
                        "address, role FROM t_user WHERE role=2 ");
        List<User> list =null;
        if(queryStr!=null && !queryStr.isEmpty()){
            sql.append(" AND user_name LIKE ?");
            list = qr.query(sql.toString(), new BeanListHandler<>(User.class), "%" + queryStr + "%");
            if (list==null||list.size()==0){
                String replace = sql.toString().replace(" AND user_name LIKE ?", "");
                replace+=" AND phone LIKE ?";
                list = qr.query(replace,new BeanListHandler<>(User.class),"%"+queryStr+"%");
            }
            return  list;
        }
        list = qr.query(sql.toString(),new BeanListHandler<>(User.class));
        return list;
    }

    //删除普通用户
    public int delUser(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "DELETE FROM t_user WHERE id = ?";
        return qr.update(sql, id);
    }

    //更新用户信息
    public int updateUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "UPDATE t_user SET user_name = ?, password = ?, " +
                "phone = ?, address = ? WHERE id = ?";
        Object[] params={user.getUserName(),user.getPassword(),
                user.getPhone(), user.getAddress(),user.getId()};
        return qr.update(sql, params);
    }
    //用户登录
    public User login(String info, String password) throws
            SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //根据用户名和密码查询用户信息的SQL语句
        String sql = "SELECT id, user_name AS userName, password, phone," +
                "address, role " + "FROM t_user WHERE user_name = ? AND password = ?";
        Object[] params={info,password};
        User user=qr.query(sql,new BeanHandler<>(User.class),params);
        if(user==null){
            //根据手机号和密码查询用户信息的SQL语句
            sql = "SELECT id, user_name AS userName, password, phone," +
                    "address, role " + "FROM t_user WHERE phone = ? AND password = ?";
            user=qr.query(sql,new BeanHandler<>(User.class),params);
        }
        return  user;
    }
    //添加用户
    public int addUser(User user) throws SQLException {
        QueryRunner r = new QueryRunner(DruidUtils.getDataSource());
        String sql = "INSERT INTO t_user" +
                "(user_name,password,phone,address,role) VALUES(?,?,?,?,?)";
        return r.update(sql, user.getUserName(), user.getPassword(),
                user.getPhone(), user.getAddress(), user.getRole());
    }
    //判断用户名是否存在
    public User isUserNameExist(String userName) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT id, user_name AS userName " +
                "FROM t_user WHERE user_name = ?";
        User u = qr.query(sql, new BeanHandler<>(User.class),userName);
        return u;
    }
    //判断手机号是否存在
    public User isPhoneExist(String phone) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT id, phone FROM t_user WHERE phone = ?";
        User u = qr.query(sql, new BeanHandler<>(User.class),phone);
        return u;
    }
    //根据用户id查询用户信息
    public User  getUserById(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT id, user_name AS userName, password, phone," +
                "address, role " +
                "FROM t_user WHERE id = ?";
        User u = qr.query(sql, new BeanHandler<>(User.class),id);
        return u;
    }
}