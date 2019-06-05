package com.deschen.myblog.modules.system.service;

import com.alibaba.fastjson.JSON;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.mapper.ArticleMapper;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ArticleDtoServiceTest extends TestUtil {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void selectByKeyWord() throws JsonProcessingException {
        List<Integer> states = new ArrayList<>();
//        states.add(0);
        states.add(1);
//        states.add(2);
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByKeyWord(states, "Git");

//        Assert.assertEquals(4, articleDtos.size());

        List<ArticleDto> articleDtoList = articleMapper.selectArticleDtoByCategoryKeyWordSortDesc(
                "前", states, "newest"
        );
        System.out.println(JsonUtil.obj2string(articleDtoList));

    }

}