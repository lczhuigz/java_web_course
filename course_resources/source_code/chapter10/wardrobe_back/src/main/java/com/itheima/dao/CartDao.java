package com.itheima.dao;

import com.itheima.model.*;
import com.itheima.utils.LocalDateTimeUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import com.itheima.utils.DruidUtils;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class CartDao {
    //删除当前用户购物车中的所有数据
    public int delUserCartData(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "DELETE FROM t_cart WHERE user_id = ?";
        return qr.update(sql, id);
    }
    //删除购物车数据
    public String delCartData(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "DELETE FROM t_cart WHERE id = ?";
        int count = qr.update(sql, id);
        //输出编辑结果
        if(count<1){
            return "删除失败！";
        }else{
            return "删除成功！";
        }
    }
    //查询购物车所有数据
    public List<Cart> allCartData(int userId) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT id,cloth_id AS clothId, cloth_size AS clothSize, " +
                "amount, user_id AS userId, date " +
        "FROM t_cart WHERE user_id = ?";
        List<Cart> allData = qr.query(sql, new
                BeanListHandler<>(Cart.class),userId);
        ClothesDao clothesDao=new ClothesDao();
        UserDao userDao = new UserDao();
        for (Cart data : allData) {
            Clothes clothes = clothesDao.getClothById(data.getClothId());
            data.setClothes(clothes);
            User user = userDao.getUserById(data.getUserId());
            data.setUser(user);
        }
        return allData;
    }
    //向购物车添加一条数据
    public String addClothesToCart(Cart cart) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "INSERT INTO t_cart (" +
                "`cloth_id`,cloth_size,amount,user_id, date) " +
                "VALUES (?,?,?,?,?)";
        //查询该用户是否已经添加过该服装到购物车
        Cart cartParam = clothesAmount(cart.getUserId(),
                cart.getClothId(),cart.getClothSize());
        if(cartParam == null){
            //没添加过，则进行添加，数量为1
            String dateTime = LocalDateTimeUtil.formatLocalDateTime(
                    LocalDateTime.now());
            Object[] params = {cart.getClothId(),cart.getClothSize(),
                    1,cart.getUserId(),dateTime};
            int addCount = qr.update(sql, params);
            return addCount > 0 ? "添加成功！" : "添加失败";
        }else {
            //添加过，则修改该服装的数量
            int count = updateClothData(cartParam.getId(), 
                    cartParam.getAmount() + 1);
            return count > 0 ? "添加成功！" : "添加失败";
        }
    }
    //查询该用户是否已经添加过该服装
    public Cart clothesAmount(int userId, int clothId,String clothSize) 
            throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //定义查询对应服装
        String sql = "SELECT id, cloth_id AS clothId, cloth_size AS clothSize, " +
                "amount, user_id AS userId, date FROM t_cart WHERE" +
                " user_id = ? and cloth_id = ? and cloth_size = ?";
        Object[] params = {userId,clothId,clothSize};
        Cart cart = qr.query(sql,new BeanHandler<>(Cart.class),params);
        System.out.println(cart);
        return cart;
    }
    //修改购物车服装数据
    public int updateClothData(int id,int amount) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "UPDATE t_cart SET amount = ? WHERE id = ?";
        Object[] params={amount,id};
        return qr.update(sql, params);
    }
}