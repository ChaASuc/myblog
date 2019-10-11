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

    /**
     * @Modify: 2019/5/26 状态码查询由一个到批量查询
     */
    List<ArticleDto> selectArticleDtoByCategoryIdSortDesc(
            @Param("categoryId") Long categoryId,
            @Param("states") List<Integer> states,
            @Param("sort") String sort
    );


    /**
     * @Modify: 2019/5/26 状态码查询由一个到批量查询
     */
    List<ArticleDto> selectArticleDtoByCategoryIdAndTagIdSortDesc(
            @Param("categoryId") Long categoryId,
            @Param("tagId") Long tagId,
            @Param("states") List<Integer> states,
            @Param("sort") String sort
    );

    List<ArticleDto> selectArticleDtoByCategoryIdOrTagIdAndKeyWordSortDesc(
            @Param("categoryId") Long categoryId,
            @Param("tagId") Long tagId,
            @Param("keyword") String keyword,
            @Param("states") List<Integer> states,
            @Param("sort") String sort
    );

    List<ArticleDto> selectArticleDtoByTagIdSortDesc(
            @Param("tagId") Long tagId,
            @Param("states") List<Integer> states,
            @Param("sort") String sort);

    List<ArticleDto> selectArticleDtoByTagKeyWordSortDesc(
            @Param("keyword") String keyword,
            @Param("states") List<Integer> states,
            @Param("sort") String sort);


    /**
     * @Modify: 2019/5/26 状态码查询由一个到批量查询
     */
    List<ArticleDto> selectArticleDtoSortDesc(
            @Param("states") List<Integer> states,
            @Param("sort") String sort
    );

    /**
     * @Modify: 2019/5/26 状态码查询由一个到批量查询
     */
    List<ArticleDto> selectArticleDtoByCategoryIdsSortDesc(
            @Param("categoryIds") List<Long> categoryIds,
            @Param("states") List<Integer> states,
            @Param("sort") String sort
    );

    List<ArticleDto> selectArticleDtoByCategoryKeyWordSortDesc(
            @Param("keyword") String keyword,
            @Param("states") List<Integer> states,
            @Param("sort") String sort
    );


    ArticleWithBLOBsDto selectArticleWithBLOBsDtoByArticleId(
            @Param("articleId") Long articleId
    );

    List<ArticleDto> selectArticleDtoByKeyWord(
            @Param("states") List<Integer> states,
            @Param("keyword") String keyword
    );



}