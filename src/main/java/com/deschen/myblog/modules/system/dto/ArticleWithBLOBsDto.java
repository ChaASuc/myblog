package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description 文章dto实体类（包含文章）
 * @Since 1.0.0
 */
@Data
public class ArticleWithBLOBsDto extends ArticleWithBLOBs implements Serializable {

    // 种类名
    private String categoryName;

    // 访问数
    private Integer visitCount;

    // 点赞数
    private Integer thumbupCount;

    // 评论数
    private Integer commentCount;

    // 标签集合
    private List<Tag> tags;
}
