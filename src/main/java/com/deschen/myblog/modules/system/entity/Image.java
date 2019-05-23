package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class Image {
    private Long imageId;

    private Long imageArticleId;

    private String imageUrl;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getImageArticleId() {
        return imageArticleId;
    }

    public void setImageArticleId(Long imageArticleId) {
        this.imageArticleId = imageArticleId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
}