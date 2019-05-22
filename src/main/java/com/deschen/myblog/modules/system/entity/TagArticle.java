package com.deschen.myblog.modules.system.entity;

import java.time.LocalDate;

public class TagArticle {
    private Long tagArticleId;

    private Long tagId;

    private Long articleId;

    private Integer state;

    private LocalDate createTime;

    private LocalDate updateTime;

    public Long getTagArticleId() {
        return tagArticleId;
    }

    public void setTagArticleId(Long tagArticleId) {
        this.tagArticleId = tagArticleId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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