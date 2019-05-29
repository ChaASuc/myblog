package com.deschen.myblog.config;


import lombok.Getter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/blog.properties")
@Getter
public class BlogConfig {

    private Integer pageSize;
    // 存储上传文件夹的名称
    private String uploadDir;

}
