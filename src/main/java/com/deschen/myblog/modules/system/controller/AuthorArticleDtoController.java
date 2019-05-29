package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.core.utils.SortUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.form.ArticleForm;
import com.deschen.myblog.modules.system.service.ArticleDtoService;
import com.deschen.myblog.modules.system.service.ArticleModuleService;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/5/26
 * @Description
 * @Since 1.0.0
 */
@Api(description = "用户种类模块")
@RestController
@RequestMapping("/author/articleDto")
public class AuthorArticleDtoController {

    @Autowired
    private ArticleDtoService articleDtoService;

    @Autowired
    private CategoryDtoService categoryDtoService;

    @Autowired
    private ArticleModuleService articleModuleService;

    @Autowired
    private SortUtil sortUtil;

    @Autowired
    private BlogConfig blogConfig;

    @GetMapping("")
    public ResultVO getArticleDtos(
            @RequestParam(value = "state") List<Integer> states,
            @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam(value = "pageNum", required = false) Integer pageNum
    ) {
        if (CollectionUtils.isEmpty(states)) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR);
        }
        String sortName = sortUtil.getSort(sort);
        PageHelper.startPage(pageNum, blogConfig.getPageSize());
        List<ArticleDto> articleDtos = articleDtoService.selectArticleDto(states, sortName);
        PageInfo<ArticleDto> articleDtoPageInfo = new PageInfo<>(articleDtos);
        ResultVO success = ResultVOUtil.success(articleDtoPageInfo);
        return success;
    }

    @GetMapping("/{articleId}")
    public ResultVO getArticleWithBLOBsDtoByArticleId(
            @PathVariable Long articleId
    ) {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        String sort = sortUtil.getSort(null);
        ArticleWithBLOBsDto articleWithBLOBsDto =
                articleDtoService.selectArticleWithBLOBsDtoByArticleId(articleId);
        List<Tag> tags =
                categoryDtoService.selectTag(articleWithBLOBsDto.getCategoryId(), BlogConstant.RECORD_VALID, sort);
        articleWithBLOBsDto.setTags(tags);
        ResultVO success = ResultVOUtil.success(articleWithBLOBsDto);
        return success;
    }

    @PostMapping("")
    public ResultVO insertArticleWithBLOBsDto(
        @RequestBody ArticleForm articleForm
    ) {
        long articleId = new IdWorker().nextId();
        articleForm.setArticleId(articleId);
        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        BeanUtils.copyProperties(articleForm, articleWithBLOBs);
        articleDtoService.insertArticleWithBLOBs(articleWithBLOBs);
        articleModuleService.insertArticleModule(articleId);
        articleDtoService.insertTagArticles(articleId, articleForm.getTags());
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @PutMapping("")
    public ResultVO updateArticleWithBLOBsDto(
            @RequestBody ArticleForm articleForm
    ) {
        ResultVO success = ResultVOUtil.success();
        Long articleId = articleForm.getArticleId();
        Long categoryId = articleForm.getCategoryId();
        List<Tag> tags = articleForm.getTags();
        // 更新文章
        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        BeanUtils.copyProperties(articleForm, articleWithBLOBs);
        articleDtoService.updateArticleWithBLOBs(articleWithBLOBs);
        // 文章为删除状态
        if (articleForm.getState() == BlogConstant.RECORD_VOID) {
            articleDtoService.deleteTagArticle(articleId, null);
            return success;
        }
        // 种类不为空，标志着换种类，文章的标签关系都要删除
        if (categoryId != null) {
            List<Tag> tagList = articleDtoService.selectTagsByArticleId(articleId);
            List<Long> tagIds =
                    tagList.stream().map(tag -> tag.getTagId()).collect(Collectors.toList());
            articleDtoService.deleteTagArticle(articleId, tagIds);
            // 种类更换，标签一定要有，否则报错
            if (CollectionUtils.isEmpty(tags)) {
                throw new GlobalException(BlogEnum.TAG_NOT_EXIST);
            }
            // 添加文章和标签的关系
            articleDtoService.insertTagArticles(articleId, tags);
        }else {
            if (CollectionUtils.isEmpty(tags)) {
                return success;
            }
            // 种类不为空，标签不为空，tagId代表就标签id，name代表新的标签名
            List<Long> tagIds =
                    tags.stream().map(tag -> tag.getTagId()).collect(Collectors.toList());
            // 删除旧的标签和文章关系
            articleDtoService.deleteTagArticle(articleId, tagIds);
            // 根据种类id和标签名获取新的标签集合
            List<Tag> tagList = tags.stream().map(
                    tag -> {
                        Tag t = categoryDtoService.selectTag(categoryId, tag.getTagName());
                        return t;
                    }
            ).collect(Collectors.toList());
            articleDtoService.insertTagArticles(articleId, tagList);
        }
        return success;
    }

}
