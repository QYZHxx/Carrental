package com.zuchexing.carrental.bmob;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 情谊纵横 on 2016/4/21.
 */
public class MyUser extends BmobUser {//用户拓展类
    private String name;                //姓名
    private String identityCard;        //身份证
    private BmobRelation carCollecs;    //收藏车辆
    private BmobRelation storeCollecs;  //收藏店铺
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobRelation getCarCollecs() {
        return carCollecs;
    }

    public void setCarCollecs(BmobRelation carCollecs) {
        this.carCollecs = carCollecs;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public BmobRelation getStoreCollecs() {
        return storeCollecs;
    }

    public void setStoreCollecs(BmobRelation storeCollecs) {
        this.storeCollecs = storeCollecs;
    }
}
