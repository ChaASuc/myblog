package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.entity.TagArticle;
import com.deschen.myblog.modules.system.form.ArticleForm;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description 文章dto事务层
 * @Modify: 2019/5/26 状态码查询由一个到批量查询
 * @Since 1.0.0
 */
public interface ArticleDtoService {

    List<ArticleDto> selectArticleDtoByCategoryId(Long categoryId, List<Integer> states, String sort);

    List<ArticleDto> selectArticleDtoByCategoryIdAndTagId(Long categoryId, Long tagId, List<Integer> states, String sort);

    List<ArticleDto> selectArticleDtoByTagId(Long tagId, List<Integer> states, String sort);

    ArticleWithBLOBsDto selectArticleWithBLOBsDtoByArticleId(Long articleId);

    List<ArticleDto> selectArticleDto(List<Integer> states, String sort);

    boolean insertArticleWithBLOBs(ArticleWithBLOBs articleWithBLOBs);

    boolean insertTagArticles(Long articleId, List<Tag> tags);

    boolean updateArticleWithBLOBs(ArticleWithBLOBs articleWithBLOBs);

    boolean deleteTagArticle(Long articleId, List<Long> tagIds);

    List<Tag> selectTagsByArticleId(Long articleId);


    void selectArticle(Long articleId);
}
