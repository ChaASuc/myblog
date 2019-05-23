package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.entity.Article;
import com.deschen.myblog.modules.system.entity.ArticleExample;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    List<ArticleWithBLOBs> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    ArticleWithBLOBs selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    List<ArticleDto> selectArticleDtoByCategoryIdSortDesc(
            @Param("categoryId") Long categoryId,
            @Param("state") Integer state,
            @Param("sort") String sort
    );

    List<ArticleDto> selectArticleDtoByCategoryIdAndTagIdSortDesc(
            @Param("categoryId") Long categoryId,
            @Param("tagId") Long tagId,
            @Param("state") Integer state,
            @Param("sort") String sort
    );

    List<ArticleDto> selectArticleDtoByCategoryIdsSortDesc(
            List<Long> categoryIds,
            @Param("state") Integer state,
            @Param("sort") String sort
    );

    ArticleWithBLOBsDto selectArticleWithBLOBsDtoByArticleId(
            @Param("articleId") Long articleId
    );


}