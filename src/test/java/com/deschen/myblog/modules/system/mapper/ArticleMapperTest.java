package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArticleMapperTest extends TestUtil {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void select() throws JsonProcessingException {

        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdSortDesc(1L, states,"newest");
        log.info("【ArticleMapper】根据种类id，文章状态码，排序规则获取文章（不包含内容）实体类，articleWithBLOBsDto = {}",
                JsonUtil.obj2string(articleDtos));

        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);

        List<ArticleDto> articleDtos1 =
                articleMapper.selectArticleDtoByCategoryIdsSortDesc(categoryIds, states, "newest");
        log.info("【ArticleMapper】根据种类id集合，文章状态码，排序规则获取文章（不包含内容）实体类，articleWithBLOBsDto = {}",
                JsonUtil.obj2string(articleDtos1));
    }


    @Test
    public void selectArticleWithBLOBsDtoByArticleId() throws JsonProcessingException {
        ArticleWithBLOBsDto articleWithBLOBsDto =
                articleMapper.selectArticleWithBLOBsDtoByArticleId(1L);
        log.info("【ArticleMapper】根据文章id获取文章实体类，articleWithBLOBsDto = {}",
                JsonUtil.obj2string(articleWithBLOBsDto));
    }

    @Test
    public void selectArticleDtoByCategoryIdAndTagIdSortDesc() throws JsonProcessingException {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<ArticleDto> articleDtos = articleMapper.selectArticleDtoByCategoryIdAndTagIdSortDesc(
                1L, 1L, states, "newest"
        );
        log.info("【ArticleMapper】根据种类id，标签id，文章状态码，排序规则获取文章（不包含内容）实体类，articleWithBLOBsDto = {}",
                JsonUtil.obj2string(articleDtos));
    }

    @Test
    public void selectArticleDtoByTagKeyWordSortDesc() throws JsonProcessingException {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<ArticleDto> articleDtos = articleMapper.selectArticleDtoByTagKeyWordSortDesc(
                "html", states, "newest"
        );

        System.out.println(JsonUtil.obj2string(articleDtos));
    }

    @Test
    public void selectArticleDtoByCategoryKeyWordSortDesc() throws JsonProcessingException {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryKeyWordSortDesc("前", states, "newest");

        System.out.println(JsonUtil.obj2string(articleDtos));
    }

    @Test
    public void selectArticleDtoByCategoryIdOrTagIdAndKeyWordSortDesc() throws JsonProcessingException {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<ArticleDto> articleDtos = articleMapper.selectArticleDtoByCategoryIdOrTagIdAndKeyWordSortDesc(
                null, null, "#", states, "newest"
        );
        System.out.println(JsonUtil.obj2string(articleDtos));
        System.out.println("#########################");
        List<ArticleDto> articleDtosCategory = articleMapper.selectArticleDtoByCategoryIdOrTagIdAndKeyWordSortDesc(
                1133935877207216100L, null, "#", states, "newest"
        );
        System.out.println(JsonUtil.obj2string(articleDtosCategory));
        System.out.println("##########################");

        List<ArticleDto> articleDtosTag = articleMapper.selectArticleDtoByCategoryIdOrTagIdAndKeyWordSortDesc(
                null, 1133990004901670912L, "#", states, "newest"
        );
        System.out.println(JsonUtil.obj2string(articleDtosTag));
        System.out.println("##########################");

        List<ArticleDto> articleDtosCategoryDto = articleMapper.selectArticleDtoByCategoryIdOrTagIdAndKeyWordSortDesc(
                1133935877207216100L, 1133990004901670912L, "#", states, "newest"
        );
        System.out.println(JsonUtil.obj2string(articleDtosCategoryDto));
        System.out.println("##########################");
    }
}