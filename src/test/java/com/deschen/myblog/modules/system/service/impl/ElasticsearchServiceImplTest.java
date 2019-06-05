//package com.deschen.myblog.modules.system.service.impl;
//
//import com.deschen.myblog.modules.system.entity.Article;
//import com.deschen.myblog.modules.system.entity.ArticleExample;
//import com.deschen.myblog.modules.system.mapper.ArticleMapper;
//import com.deschen.myblog.utils.TestUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@Slf4j
//public class ElasticsearchServiceImplTest extends TestUtil {
//
//    @Autowired
//    private ArticleESRepository repository;
//
//    @Autowired
//    private ArticleMapper articleMapper;
//
//
//    @Test
//    public void selectArticleEs() {
//    }
//
//    @Test
//    public void save() {
//        List<Article> articles =
//                articleMapper.selectByExample(new ArticleExample());
//        ArticleES articleES = new ArticleES();
//        BeanUtils.copyProperties(articles.get(0), articleES);
//        repository.save(articleES);
//    }
//}