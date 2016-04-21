package com.zuchexing.carrental.bmob;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 情谊纵横 on 2016/4/21.
 */
public class Store extends BmobObject {//店铺信息表

    private MyUser myUser;             //体现的是一对一的关系，该店铺属于某个用户
    private String storeName;          //店铺的名字
    private String storeAddress;       //店铺的地址
    private String storeIntroduce;     //店铺简介
    private String storePhone;         //店铺的联系电话
    private List<BmobFile> storePic;   //店铺的图片
    private Integer collectNum;        //店铺的收藏个数

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreIntroduce() {
        return storeIntroduce;
    }

    public void setStoreIntroduce(String storeIntroduce) {
        this.storeIntroduce = storeIntroduce;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public List<BmobFile> getStorePic() {
        return storePic;
    }

    public void setStorePic(List<BmobFile> storePic) {
        this.storePic = storePic;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }
}
