package com.zuchexing.carrental.bmob;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 情谊纵横 on 2016/4/21.
 */
public class MyUser extends BmobUser {//用户拓展类
    private String name;                //姓名
    private String nickname;            //昵称
    private String identityCard;        //身份证
    private BmobRelation carCollecs;    //收藏车辆
    private BmobRelation storeCollecs;  //收藏店铺
    private String   introduce;         //简介
    private String    education;        //学历
    private String   post;              //职位
    private String   likes;             //兴趣爱好
    private BmobFile headPortrait;      //头像

    public BmobFile getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(BmobFile headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

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
