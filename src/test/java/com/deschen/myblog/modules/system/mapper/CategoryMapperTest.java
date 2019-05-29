package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class CategoryMapperTest extends TestUtil {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void insertCategorysSelective() {
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Category category = new Category();
            long id = new IdWorker().nextId();
            category.setCategoryId(id);
            category.setCategoryName(i + "");
            categories.add(category);
        }
        int success = categoryMapper.insertCategorysSelective(categories);
        if (success != 10) {
            log.error("【CategoryMapper】批量插入失败");
        }
        log.info("【CategoryMapper】批量插入成功");
    }
}