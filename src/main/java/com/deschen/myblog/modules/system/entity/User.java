package com.deschen.myblog.modules.system.entity;

import com.deschen.myblog.core.serializer.Date2LongSerializer;
import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class User {
    @JsonSerialize(using = Long2StringSerializer.class)
    private Long userId;

    private String userName;

    private String userPassword;

    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private String salt;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long imageId;

    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}