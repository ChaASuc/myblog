package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.Tag;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description
 * @Since 1.0.0
 */
public interface CategoryDtoService {

    boolean insertTags(List<Tag> tags);

    boolean insertCategories(List<Category> categories);

    boolean updateCategories(List<Category> categories);

    boolean updateTags(List<Tag> tags);
}
