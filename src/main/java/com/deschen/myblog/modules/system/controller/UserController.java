package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.ShiroEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.core.utils.SortUtil;
import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.dto.ArticleWithBLOBsDto;
import com.deschen.myblog.modules.system.dto.CategoryDto;
import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.service.ArticleDtoService;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.ReviewDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description
 * @Since 1.0.0
 */
@Api(description = "用户模块")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private CategoryDtoService categoryDtoService;

    @Autowired
    private ArticleDtoService articleDtoService;

    @Autowired
    private ImageDtoService imageDtoService;

    @Autowired
    private ReviewDtoService reviewDtoService;
    @Autowired
    private SortUtil sortUtil;

    @Autowired
    private RedisUtil redisUtil;

    private final ResourceLoader resourceLoader;

    @Autowired
    public UserController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value="种类及标签", notes = "已测试")
    @GetMapping("/categoryDto")
    public List<CategoryDto> getCategoryDtos() {
        Integer state = BlogConstant.RECORD_VALID;
        String sort = sortUtil.getSort(null);
        List<Category> categories =
                categoryDtoService.selectCategory(state, sort);
        List<CategoryDto> categoryDtos = categories.stream().map(
                category -> {
                    CategoryDto categoryDto = new CategoryDto();
                    BeanUtils.copyProperties(category, categoryDto);
                    List<Tag> tags =
                            categoryDtoService.selectTag(category.getCategoryId(), state, sort);
                    categoryDto.setTags(tags);
                    return categoryDto;
                }
        ).collect(Collectors.toList());
        log.info("【获取种类和标签】种类和标签状态显示及排序，categoryDtos = {}, state = {}, sort = {}",
                categoryDtos, state, sort);
        return categoryDtos;
    }

    @ApiOperation(value="根据种类id，标签id或种类id和标签id对文章进行排序", notes = "已测试")
    @GetMapping("/articleDto")
    public ResultVO getArticleDtos(
            @ApiParam("categoryId")
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @ApiParam("tagId")
            @RequestParam(value = "tagId", required = false) Long tagId,
            @ApiParam("sort")
            @RequestParam(value = "sort", required = false) Integer sort
    ) {

        String categoryPrefix = RedisConstant.CATEGORY_PREFIX;
        String tagPrefix = RedisConstant.TAG_PREFIX;

        String sortName = sortUtil.getSort(sort);

        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);

        List<ArticleDto> articleDtos = new ArrayList<>();
        if (categoryId == null && tagId == null) {
            articleDtos = articleDtoService.selectArticleDto(states, sortName);
        } else if (categoryId != null) {
            categoryDtoService.selectBycategoryIdOrTagId(categoryId, null);
            redisInc(categoryId, categoryPrefix);
            articleDtos = articleDtoService.selectArticleDtoByCategoryId(categoryId, states, sortName);
            if (tagId != null) {
                categoryDtoService.selectBycategoryIdOrTagId(categoryId, tagId);
                redisInc(tagId, tagPrefix);
                articleDtos =
                        articleDtoService.selectArticleDtoByCategoryIdAndTagId(categoryId, tagId, states, sortName);
            }
        } else {
            categoryDtoService.selectBycategoryIdOrTagId(null, tagId);
            redisInc(tagId, tagPrefix);
            articleDtos = articleDtoService.selectArticleDtoByTagId(tagId, states, sortName);

        }
        ResultVO success = ResultVOUtil.success(articleDtos);
        return success;
    }

    private void redisInc(Long id, String prefix) {
        String str = String.format(prefix, id);
        if (redisUtil.get(str) == null) {
            redisUtil.set(str, 1);
        } else {
            redisUtil.incr(str, 1);
        }
    }



    @ApiOperation(value="文章id获取文章实体类", notes = "已测试")
    @GetMapping("/article/{articleId}")
    public ResultVO getArticleWithBLOBsDtoByArticleId(
            @ApiParam(value = "文章id", required = true)
            @PathVariable Long articleId,
            @ApiParam(value = "用户ip", required = true)
            @RequestParam("ip") String ip
    ) {
        articleDtoService.selectArticle(articleId);
        String visitPrefix = RedisConstant.VISIT_PREFIX;
        // 查看ip是否有记录
        ip = String.format(RedisConstant.VISITIP_PREFIX, ip.trim());
        if (redisUtil.get(ip) == null) {
            // 没有记录就存2个小时
            redisUtil.set(ip, 1, 10, TimeUnit.SECONDS);
            // 查看浏览次数
            redisInc(articleId, visitPrefix);
        }
        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);
        String sort = sortUtil.getSort(null);
        ArticleWithBLOBsDto articleWithBLOBsDto =
                articleDtoService.selectArticleWithBLOBsDtoByArticleId(articleId);
        if (articleWithBLOBsDto.getState() != BlogConstant.RECORD_VALID) {
            throw new GlobalException(ShiroEnum.NOT_PERMISSION);
        }
        ResultVO success = ResultVOUtil.success(articleWithBLOBsDto);
        return success;
    }

    @ApiOperation(value="图片显示", notes = "已测试")
    @GetMapping("/image/{imageId}")
    public ResponseEntity getImage(
            @PathVariable Long imageId
    ) {
        Image image =
                imageDtoService.selectImageByImageId(imageId);
        if (image.getState() == BlogConstant.RECORD_VOID) {
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + image.getImageUrl()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value="文章id点赞", notes = "已测试")
    @PostMapping("/thumbup/{articleId}")
    public ResultVO insertArticleThumbup(
            @ApiParam(value = "登入网页的ip地址", required = true)
            @RequestParam String ip,
            @ApiParam(value = "文章id", required = true)
            @PathVariable Long articleId
    ) {
        articleDtoService.selectArticle(articleId);
        // 查看点赞总数是否存在
        String thumbup = String.format(RedisConstant.THUMBUP_PREFIX, articleId);
        if (redisUtil.get(thumbup) == null) {
            redisUtil.set(thumbup, 0);
        }
        // 查看用户是否已点赞存在
        ip = String.format(RedisConstant.THUMBUPIP_PREFIX, ip.trim());
        if (redisUtil.get(ip) == null) {
            // 不存在，就代表点赞，并点赞总数加1
            redisUtil.set(ip, 1);
            redisUtil.incr(thumbup, 1);
        }else {
            // 存在，就代表取消点赞，点赞数减1
            redisUtil.decr(thumbup, 1);
            redisUtil.del(ip);
        }

        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "根据文章id获取评论", notes = "已测试")
    @GetMapping("/review/{articleId}")
    public ResultVO selectReviewDtos(
            @PathVariable Long articleId
    ) {
        List<ReviewDto> reviewDtos =
                reviewDtoService.selectReviewDto(articleId, BlogConstant.RECORD_VALID, null);
        ResultVO success = ResultVOUtil.success(reviewDtos);
        return success;
    }

}
