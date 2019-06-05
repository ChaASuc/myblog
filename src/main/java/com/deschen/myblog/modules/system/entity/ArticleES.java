//package com.deschen.myblog.modules.system.entity;
//
//import com.deschen.myblog.core.serializer.Long2StringSerializer;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @Author deschen
// * @Create 2019/6/5
// * @Description 搜索实体类
// * @Since 1.0.0
// */
//@Data
//@Document(indexName = "blog", type = "blog_article")
//public class ArticleES implements Serializable {
//
//    @Id
//    private Long articleId;
//
//    @Field(index= true,
//            analyzer="ik_max_word",searchAnalyzer="ik_max_word")
//    private String articleTitle;
//
//    @Field(index= true,
//            analyzer="ik_max_word",searchAnalyzer="ik_max_word")
//    private String articleContent;
//
//    private Integer state;
//
//    private Long categoryId;
//
//}
