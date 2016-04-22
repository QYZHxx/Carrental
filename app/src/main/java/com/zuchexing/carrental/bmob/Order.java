package com.zuchexing.carrental.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by 情谊纵横 on 2016/4/21.
 */
public class Order extends BmobObject {//订单表

    private MyUser orderUser;         //体现的是一对一的关系，该订单用户
    private Car orderCar;             //体现的是一对一的关系，该订单车辆
    private String orderState;        //订单状态
    private Integer orderPrice;       //订单价格
    private Integer orderTime;        //有效时间

    public MyUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(MyUser orderUser) {
        this.orderUser = orderUser;
    }

    public Car getOrderCar() {
        return orderCar;
    }

    public void setOrderCar(Car orderCar) {
        this.orderCar = orderCar;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }
}
