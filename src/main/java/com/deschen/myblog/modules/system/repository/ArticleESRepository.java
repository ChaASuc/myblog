//package com.deschen.myblog.modules.system.repository;
//
//import com.deschen.myblog.modules.system.entity.ArticleES;
//import com.github.pagehelper.PageInfo;
//import org.springframework.data.domain.Page;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.stereotype.Component;
//
//import java.awt.print.Pageable;
//
///**
// * @Author deschen
// * @Create 2019/6/5
// * @Description
// * @Since 1.0.0
// */
//@Component
//public interface ArticleESRepository extends ElasticsearchRepository<ArticleES, Long> {
//
//    Page<ArticleES> findAllByArticleTitleOrArticleContent(
//            String title, String content, Pageable pageable);
//
//    Page<ArticleES> findAllByArticleTitleOrAndArticleContentAndStateEquals(
//            String title, String content, Integer state, Pageable pageable
//    );
//}
