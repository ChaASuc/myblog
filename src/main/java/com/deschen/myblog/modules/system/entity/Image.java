package com.deschen.myblog.modules.system.entity;

import java.time.LocalDate;

public class Image {
    private Long imageId;

    private Long imageArticleId;

    private String imageUrl;

    private Integer state;

    private LocalDate createTime;

    private LocalDate updateTime;

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

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }
}