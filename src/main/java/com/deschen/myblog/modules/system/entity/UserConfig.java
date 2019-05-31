package com.deschen.myblog.modules.system.entity;

import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class UserConfig {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long configId;

    private Long userId;

    private Integer articleSum;

    private Integer visitSum;

    private Integer thumbupSum;

    private Integer commentSum;

    private Date createTime;

    private Date updateTime;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getArticleSum() {
        return articleSum;
    }

    public void setArticleSum(Integer articleSum) {
        this.articleSum = articleSum;
    }

    public Integer getVisitSum() {
        return visitSum;
    }

    public void setVisitSum(Integer visitSum) {
        this.visitSum = visitSum;
    }

    public Integer getThumbupSum() {
        return thumbupSum;
    }

    public void setThumbupSum(Integer thumbupSum) {
        this.thumbupSum = thumbupSum;
    }

    public Integer getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(Integer commentSum) {
        this.commentSum = commentSum;
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