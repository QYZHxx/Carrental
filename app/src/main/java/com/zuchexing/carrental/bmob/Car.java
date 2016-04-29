package com.zuchexing.carrental.bmob;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 情谊纵横 on 2016/4/21.
 */
public class Car extends BmobObject {//车辆信息表

    private MyUser myUser;           //车辆的拥有者,体现的是一对一的关系，该车辆属于某个用户
    private String carNum;           //车牌号
    private String carAddress;       //地址
    private String carCity;          //所在城市
    private String carPromotional;   //优惠
    private Integer collectNum;      //收藏个数
    private String carState;         //状态
    private List<BmobFile> carPic;   //照片
    private Integer carKm;            //行驶公里
    private Integer carVehices;       //载人数
    private Integer carAge;           //车龄
    private Integer carRentPrice;     //租用的价格
    private String  carName;          //车辆名称
    private String carLongitude;     //车辆经度
    private String carLatitude;      //车辆纬度
    private BmobFile carImage;
   // private  BmobRelation carCollecs;     //车辆收藏者,多对多关系：用于存储喜欢该车辆的所有用户


    public BmobFile getCarImage() {
        return carImage;
    }

    public void setCarImage(BmobFile carImage) {
        this.carImage = carImage;
    }

    public String getCarLongitude() {
        return carLongitude;
    }

    public void setCarLongitude(String carLongitude) {
        this.carLongitude = carLongitude;
    }

    public String getCarLatitude() {
        return carLatitude;
    }

    public void setCarLatitude(String carLatitude) {
        this.carLatitude = carLatitude;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }

    public String getCarCity() {
        return carCity;
    }

    public void setCarCity(String carCity) {
        this.carCity = carCity;
    }

    public String getCarPromotional() {
        return carPromotional;
    }

    public void setCarPromotional(String carPromotional) {
        this.carPromotional = carPromotional;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public List<BmobFile> getCarPic() {
        return carPic;
    }

    public void setCarPic(List<BmobFile> carPic) {
        this.carPic = carPic;
    }

    public Integer getCarKm() {
        return carKm;
    }

    public void setCarKm(Integer carKm) {
        this.carKm = carKm;
    }

    public Integer getCarVehices() {
        return carVehices;
    }

    public void setCarVehices(Integer carVehices) {
        this.carVehices = carVehices;
    }

    public Integer getCarAge() {
        return carAge;
    }

    public void setCarAge(Integer carAge) {
        this.carAge = carAge;
    }

    public Integer getCarRentPrice() {
        return carRentPrice;
    }

    public void setCarRentPrice(Integer carRentPrice) {
        this.carRentPrice = carRentPrice;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

//    public BmobRelation getCarCollecs() {
//        return carCollecs;
//    }
//
//    public void setCarCollecs(BmobRelation carCollecs) {
//        this.carCollecs = carCollecs;
//    }
}
