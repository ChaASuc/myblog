package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.entity.*;
import com.deschen.myblog.modules.system.form.ArticleForm;
import com.deschen.myblog.modules.system.mapper.*;
import com.deschen.myblog.modules.system.service.ArticleDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class ArticleDtoServiceImpl implements ArticleDtoService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagArticleMapper tagArticleMapper;

    @Autowired
    private VisitMapper visitMapper;

    @Autowired
    private ThumbupMapper thumbupMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisUtil redisUtil;



    @Override

    /**
     *
     * @Param [categoryId, state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.ArticleDto>
     * @Author: deschen
     * @Date: 2019/5/23 10:57
     * @Description: 根据种类id, 文章状态码，排序规则获取文章dto实体类
     */
    public List<ArticleDto> selectArticleDtoByCategoryId(Long categoryId, List<Integer> states, String sort) {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdSortDesc(categoryId, states, sort);
        if (articleDtos.size() == 0) {
            return null;
        }
        articleDtoAddTags(articleDtos);
        return articleDtos;
    }

    @Override

    /**
     * @Param: [categoryId, tagId, state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.ArticleDto>
     * @Author: deschen
     * @Date: 2019/5/23 22:35
     * @Description: 根据种类id, 文章状态码，排序规则获取文章dto实体类
     */
    public List<ArticleDto> selectArticleDtoByCategoryIdAndTagId(Long categoryId, Long tagId, List<Integer> states, String sort) {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdAndTagIdSortDesc(categoryId, tagId, states, sort);
        articleDtoAddTags(articleDtos);
        return articleDtos;
    }

    @Override

    /**
     *
     * @Param [categoryIds, state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.ArticleDto>
     * @Author: deschen
     * @Date: 2019/5/23 10:42
     * @Description: 根据种类id集合, 文章状态码，排序规则获取文章dto实体类
     */
    public List<ArticleDto> selectArticleDtoByTagId(Long tagId, List<Integer> states, String sort) {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByTagIdSortDesc(tagId, states, sort);
        if (articleDtos.size() == 0) {
            return null;
        }
        articleDtoAddTags(articleDtos);
        return articleDtos;
    }

    @Override

    /**
     * @Param: [articleId]
     * @Return:com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto
     * @Author: deschen
     * @Date: 2019/5/23 11:36
     * @Description: 根据文章id获取文章（内容）实体类
     */
    public ArticleWithBLOBsDto selectArticleWithBLOBsDtoByArticleId(Long articleId) {
        ArticleWithBLOBsDto articleWithBLOBsDto =
                articleMapper.selectArticleWithBLOBsDtoByArticleId(articleId);
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<Tag> tags =
                tagArticleMapper.selectTagsByArticleId(articleId, states);
        articleWithBLOBsDto.setTags(tags);
        return articleWithBLOBsDto;
    }

    @Override

    /**
     * @Param: [state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.ArticleDto>
     * @Author: deschen
     * @Date: 2019/5/26 17:19
     * @Description: 根据文章状态码和排序规则获取文章（内容）实体类
     */
    public List<ArticleDto> selectArticleDto(List<Integer> states, String sort) {
        List<ArticleDto> articleDtos = articleMapper.selectArticleDtoSortDesc(states, sort);
        articleDtoAddTags(articleDtos);
        return articleDtos;
    }

    @Override

    /**
     * @Param: [articleForm]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 11:46
     * @Description: 添加文章（内容）
     */
    @Transactional
    public boolean insertArticleWithBLOBs(ArticleWithBLOBs articleWithBLOBs) {

        int success = articleMapper.insertSelective(articleWithBLOBs);
        if (success == 0) {
            throw new GlobalException(BlogEnum.ARTICLE_INSERT_ERROR);
        }

        return true;
    }

    @Override

    /**
     * @Param: [articleForm]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 17:12
     * @Description: 添加文章与标签集合到blog_tag_article表
     */
    @Transactional
    public void insertTagArticles(Long articleId, List<Long> tagIds) {
        // 创建标签和文章的联系
        List<TagArticle> tagArticles = tagIds.stream().map(
                tagId -> {
                    TagArticle tagArticle = new TagArticle();
                    long tagArticleId = new IdWorker().nextId();
                    tagArticle.setTagArticleId(tagArticleId);
                    tagArticle.setArticleId(articleId);
                    tagArticle.setTagId(tagId);
                    tagArticle.setState(BlogConstant.RECORD_VALID);
                    return tagArticle;
                }
        ).collect(Collectors.toList());

        int successTagArticle = tagArticleMapper.insertTagArticlesSelective(tagArticles);
        if (successTagArticle == 0) {
            throw new GlobalException(BlogEnum.TAG_ARTICLE_INSERT_ERROR);
        }
    }

    @Override

    /**
     * @Param: [articleForm]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 11:55
     * @Description: 更新文章信息，当state == BlogConstant.RECORD_VOID时，代表删除文章
     * @Modify:
     */
    @Transactional
    public boolean updateArticleWithBLOBs(ArticleWithBLOBs articleWithBLOBs) {

        int success =
                articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
        if (success == 1) {
            return true;
        }
        throw new GlobalException(BlogEnum.ARTICLE_UPDATE_ERROR);
    }

    @Override

    /**
     * @Param: [articleId, tagIds]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 17:27
     * @Description: 删除文章与其标签关系
     */
    @Transactional
    public boolean deleteTagArticle(
            Long articleId, List<Long> tagIds) {

        TagArticle tagArticle = new TagArticle();
        tagArticle.setState(BlogConstant.RECORD_VOID);
        TagArticleExample tagArticleExample = new TagArticleExample();
        // 文章id
        TagArticleExample.Criteria criteria = tagArticleExample.createCriteria().andArticleIdEqualTo(articleId);
        if (tagIds != null) {
            // 标签id集合
            criteria.andTagIdIn(tagIds);
        }
        int success =
                tagArticleMapper.updateByExampleSelective(tagArticle, tagArticleExample);
        if (success == 0) {
            throw new GlobalException(BlogEnum.TAG_ARTICLE_UPDATE_ERROR);
        }
        return true;
    }

    @Override

    /**
     * @Param: [articleId]
     * @Return:java.util.List<com.deschen.myblog.modules.system.entity.Tag>
     * @Author: deschen
     * @Date: 2019/5/28 19:11
     * @Description: 根据文章id获取标签（有效的）
     */
    public List<Tag> selectTagsByArticleId(Long articleId) {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        List<Tag> tags = tagArticleMapper.selectTagsByArticleId(articleId, states);
        return tags;
    }

    @Override

    /**
     * @Param: [articleId]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 14:02
     * @Description: 该文章是否存在
     */
    public void selectArticle(Long articleId) {
        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(articleId);
        if (articleWithBLOBs == null || articleWithBLOBs.getState() != BlogConstant.RECORD_VALID) {
            throw new GlobalException(BlogEnum.ARTICLE_NOT_EXIST);
        }
    }


    /**
     * @Param: articleDtos
     * @Return:
     * @Author: deschen
     * @Date: 2019/5/23 11:02
     * @Description: 根据文章dio实体类集合获取有效标签集合并组装
     */
    private void articleDtoAddTags(List<ArticleDto> articleDtos) {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        articleDtos.stream().forEach(
                articleDto -> {
                    List<Tag> tags =
                            tagArticleMapper.selectTagsByArticleId(articleDto.getArticleId(), states);
                    articleDto.setTags(tags);
                }
        );
    }
}
