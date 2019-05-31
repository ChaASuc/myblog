package com.deschen.myblog.modules.system.entity;

import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Review {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long reviewId;

    @NotBlank(message = "内容不为空")
    private String reviewContent;

    @JsonSerialize(using = Long2StringSerializer.class)
    @NotEmpty(message = "文章id不为空")
    private Long articleId;

    @JsonSerialize(using = Long2StringSerializer.class)
    @NotEmpty(message = "用户id不为空")
    private Long userId;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long reviewParent;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    private String reviewUrl;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent == null ? null : reviewContent.trim();
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReviewParent() {
        return reviewParent;
    }

    public void setReviewParent(Long reviewParent) {
        this.reviewParent = reviewParent;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl == null ? null : reviewUrl.trim();
    }
}