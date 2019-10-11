//package com.deschen.myblog.modules.system.service.impl;
//
//import com.deschen.myblog.modules.system.entity.ArticleES;
//import com.deschen.myblog.modules.system.repository.ArticleESRepository;
//import com.deschen.myblog.modules.system.service.ElasticsearchService;
//import com.github.pagehelper.PageInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import java.awt.print.Pageable;
//import java.util.List;
//
///**
// * @Author deschen
// * @Create 2019/6/5
// * @Description
// * @Since 1.0.0
// */
//@Service
//@Slf4j
//public class ElasticsearchServiceImpl implements ElasticsearchService {
//
//    @Autowired
//    private ArticleESRepository articleESRepository;
//
//
//    @Override
//    public Page<ArticleES> selectArticleEs(String title, String content, Pageable pageable) {
//        Page<ArticleES> articleESPageInfo =
//                articleESRepository.findAllByArticleTitleOrArticleContent(title, content, pageable);
//        return articleESPageInfo;
//    }
//
//    @Override
//    public void save(List<ArticleES> articleESList) {
//        articleESRepository.saveAll(articleESList);
//    }
//
//
//}
