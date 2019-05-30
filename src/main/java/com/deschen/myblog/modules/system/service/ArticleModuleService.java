package com.deschen.myblog.modules.system.service;

/**
 * @Author deschen
 * @Create 2019/5/28
 * @Description 文章的组将（浏览量，点赞量，评论量）
 * @Since 1.0.0
 */
public interface ArticleModuleService {

    void insertArticleModule(Long articleId);

    void transVisitCountFromRedisDB();

    void transThumbupCountFromRedisDB();

    void transCommentCountFromRedisDB();
}
