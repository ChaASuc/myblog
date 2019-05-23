package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.form.ArticleForm;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description 文章dto事务层
 * @Since 1.0.0
 */
public interface ArticleDtoService {

    List<ArticleDto> selectArticleDtoByCategoryId(Long categoryId, Integer state, String sort);

    List<ArticleDto> selectArticleDtoByCategoryIdAndTagId(Long categoryId, Long tagId, Integer state, String sort);

    List<ArticleDto> selectArticleDtoByCategoryIds(List<Long> categoryIds, Integer state, String sort);

    ArticleWithBLOBsDto selectArticleWithBLOBsDtoByArticleId(Long articleId);

    boolean insertArticleWithBLOBs(ArticleForm articleForm);

    boolean insertTagArticle(ArticleForm articleForm);

    boolean updateArticleWithBLOBs(ArticleForm articleForm);

    boolean updateTagArticle(ArticleForm articleForm);

//    updateA

}
