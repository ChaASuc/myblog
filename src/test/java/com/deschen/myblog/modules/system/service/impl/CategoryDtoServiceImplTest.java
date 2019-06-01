//package com.deschen.myblog.modules.system.service.impl;
//
//import com.deschen.myblog.core.utils.IdWorker;
//import com.deschen.myblog.core.utils.JsonUtil;
//import com.deschen.myblog.modules.system.dto.CategoryDto;
//import com.deschen.myblog.modules.system.entity.Category;
//import com.deschen.myblog.modules.system.entity.Tag;
//import com.deschen.myblog.modules.system.service.CategoryDtoService;
//import com.deschen.myblog.utils.TestUtil;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.Assert.*;
//
//@Slf4j
//public class CategoryDtoServiceImplTest extends TestUtil {
//
//    @Autowired
//    private CategoryDtoService categoryDtoService;
//
//    @Test
//    public void insertTags() {
//        List<Tag> tags = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            long id = new IdWorker().nextId();
//            Tag tag = new Tag();
//            tag.setTagId(id);
//            tag.setTagName("" + i);
//            tag.setCategoryId(1L);
//            tag.setHot(i);
//            tags.add(tag);
//        }
//        boolean b = categoryDtoService.insertTags(tags);
//        log.info("根据");
//    }
//
//    @Test
//    public void insertCategories() {
//        List<Category> categories = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            long id = new IdWorker().nextId();
//            Category category = new Category();
//            category.setCategoryId(id);
//            category.setCategoryName("" + i);
//            category.setHot(i);
//            categories.add(category);
//        }
//        boolean b = categoryDtoService.insertCategories(categories);
//        Assert.assertEquals(true, b);
//    }
//
//    @Test
//    public void selectCategoryDto() {
//        List<Category> categories = categoryDtoService.selectCategory(null, null);
//        List<CategoryDto> categoryDtos = categories.stream().map(
//                category -> {
//                    CategoryDto categoryDto = new CategoryDto();
//                    BeanUtils.copyProperties(category, categoryDto);
//                    List<Tag> tags =
//                            categoryDtoService.selectTag(category.getCategoryId(), null, null);
//                    categoryDto.setTags(tags);
//                    return categoryDto;
//                }
//        ).collect(Collectors.toList());
//        try {
//            log.info("【种类及标签的状态显示及排序】categoryDtos = {}", JsonUtil.obj2string(categoryDtos));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}