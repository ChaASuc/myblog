package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.mapper.CategoryMapper;
import com.deschen.myblog.modules.system.mapper.TagMapper;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description 种类及标签业务层实现类
 * @Since 1.0.0
 */
@Service
@Slf4j
public class CategoryDtoServiceImpl implements CategoryDtoService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override

    /**
     * @Param: [tags]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 15:17
     * @Description: 批量插入标签实体类（每个实体类一样格式）
     */
    @Transactional
    public boolean insertTags(List<Tag> tags) {
        int success = tagMapper.insertTagsSelective(tags);
        if (success == tags.size()) {
            return true;
        }
        throw new GlobalException(BlogEnum.TAG_INSERT_ERROR);
    }

    @Override

    /**
     * @Param: [categories]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 22:25
     * @Description: 批量插入种类（格式一致）
     */
    @Transactional
    public boolean insertCategories(List<Category> categories) {
        int success = categoryMapper.insertCategorysSelective(categories);
        if (success == categories.size()) {
            return true;
        }
        throw new GlobalException(BlogEnum.CATEGORY_INSERT_ERROR);
    }

    @Override

    /**
     * @Param: [categories]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 22:27
     * @Description: 批量更新种类 ,state == BlogConstant.VOID代表删除操作
     *               与article表关联
     */
//    @Transactional
    public boolean updateCategories(List<Category> categories) {
        categories.stream().forEach(
                category -> {
                    int success = categoryMapper.updateByPrimaryKeySelective(category);
                    if (success != 1) {
                        throw new GlobalException(BlogEnum.CATEGORY_UPDATE_ERROR);
                    }
                }
        );
        return true;
    }

    @Override

    /**
     * @Param: [tags]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 23:15
     * @Description: 批量更新标签 ,state == BlogConstant.VOID代表删除操作
     *      *               与tag_article表关联
     */
    public boolean updateTags(List<Tag> tags) {
        tags.stream().forEach(
                tag -> {
                    int success = tagMapper.updateByPrimaryKeySelective(tag);
                    if (success != 1) {
                        throw new GlobalException(BlogEnum.TAG_UPDATE_ERROR);
                    }
                }
        );
        return false;
    }
}
