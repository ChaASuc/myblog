package com.deschen.myblog.modules.system.entity;

import java.time.LocalDate;

public class Thumbup {
    private Long thumbupId;

    private Integer thumbupCount;

    private Long articleId;

    private LocalDate createTime;

    private LocalDate updateTime;

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