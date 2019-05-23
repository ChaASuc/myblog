package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.entity.TagArticle;
import com.deschen.myblog.modules.system.entity.TagArticleExample;
import com.deschen.myblog.modules.system.form.ArticleForm;
import com.deschen.myblog.modules.system.mapper.ArticleMapper;
import com.deschen.myblog.modules.system.mapper.TagArticleMapper;
import com.deschen.myblog.modules.system.service.ArticleDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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



    @Override

    /**
     *
     * @Param [categoryId, state, sort]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.ArticleDto>
     * @Author: deschen
     * @Date: 2019/5/23 10:57
     * @Description: 根据种类id, 文章状态码，排序规则获取文章dto实体类
     */
    public List<ArticleDto> selectArticleDtoByCategoryId(Long categoryId, Integer state, String sort) {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdSortDesc(categoryId, state, sort);
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
    public List<ArticleDto> selectArticleDtoByCategoryIdAndTagId(Long categoryId, Long tagId, Integer state, String sort) {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdAndTagIdSortDesc(categoryId, tagId, state, sort);
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
    public List<ArticleDto> selectArticleDtoByCategoryIds(List<Long> categoryIds, Integer state, String sort) {
        List<ArticleDto> articleDtos =
                articleMapper.selectArticleDtoByCategoryIdsSortDesc(categoryIds, state, sort);
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
        List<Tag> tags =
                tagArticleMapper.selectTagsByArticleId(articleId, BlogConstant.RECORD_VALID);
        articleWithBLOBsDto.setTags(tags);
        return articleWithBLOBsDto;
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
    public boolean insertArticleWithBLOBs(ArticleForm articleForm) {
        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        BeanUtils.copyProperties(articleForm, articleWithBLOBs);
        long id = new IdWorker().nextId();
        articleWithBLOBs.setArticleId(id);

        int success = articleMapper.insertSelective(articleWithBLOBs);
        if (success != 0) {
            return true;
        }
        throw new GlobalException(BlogEnum.ARTICLE_INSERT_ERROR);
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
    public boolean insertTagArticle(ArticleForm articleForm) {
        List<TagArticle> tagArticles = articleForm.getTags().stream().map(
                tag -> {
                    TagArticle tagArticle = new TagArticle();
                    BeanUtils.copyProperties(tag, tagArticle);
                    long id = new IdWorker().nextId();
                    tagArticle.setTagArticleId(id);
                    return tagArticle;
                }
        ).collect(Collectors.toList());
        int success = tagArticleMapper.insertTagArticlesSelective(tagArticles);
        if (success == tagArticles.size()) {
            return true;
        }
        throw new GlobalException(BlogEnum.TAG_ARTICLE_INSERT_ERROR);
    }

    @Override

    /**
     * @Param: [articleForm]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 11:55
     * @Description: 更新文章信息，当state == BlogConstant.RECORD_VOID时，代表删除文章
     */
    @Transactional
    public boolean updateArticleWithBLOBs(ArticleForm articleForm) {
        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        BeanUtils.copyProperties(articleForm, articleWithBLOBs);

        int success =
                articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
        if (success == 1) {
            return true;
        }
        throw new GlobalException(BlogEnum.ARTICLE_UPDATE_ERROR);
    }

    @Override

    /**
     * @Param: [articleForm]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/23 17:27
     * @Description: 更新文章与其标签关系
     */
    @Transactional
    public boolean updateTagArticle(ArticleForm articleForm) {
        Long articleId = articleForm.getArticleId();
        articleForm.getTags().stream().forEach(
                tag -> {
                    TagArticle tagArticle = new TagArticle();
//                    BeanUtils.copyProperties(articleForm, tagArticle);
//                    tagArticle.setTagId(tag.getTagId());
                    Long tagId = tag.getTagId();
                    tagArticle.setState(tag.getState());
                    TagArticleExample tagArticleExample = new TagArticleExample();
                    tagArticleExample.createCriteria()
                            .andTagIdEqualTo(tagId).andArticleIdEqualTo(articleId);
                    int success = tagArticleMapper.updateByExampleSelective(tagArticle, tagArticleExample);
                    if (success != 1) {
                        throw new GlobalException(BlogEnum.TAG_ARTICLE_UPDATE_ERROR);
                    }
                }
        );
        return true;
    }


    /**
     * @Param: articleDtos
     * @Return:
     * @Author: deschen
     * @Date: 2019/5/23 11:02
     * @Description: 根据文章dio实体类集合获取有效标签集合并组装
     */
    private void articleDtoAddTags(List<ArticleDto> articleDtos) {
        articleDtos.stream().forEach(
                articleDto -> {
                    List<Tag> tags =
                            tagArticleMapper.selectTagsByArticleId(articleDto.getArticleId(), BlogConstant.RECORD_VALID);
                    articleDto.setTags(tags);
                }
        );
    }
}
