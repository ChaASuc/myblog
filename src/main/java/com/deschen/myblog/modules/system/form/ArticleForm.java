package com.deschen.myblog.modules.system.form;

import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description
 * @Since 1.0.0
 */
@Data
public class ArticleForm extends ArticleWithBLOBs implements Serializable {

    // 标签id集合
    private List<Tag> tags;

}
