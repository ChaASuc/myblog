package com.deschen.myblog.modules.system.entity;

import com.deschen.myblog.core.serializer.Date2LongSerializer;
import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class TagArticle {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long tagArticleId;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long tagId;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long articleId;

    private Integer state;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

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