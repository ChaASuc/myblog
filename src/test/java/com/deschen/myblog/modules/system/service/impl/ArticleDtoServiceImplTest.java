//package com.deschen.myblog.modules.system.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.deschen.myblog.core.constants.BlogConstant;
//import com.deschen.myblog.modules.system.dto.ArticleDto;
//import com.deschen.myblog.modules.system.service.ArticleDtoService;
//import com.deschen.myblog.utils.TestUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@Slf4j
//public class ArticleDtoServiceImplTest extends TestUtil {
//
//    @Autowired
//    private ArticleDtoService articleDtoService;
//
//    @Test
//    public void selectArticleDtoByCategoryId() {
//        Long categoryId = 1L;
//        Integer state = BlogConstant.RECORD_VALID;
//        String sort = "hot";
//        List<ArticleDto> articleDtos = articleDtoService.selectArticleDtoByCategoryId(
//                categoryId, state, sort
//        );
//        log.info("【文章service】根种类id，状态，排序获取articleDtos," +
//                        " categoryId = {}, state = {}, sort = {}, articleDtos = {}",
//                categoryId, state, sort, JSONObject.toJSONString(articleDtos));
//    }
//
//    @Test
//    public void selectArticleDtoByCategoryIds() {
//        List<Long> categoryIds = new ArrayList<>();
//        categoryIds.add(1L);
//        categoryIds.add(2L);
//        Integer state = BlogConstant.RECORD_VALID;
//        String sort = "hot";
//        List<ArticleDto> articleDtos = articleDtoService.selectArticleDtoByCategoryIds(
//                categoryIds, state, sort
//        );
//        log.info("【文章service】根种类id，状态，排序获取articleDtos," +
//                        " categoryId = {}, state = {}, sort = {}, articleDtos = {}",
//                categoryIds, state, sort, JSONObject.toJSONString(articleDtos));
//    }
//}