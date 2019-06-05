package com.deschen.myblog.config;


import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/blog.properties")
@Data
public class BlogConfig {

    @Value("${article.pageSize}")
    private Integer pageSize;

    @Value("${guestbook.pageSize}")
    private Integer gPageSize;

    // 存储上传文件夹的名称
    @Value(("${uploadDir}"))
    private String uploadDir;

}
