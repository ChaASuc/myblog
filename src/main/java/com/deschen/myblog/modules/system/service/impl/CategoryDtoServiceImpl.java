package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.entity.*;
import com.deschen.myblog.modules.system.mapper.ArticleMapper;
import com.deschen.myblog.modules.system.mapper.CategoryMapper;
import com.deschen.myblog.modules.system.mapper.TagArticleMapper;
import com.deschen.myblog.modules.system.mapper.TagMapper;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override

    /**
     * @Param: [tags]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 15:17
     * @Description: 批量插入标签实体类（每个实体类一样格式）
     */
    @Transactional
    public List<Long> insertTags(List<Tag> tags) {
        List<Long> longs = new ArrayList<>();
        tags.stream().forEach(
                tag -> {
                    long id = new IdWorker().nextId();
                    tag.setTagId(id);
                    longs.add(id);
                }
        );
        int success = tagMapper.insertTagsSelective(tags);
        if (success != tags.size()) {
            log.info("【批量添加标签】 失败，tagSize = {}, success = {}",
                    tags.size(), success);
            throw new GlobalException(BlogEnum.TAG_INSERT_ERROR);
        }
        return longs;
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
    public void insertCategories(List<Category> categories) {
        categories.stream().forEach(
                category -> {
                    long id = new IdWorker().nextId();
                    category.setCategoryId(id);
                }
        );
        int success = categoryMapper.insertCategorysSelective(categories);
        if (success != categories.size()) {
            log.info("【批量添加种类】 失败，categorySize = {}, success = {}",
                    categories.size(), success);
            throw new GlobalException(BlogEnum.CATEGORY_INSERT_ERROR);
        }
    }

    @Override

    /**
     * @Param: [categoryDtos]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 22:27
     * @Description: state == BlogConstant.VOID代表删除操作
     *               与article表关联
     * @Modify: 2019/5/26 批量更新种类及标签
     */
    @Transactional
    public void updateCategoryDtos(List<CategoryDto> categoryDtos) {
        // 1更新种类
        categoryDtos.stream().filter(
                categoryDto -> categoryDto.getCategoryId() != null
        ).forEach(
                categoryDto -> {
                    Category category = new Category();
                    BeanUtils.copyProperties(categoryDto, category);
                    int success = categoryMapper.updateByPrimaryKeySelective(category);
                    if (success != 1) {
                        log.info("【更新种类及标签】 种类更新失败，success = {}, categoryId = {}",
                                success, category.getCategoryId());
                        throw new GlobalException(BlogEnum.CATEGORY_UPDATE_ERROR);
                    }

                    if (categoryDto.getState() == BlogConstant.RECORD_VOID) {
                        // 种类状态改变影响标签状态
                        Tag tag = new Tag();
                        tag.setState(BlogConstant.RECORD_VOID);
                        TagExample tagExample = new TagExample();
                        tagExample.createCriteria().andCategoryIdEqualTo(categoryDto.getCategoryId());
                        tagMapper.updateByExampleSelective(tag, tagExample);

                        // 种类状态影响文章状态
                        ArticleWithBLOBs article = new ArticleWithBLOBs();
                        article.setState(BlogConstant.RECORD_VOID);
                        ArticleExample articleExample = new ArticleExample();
                        articleExample.createCriteria().andCategoryIdEqualTo(category.getCategoryId());
                        articleMapper.updateByExampleSelective(article, articleExample);
                    }
        });

        // 2 更新标签
        categoryDtos.stream().filter(
                categoryDto -> !CollectionUtils.isEmpty(categoryDto.getTags())
        ).forEach(
                categoryDto -> {
                    List<Tag> tags = categoryDto.getTags();
                    tags.stream().forEach(
                            tag -> {
                                int success = tagMapper.updateByPrimaryKeySelective(tag);
                                if (success != 1) {
                                    log.info("【更新种类及标签】 种类标签失败，success = {}, tagId = {}",
                                            success, tag.getTagId());
                                    throw new GlobalException(BlogEnum.TAG_UPDATE_ERROR);
                                }
                                // 标签的状态改变影响文章的状态
                                if (tag.getState() == BlogConstant.RECORD_VOID) {
                                    TagArticle tagArticle = new TagArticle();
                                    tagArticle.setState(BlogConstant.RECORD_VOID);
                                    TagArticleExample tagArticleExample = new TagArticleExample();
                                    tagArticleExample.createCriteria().andTagIdEqualTo(tag.getTagId());
                                    tagArticleMapper.updateByExample(tagArticle, tagArticleExample);
                                }
                            }
                    );
                }
        );
    }

    @Override

    /**
     * @Param: [state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.CategoryDto>
     * @Author: deschen
     * @Date: 2019/5/26 22:01
     * @Description: 种类标签状态显示及排序
     */
    public List<CategoryDto> selectCategoryDto(Integer state, String sort) {
        List<Category> categories = selectCategory(state, sort);
        List<CategoryDto> categoryDtos = categories.stream().map(
                category -> {
                    CategoryDto categoryDto = new CategoryDto();
                    BeanUtils.copyProperties(category, categoryDto);
                    List<Tag> tags =
                            selectTag(category.getCategoryId(), state, sort);
                    categoryDto.setTags(tags);
                    return categoryDto;
                }
        ).collect(Collectors.toList());
        log.info("【获取种类和标签】种类和标签状态显示及排序，categoryDtos = {}, state = {}, sort = {}",
                categoryDtos, state, sort);
        return categoryDtos;
    }

    @Override

    /**
     * @Param: [state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.entity.Category>
     * @Author: deschen
     * @Date: 2019/5/26 21:15
     * @Description: 种类状态显示及排序
     */
    public List<Category> selectCategory(Integer state, String sort) {
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria categoryExampleCriteria = categoryExample.createCriteria();

        // 排序规则
        if (sort.equals("hot")) {
            categoryExample.setOrderByClause("HOT DESC");
        }else if (sort.equals("newest")){
            categoryExample.setOrderByClause("UPDATETIME  DESC");
        }else {
            throw new GlobalException(BlogEnum.SORT_ERROR);
        }

        // 状态吗规则
        if (state != null) {
            categoryExampleCriteria.andStateEqualTo(state);
        }

        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        log.info("【获取种类和标签】种类状态显示及排序，categories = {}, state = {}, sort = {}",
                categories, state, sort);
        return categories;
    }

    @Override

    /**
     * @Param: [state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.entity.Tag>
     * @Author: deschen
     * @Date: 2019/5/26 21:17
     * @Description: 标签状态显示及排序
     */
    public List<Tag> selectTag(Long categoryId, Integer state, String sort) {
        TagExample tagExample = new TagExample();
        TagExample.Criteria tagExampleCriteria = tagExample.createCriteria();
        // 排序规则
        if (sort.equals("hot")) {
            tagExample.setOrderByClause("HOT DESC");
        }else if (sort.equals("newest")){
            tagExample.setOrderByClause("UPDATETIME  DESC");
        }else {
            throw new GlobalException(BlogEnum.SORT_ERROR);
        }
        // 状态吗规则
        if (state != null) {
            tagExampleCriteria.andStateEqualTo(state);
        }
        // 判断种类id是否存在
        if (categoryId != null) {
            tagExampleCriteria.andCategoryIdEqualTo(categoryId);
        }
        List<Tag> tags = tagMapper.selectByExample(tagExample);
        log.info("【获取种类和标签】标签状态显示及排序，tags = {}, state = {}, sort = {}",
                tags, state, sort);
        return tags;
    }

    @Override

    /**
     * @Param: []
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/28 19:39
     * @Description: reids点击种类和标签的次数更新相应实体类的hot
     */
    @Transactional
    public void transHotFromRedisDB() {
        String categoryPrefix = String.format(RedisConstant.CATEGORY_PREFIX, "*");
        String tagPrefix = String.format(RedisConstant.TAG_PREFIX, "*");

        Set<String> ckeys = redisUtil.keys(categoryPrefix);
        Set<String> tkeys = redisUtil.keys(tagPrefix);

        ckeys.stream().forEach(
                ckey -> {
                    // 种类id
                    Long categoryId = Long.valueOf(ckey.substring(ckey.lastIndexOf("_") + 1));
                    // redis存的种类热度
                    Integer hot = (Integer) redisUtil.get(ckey);
                    // 获取种类
                    Category category = categoryMapper.selectByPrimaryKey(categoryId);
                    if (category != null) {  // 不存在则跳过，避免不必要报错
                        if (category.getHot() > hot) {
                            // 代表redis错误删掉种类热度缓存
                            redisUtil.set(ckey, category.getHot());
                        }else if(category.getHot() < hot){
                            category.setHot(hot);
                            int success = categoryMapper.updateByPrimaryKey(category);
                            if (success == 0) {
                                throw new GlobalException(BlogEnum.CATEGORY_UPDATE_ERROR);
                            }
                        }// 相同跳过，提高效率
                    }

                }
        );

        tkeys.stream().forEach(
                tkey -> {
                    // 获取标签id
                    Long tagId = Long.valueOf(tkey.substring(tkey.lastIndexOf("_") + 1));
                    Integer hot = (Integer) redisUtil.get(tkey);
                    // 获取标签
                    Tag tag = tagMapper.selectByPrimaryKey(tagId);
                    if (tag != null) {  // 不存在跳过，避免报错，提高效率
                        if (tag.getHot() > hot) {
                            // 标签缓存误删
                            redisUtil.set(tkey, tag.getHot());
                        } else if (tag.getHot() < hot){
                            tag.setHot(hot);
                            int success =
                                    tagMapper.updateByPrimaryKey(tag);
                            if (success == 0) {
                                throw new GlobalException(BlogEnum.TAG_UPDATE_ERROR);
                            }
                        }// 相同跳过，提高效率
                    }

                }
        );

    }

    @Override

    /**
     * @Param: [categoryId, tagName]
     * @Return:com.deschen.myblog.modules.system.entity.Tag
     * @Author: deschen
     * @Date: 2019/5/29 0:19
     * @Description: 根据种类id和标签名获取标签实体类
     */
    public Tag selectTag(Long categoryId, String tagName) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria()
                .andCategoryIdEqualTo(categoryId)
                .andTagNameEqualTo(tagName);
        List<Tag> tags =
                tagMapper.selectByExample(tagExample);
        if (CollectionUtils.isEmpty(tags) || tags.size() > 1) {
            throw new GlobalException(BlogEnum.TAG_NOT_EXIST);
        }
        return tags.get(0);
    }

    @Override

    /**
     * @Param: [categoryId, tagId]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/29 13:51
     * @Description: 查看种类，标签id及组合是否存在
     */
    public void selectBycategoryIdOrTagId(Long categoryId, Long tagId) {
        if (categoryId != null) {
            Category category =
                    categoryMapper.selectByPrimaryKey(categoryId);
            if (category == null || category.getState() == BlogConstant.RECORD_VOID) {
                throw new GlobalException(BlogEnum.CATEGORY_NOT_EXIST);
            }
            if (tagId != null) {
                TagExample tagExample = new TagExample();
                tagExample.createCriteria().andTagIdEqualTo(tagId)
                        .andCategoryIdEqualTo(categoryId);
                List<Tag> tags = tagMapper.selectByExample(tagExample);
                if (tags.size() == 0 || tags.get(0).getState() == BlogConstant.RECORD_VOID) {
                    throw new GlobalException(BlogEnum.CATEGORY_TAG_NOT_EXIST);
                }
            }

        }
        if (tagId != null) {
            Tag tag = tagMapper.selectByPrimaryKey(tagId);
            if (tag == null || tag.getState() == BlogConstant.RECORD_VOID) {
                throw new GlobalException(BlogEnum.TAG_NOT_EXIST);
            }
        }

    }

    public static void main(String[] args) {
        Long aLong = Long.valueOf("1131486952118300672");
        System.out.println(aLong);
    }

}
