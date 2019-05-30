package com.deschen.myblog.modules.system.entity;

import com.deschen.myblog.core.serializer.Date2LongSerializer;
import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class Article {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long articleId;

    private String articleTitle;

    private Integer state;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long categoryId;

    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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