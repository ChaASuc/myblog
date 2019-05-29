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

    List<Long> insertTags(List<Tag> tags);

    void insertCategories(List<Category> categories);

    /**
     * @Modify: 一对多修改（集合形式）修改种类及其标签
     * @param categoryDtos
     * @return
     */
    void updateCategoryDtos(List<CategoryDto> categoryDtos);

    List<CategoryDto> selectCategoryDto(Integer state, String sort);

    List<Category> selectCategory(Integer state, String sort);

    List<Tag> selectTag(Long categoryId, Integer state, String sort);

    void transHotFromRedisDB();

    Tag selectTag(Long categoryId, String tagName);

    void selectBycategoryIdOrTagId(Long categoryId, Long tagId);
}
