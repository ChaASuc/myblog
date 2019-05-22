package com.deschen.myblog.modules.system.entity;

import java.time.LocalDate;

public class Article {
    private Long articleId;

    private String articleTitle;

    private Byte articleIspublic;

    private Integer state;

    private Integer articleVisit;

    private Integer articleThumbup;

    private Integer articleComment;

    private String articleUrl;

    private Long articleCategoryId;

    private LocalDate createTime;

    private LocalDate updateTime;

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

    public Byte getArticleIspublic() {
        return articleIspublic;
    }

    public void setArticleIspublic(Byte articleIspublic) {
        this.articleIspublic = articleIspublic;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getArticleVisit() {
        return articleVisit;
    }

    public void setArticleVisit(Integer articleVisit) {
        this.articleVisit = articleVisit;
    }

    public Integer getArticleThumbup() {
        return articleThumbup;
    }

    public void setArticleThumbup(Integer articleThumbup) {
        this.articleThumbup = articleThumbup;
    }

    public Integer getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(Integer articleComment) {
        this.articleComment = articleComment;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    public Long getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
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