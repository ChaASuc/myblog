package com.deschen.myblog.core.constants;

/**
 * @Author deschen
 * @Create 2019/4/25
 * @Description
 * @Since 1.0.0
 */
public interface BlogConstant {

    Integer RECORD_VOID = 0;

    Integer RECORD_VALID = 1;

    Integer RECORD_DRAFT = 2;


    /*页面大小*/
    Integer pageSize = 10;

    Integer CATEGORY = 1;

    Integer TAG = 2;

    Integer HOT = 1;

    Integer NEWEST = 2;

    Integer COMMENT = 3;

    // 默认用户头像图片文件夹
    Long DEFAULT_DIRID = 1133930631181381632L;

    String IMAGEURL = "http://10.79.3.57:8080/myblog/user/image/";

    // 默认博客文章上传图片文件夹
    Long ARTICLE_DIRID = 1133930737993527296L;

    Integer FLAGADMIN = 1;

    // 默认博主的用户id
    Long AUTHOR_ID = 1L;
}
