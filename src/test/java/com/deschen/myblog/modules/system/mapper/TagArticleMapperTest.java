package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.entity.TagArticle;
import com.deschen.myblog.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class TagArticleMapperTest extends TestUtil {

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Test
    public void insertTagArticlesSelective() {
        List<TagArticle> tagArticles = new ArrayList<>();
        for (Long l = 5L; l <= 10; l++) {
            TagArticle tagArticle = new TagArticle();
            long id = new IdWorker().nextId();
            tagArticle.setTagArticleId(id);
            tagArticle.setTagId(l);
            tagArticle.setArticleId(l);
            tagArticles.add(tagArticle);
        }
        int success = tagArticleMapper.insertTagArticlesSelective(tagArticles);
        if (success == tagArticles.size()) {
            log.info("【tagArticleMapper】批量插入成功");
        }else {
            log.error("【tagArticleMapper】批量插入失败");
        }
    }

}