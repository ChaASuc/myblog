package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.core.utils.SortUtil;
import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.entity.Article;
import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.service.ArticleDtoService;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/author/categoryDto")
public class AuthorCategoryDtoController {

    @Autowired
    private CategoryDtoService categoryDtoService;

    @Autowired
    private ArticleDtoService articleDtoService;

    @Autowired
    private SortUtil sortUtil;


    @ApiOperation(value = "获取所有的种类和标签",notes = "已测试")
    @GetMapping("")
    public ResultVO getCategoryDtos(
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "sort", required = false) Integer sort
    ) {
        String sortName = sortUtil.getSort(sort);
        List<CategoryDto> categoryDtos = categoryDtoService.selectCategoryDto(state, sortName);
        ResultVO success = ResultVOUtil.success(categoryDtos);
        return success;
    }


    @ApiOperation(value = "获取状态的种类",notes = "已测试")
    @GetMapping("/category")
    public ResultVO getCategories(
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "sort", required = false) Integer sort
    ) {
        String sortName = sortUtil.getSort(sort);
        List<Category> categories = categoryDtoService.selectCategory(state, sortName);
//        List<CategoryDto> categoryDtos = categoryDtoService.selectCategoryDto(state, sortName);
        ResultVO success = ResultVOUtil.success(categories);
        return success;
    }


    @ApiOperation(value = "获取状态的种类下的标签",notes = "已测试")
    @GetMapping("/tag")
    public ResultVO getTags(
            @RequestParam(value = "categoryId") Long categoryId,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "sort", required = false) Integer sort
    ) {
        String sortName = sortUtil.getSort(sort);
        List<Tag> tags = categoryDtoService.selectTag(categoryId, state, sortName);
        ResultVO success = ResultVOUtil.success(tags);
        return success;
    }

    @ApiOperation(value = "批量创建种类",notes = "已测试")
    @PostMapping("/categories")
    public ResultVO insertCategories(
            @RequestBody  List<Category> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR);
        }
        categoryDtoService.insertCategories(categories);
        return ResultVOUtil.success();
    }

    @ApiOperation(value = "批量创建种类下的标签",notes = "已测试")
    @PostMapping("/tags")
    public ResultVO insertTags(
            @RequestBody List<Tag> tags
    ) {
        if (CollectionUtils.isEmpty(tags)) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR);
        }
        categoryDtoService.insertTags(tags);
        return ResultVOUtil.success();
    }

    @ApiOperation(value = "批量更新种类及其标签",notes = "已测试")
    @PutMapping
    @Transactional
    public ResultVO updateCategoryDtos(
            @RequestBody List<CategoryDto> categoryDtos
    ) {
        if (CollectionUtils.isEmpty(categoryDtos)) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR);
        }
        categoryDtoService.updateCategoryDtos(categoryDtos);
        return ResultVOUtil.success();
    }
}
