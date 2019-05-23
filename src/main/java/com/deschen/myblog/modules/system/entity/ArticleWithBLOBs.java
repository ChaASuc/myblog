package com.deschen.myblog.modules.system.entity;

public class ArticleWithBLOBs extends Article {
    private String articleHtml;

    private String articleContent;

    public String getArticleHtml() {
        return articleHtml;
    }

    public void setArticleHtml(String articleHtml) {
        this.articleHtml = articleHtml == null ? null : articleHtml.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}