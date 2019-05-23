package com.deschen.myblog.modules.system.entity;

import java.util.Date;

public class Thumbup {
    private Long thumbupId;

    private Integer thumbupCount;

    private Long articleId;

    private Date createTime;

    private Date updateTime;

    public Long getThumbupId() {
        return thumbupId;
    }

    public void setThumbupId(Long thumbupId) {
        this.thumbupId = thumbupId;
    }

    public Integer getThumbupCount() {
        return thumbupCount;
    }

    public void setThumbupCount(Integer thumbupCount) {
        this.thumbupCount = thumbupCount;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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