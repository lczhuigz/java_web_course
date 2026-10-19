package com.itheima.service;

import com.itheima.dao.ClothesDao;
import com.itheima.model.*;
import java.util.List;
public class ClothesService {
    ClothesDao clothesDao = new ClothesDao();
    //查询所有服装数据
    public List<Clothes> getAllClothesData(){
        try{
            List<Clothes> allClothes = clothesDao.getClothesByParams(null,null,null);
            //查询每件服装的尺码信息
            for (Clothes c : allClothes) {
                List<Size> sizeList =
                        clothesDao.getSizeByType(c.getTypeId());
                c.setSizeList(sizeList);
            }
            return allClothes;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //上架服装
    public String addClothes(Clothes clothes) {
        try{
            int count = clothesDao.addClothes(clothes);
            if(count > 0){
                return "上架成功";
            }else {
                return "上架失败";
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //根据条件查询服装
    public List<Clothes> getClothesByParams(String clothName, String style,
                                            String type) {
        try{
            List<Clothes> clothes =
                    clothesDao.getClothesByParams(clothName,style,type);
            for (Clothes c : clothes) {
                List<Size> sizeList =
                        clothesDao.getSizeByType(c.getTypeId());
                c.setSizeList(sizeList);
            }
            return clothes;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //删除服装
    public String delClothes(int id) {
        try{
            int count = clothesDao.deleteClothes(id);
            if(count > 0) {
                return "下架服装成功！";
            }else {
                return "下架失败！";
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //修改服装信息
    public String editClothes(Clothes editClothes) {
        try{
            int count = clothesDao.editClothes(editClothes);
            if(count > 0) {
                return "修改成功！";
            }else {
                return "修改失败！";
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //查看服装详细信息
    public Clothes getClothDetails(int id){
        try{
            Clothes clothById = clothesDao.getClothById(id);
            return clothById;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //根据服装名称模糊查询
    public List<Clothes> getClothesByName(String name){
        try{
            List<Clothes> clothesByName = clothesDao.getClothesByName(name);
            return clothesByName;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //获取所有服装
    public List<Clothes> getAllClothes(String style,String type){
        try{
            return clothesDao.getAllClothes(style,type);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //获取所有服装风格
    public List<String> getAllStyles(){
        try{
            return clothesDao.getStyles();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //获取所有服装类别
    public List<Type> getAllTypes(){
        try{
            return clothesDao.getTypes();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}