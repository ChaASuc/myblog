package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.core.utils.SortUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.entity.ArticleWithBLOBs;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.form.ArticleDtoForm;
import com.deschen.myblog.modules.system.form.ArticleForm;
import com.deschen.myblog.modules.system.form.TagForm;
import com.deschen.myblog.modules.system.service.ArticleDtoService;
import com.deschen.myblog.modules.system.service.ArticleModuleService;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@Api(description = "用户文章模块")
@RestController
@RequestMapping("/author/articleDto")
@Slf4j
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

    @ApiOperation(value = "根据状态吗，排序规则，页码获取文章标签", notes = "已测试")
    @GetMapping("")
    public ResultVO getArticleDtos(
            @RequestParam(required = false) List<Integer> states,
            @RequestParam(required = false) Integer sort,
            @RequestParam(required = false) Integer pageNum
    ) {
        List<Integer> stateList = new ArrayList<>();
        if (CollectionUtils.isEmpty(states) || states.size() == 0) {
            stateList.add(BlogConstant.HOT);
        }else {
            stateList = states;
        }
        String sortName;
        if (sort == null) {
            sortName = sortUtil.getSort(null);
        }else {
            sortName = sortUtil.getSort(sort);
        }
        if (pageNum == null) {
            pageNum = 0;
        }else {
            pageNum = pageNum - 1;
        }

        Integer pageSize = blogConfig.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDto> articleDtos = articleDtoService.selectArticleDto(stateList, sortName);
        PageInfo<ArticleDto> articleDtoPageInfo = new PageInfo<>(articleDtos);
        ResultVO success = ResultVOUtil.success(articleDtoPageInfo);
        return success;
    }

    @ApiOperation(value = "根据文章id获取文章", notes = "已测试")
    @GetMapping("/{articleId}")
    public ResultVO getArticleWithBLOBsDtoByArticleId(
            @PathVariable Long articleId
    ) {
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        String sort = sortUtil.getSort(null);
        ArticleWithBLOBsDto articleWithBLOBsDto =
                articleDtoService.selectArticleWithBLOBsDtoByArticleId(articleId);
        ResultVO success = ResultVOUtil.success(articleWithBLOBsDto);
        return success;
    }


    @ApiOperation(value = "创建文章", notes = "已测试")
    @PostMapping("")
    public ResultVO insertArticleWithBLOBsDto(
        @RequestBody ArticleForm articleForm
    ) {
        if (articleForm.getCategoryId() == null) {
            throw new GlobalException(BlogEnum.CATEGORY_NOT_EXIST);
        }
        if (CollectionUtils.isEmpty(articleForm.getTagForms())) {
            throw new GlobalException(BlogEnum.TAG_NOT_EXIST);
        }
        long articleId = new IdWorker().nextId();
        articleForm.setArticleId(articleId);
        List<Long> tagIds = articleForm.getTagForms().stream().map(
                tagForm -> tagForm.getTagId()
        ).collect(Collectors.toList());
        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        BeanUtils.copyProperties(articleForm, articleWithBLOBs);
        articleDtoService.insertArticleWithBLOBs(articleWithBLOBs);
        articleModuleService.insertArticleModule(articleId);
        articleDtoService.insertTagArticles(articleId, tagIds);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "更新文章",notes = "已测试")
    @PutMapping("")
    public ResultVO updateArticleWithBLOBsDto(
            @RequestBody ArticleForm articleForm
    ) {
        ResultVO success = ResultVOUtil.success();
        Long articleId = articleForm.getArticleId();
        List<TagForm> tagForms = articleForm.getTagForms();
        Long categoryId = articleForm.getCategoryId();
        // 更新文章
        ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
        BeanUtils.copyProperties(articleForm, articleWithBLOBs);
        articleDtoService.updateArticleWithBLOBs(articleWithBLOBs);
        // 种类不为空，标志着换种类，文章的标签关系都要删除
        if (categoryId != null) {
        List<Tag> tagList = articleDtoService.selectTagsByArticleId(articleId);
        List<Long> tagIds =
                tagList.stream().map(tag -> tag.getTagId()).collect(Collectors.toList());
        articleDtoService.deleteTagArticle(articleId, tagIds);
        // 种类更换，标签一定要有，否则报错
        if (CollectionUtils.isEmpty(tagForms)) {
            throw new GlobalException(BlogEnum.TAG_NOT_EXIST);
        }
        List<Long> tagIdList = articleForm.getTagForms().stream().map(
                tagForm -> tagForm.getTagId()
        ).collect(Collectors.toList());
            // 添加文章和标签的关系
            articleDtoService.insertTagArticles(articleId, tagIdList);
        }else {
            if (CollectionUtils.isEmpty(tagForms)) {
                return success;
            }
            // 种类不为空，标签不为空，tagId代表就标签id，name代表新的标签名
            List<Long> tagIdList = articleForm.getTagForms().stream().map(
                    tagForm -> tagForm.getTagId()
            ).collect(Collectors.toList());
            // 删除旧的标签和文章关系
            articleDtoService.deleteTagArticle(articleId, tagIdList);
            // 根据种类id和标签名获取新的标签集合
            List<Long> newTagList = articleForm.getTagForms().stream().map(
                    tagForm -> tagForm.getNewTagId()
            ).collect(Collectors.toList());
            articleDtoService.insertTagArticles(articleId, newTagList);
        }
        return success;
    }

    public static void main(String[] args) {
        ArticleDtoForm articleDtoForm = new ArticleDtoForm();
        List<Integer> states = new ArrayList<>();
        states.add(1);
        states.add(2);
        articleDtoForm.setStates(states);
        articleDtoForm.setPageNum(0);
        articleDtoForm.setSort(1);
        try {
            log.info("【文章模块】 articleDtoForm={}", JsonUtil.obj2string(articleDtoForm));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}

