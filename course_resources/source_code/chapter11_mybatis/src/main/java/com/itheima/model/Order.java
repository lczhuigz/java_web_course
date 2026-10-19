package com.itheima.model;

public class Order {
    private int orderId;          //订单编号
    private int userId;           //用户编号
    private double price;         //订单价格
    //… getter方法、setter方法和重写的toString()方法

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}