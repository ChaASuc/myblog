package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.core.utils.PageVOUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ArticleMapperTest extends TestUtil {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void select() throws JsonProcessingException {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdSortDesc(1L, 1,null);
        log.info("【ArticleMapper】根据种类id，文章状态码，排序规则获取文章（不包含内容）实体类，articleWithBLOBsDto = {}",
                JsonUtil.obj2string(articleDtos));

        List<Long> categoryIds = new ArrayList<>();
        categoryIds.add(1L);
        List<ArticleDto> articleDtos1 =
                articleMapper.selectArticleDtoByCategoryIdsSortDesc(categoryIds, 1, null);
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
}