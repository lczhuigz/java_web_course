package com.itheima.dao;

import com.itheima.model.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import com.itheima.utils.DruidUtils;
import java.sql.SQLException;
import java.util.*;
public class ClothesDao {
    //删除服装数据
    public int deleteClothes(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "DELETE FROM t_clothes WHERE id=?";
        return qr.update(sql, id);
    }
    //编辑服装信息
    public int editClothes(Clothes editClothes) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "UPDATE t_clothes SET cloth_name = ?, image = ?, " +
                "type_id = ?, style = ?, price = ? WHERE id = ?";
        Object[] params={editClothes.getClothName(),editClothes.getImage(),
                editClothes.getTypeId(),editClothes.getStyle(),
                editClothes.getPrice(),editClothes.getId()};
        return qr.update(sql, params);
    }
    //多条件查询服装
    public List<Clothes> getClothesByParams(String clothName, String
            style, String type) throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        StringBuilder sql=new StringBuilder().append(
                "SELECT t_clothes.id, cloth_name AS clothName," +
                "image, type_id AS typeId, type_name AS typeName, " +
                "style, price FROM t_clothes JOIN t_type ON " +
                "type_id = t_type.id WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if (clothName != null && !clothName.isEmpty()) {
            sql.append(" AND cloth_name LIKE ?" );
            params.add("%"+clothName+"%");
        }
        if (style != null && !style.isEmpty()) {
            sql.append(" AND style = ?" );
            params.add(style);
        }
        if (type != null && !type.isEmpty()) {
            sql.append(" AND type_name = ? ");
            params.add(type);
        }
        return qr.query(sql.toString(), new BeanListHandler<>(Clothes.class), params.toArray());
    }
    //添加服装
    public int addClothes(Clothes clothes) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //定义插入服装数据的SQL
        String sql = "INSERT INTO t_clothes (`cloth_name`, image, type_id," +
                "style, price) VALUES (?,?,?,?,?)";
        //设置传入SQL的参数值
        Object[] params = {clothes.getClothName(),clothes.getImage(),
                clothes.getTypeId(), clothes.getStyle(),clothes.getPrice()};
        return qr.update(sql,params);
    }

    //根据服装类别查询尺码信息
    public List<Size> getSizeByType(int typeId) throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT t_size.id, size_name AS sizeName " +
                "FROM t_size" +" JOIN t_type ON t_size.type_id = " +
                "t_type.id WHERE t_size.type_id = ?";
        List<Size> sizeList = qr.query(sql, new
                BeanListHandler<>(Size.class),typeId);
        return sizeList;
    }
    //根据服装ID查看详细信息
    public Clothes getClothById(int id) throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        String sql =
                "SELECT t_clothes.id,cloth_name AS clothName ,image," +
                        "t_type.id AS typeId, image, t_type.type_name AS typeName," +
                        " t_clothes.style, t_clothes.price FROM t_clothes " +
                        "JOIN t_type ON t_clothes.type_id = t_type.id" +
                        " WHERE t_clothes.id = ?";
        Clothes cloth = qr.query(sql, new BeanHandler<>(Clothes.class),id);
        int typeId = cloth.getTypeId();
        List<Size> sizeByType = getSizeByType(typeId);
        cloth.setSizeList(sizeByType);
        return cloth;
    }
    //根据服装名称模糊查询服装
    public List<Clothes> getClothesByName(String name) {
        try{
            //创建QueryRunner对象
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT t_clothes.id, cloth_name AS clothName, image, " +
                    "type_name AS typeName, style, price FROM t_clothes JOIN t_type" +
                    " ON t_clothes.type_id = t_type.id WHERE cloth_name LIKE ?";
            // 为参数加入通配符
            List<Clothes> clothes = qr.query(sql, new
                    BeanListHandler<>(Clothes.class), "%" + name + "%");
            return clothes;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //查询所有服装风格
    public List<String> getStyles() throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //定义查询所有服装的SQL
        String sql = "SELECT style FROM t_clothes GROUP BY style";
        List<String> styles = qr.query(sql, new ColumnListHandler<>());
        return styles;
    }
    //查询所有服装类别
    public List<Type> getTypes() throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //定义查询所有服装的SQL
        String sql = "SELECT id, type_name AS typeName FROM t_type";
        List<Type> types = qr.query(sql, new BeanListHandler<>(Type.class));
        return types;
    }
    //查询所有（指定风格或类别）的服装
    public List<Clothes> getAllClothes(String style,String type) throws
            SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
        //定义查询所有服装的SQL
        StringBuilder sql=new StringBuilder().append(
                "SELECT t_clothes.id, cloth_name AS clothName, image, "
                + "type_name AS typeName, style, price " +
                "FROM t_clothes JOIN t_type ON t_clothes.type_id = t_type.id " +
                        "WHERE 1=1 ");
        List<String> params = new ArrayList<>();
        if(style != null && !style.isEmpty()){
            sql.append(" AND style=?");
            params.add(style);
        }
        if(type != null && !type.isEmpty()){
            sql.append(" AND type_name =?");
            params.add(type);
        }
        return qr.query(sql.toString(),
                new BeanListHandler<>(Clothes.class),params.toArray());
    }
}