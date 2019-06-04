package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.enums.ShiroEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.*;
import com.deschen.myblog.modules.system.dto.*;
import com.deschen.myblog.modules.system.entity.*;
import com.deschen.myblog.modules.system.form.GuestBookForm;
import com.deschen.myblog.modules.system.form.ReviewForm;
import com.deschen.myblog.modules.system.service.*;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private UserDtoService userDtoService;

    @Autowired
    private GuestBookDtoService guestBookDtoService;

    @Autowired
    private BlogConfig blogConfig;

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
            @RequestParam(value = "sort", required = false) Integer sort,
            @ApiParam("pageNum")
            @RequestParam(value = "pageNum", required = false) Integer pageNum
    ) {

        String categoryPrefix = RedisConstant.CATEGORY_PREFIX;
        String tagPrefix = RedisConstant.TAG_PREFIX;

        String sortName = sortUtil.getSort(sort);

        List<Integer> states = new ArrayList<>();
        states.add(BlogConstant.RECORD_VALID);

        if (pageNum == null) {
            pageNum = 1;
        }

        List<ArticleDto> articleDtos = new ArrayList<>();
        if (categoryId == null && tagId == null) {
            PageHelper.startPage(pageNum, blogConfig.getPageSize());
            articleDtos = articleDtoService.selectArticleDto(states, sortName);
        } else if (categoryId != null) {
            if (tagId != null) {
                categoryDtoService.selectBycategoryIdOrTagId(categoryId, tagId);
                redisInc(tagId, tagPrefix);
                PageHelper.startPage(pageNum, blogConfig.getPageSize());
                articleDtos =
                        articleDtoService.selectArticleDtoByCategoryIdAndTagId(categoryId, tagId, states, sortName);
            }else {
                categoryDtoService.selectBycategoryIdOrTagId(categoryId, null);
                redisInc(categoryId, categoryPrefix);
                PageHelper.startPage(pageNum, blogConfig.getPageSize());
                articleDtos = articleDtoService.selectArticleDtoByCategoryId(categoryId, states, sortName);
            }
        } else {
            categoryDtoService.selectBycategoryIdOrTagId(null, tagId);
            redisInc(tagId, tagPrefix);
            PageHelper.startPage(pageNum, blogConfig.getPageSize());
            articleDtos = articleDtoService.selectArticleDtoByTagId(tagId, states, sortName);

        }
        PageInfo<ArticleDto> articleDtoPageInfo = new PageInfo<>(articleDtos);
        ResultVO success = ResultVOUtil.success(articleDtoPageInfo);
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
            @PathVariable Long articleId,
            @RequestParam(required = false) Integer sort
    ) {

        if (sort == null) {
            sort = 0;  // 最新
        } else if (sort < 0 || sort > 1) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "sort字段小于0，大于1");
        }
        List<ReviewDto> reviewDtos =
                reviewDtoService.selectReviewDto(articleId, BlogConstant.RECORD_VALID, null, sort);
        ResultVO success = ResultVOUtil.success(reviewDtos);
        return success;
    }


    @ApiOperation(value = "添加评论或回复", notes = "已测试")
    @PostMapping("/review")
    public ResultVO insertReview(
            @Valid @RequestBody ReviewForm reviewForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.info("【添加评论】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        if (reviewForm.getEmail() != null) {
            String email = reviewForm.getEmail().trim();
            if (!email.equals("") && !EmailUtil.checkEmaile(email)) {
                log.info("【添加评论】邮箱校验失败，email = {}", email);
                throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                        "邮箱格式错误");
            }
        }

        Long articleId = reviewForm.getArticleId();
        articleDtoService.selectArticle(articleId);

        // 创建用户
        User user = new User();
        BeanUtils.copyProperties(reviewForm, user);
        // 获取用户头像的随机图片
        Image image =
                imageDtoService.selectRandomImage(null);
        user.setImageId(image.getImageId());
        Long userId = userDtoService.insertUser(user);

        Review review = new Review();
        BeanUtils.copyProperties(reviewForm, review);
        long reviewId = new IdWorker().nextId();
        review.setReviewId(reviewId);
        review.setUserId(userId);
        // 是回复还是评论
        Long reviewParentId = reviewForm.getReviewParent();
        if (reviewParentId != null) {
            // 回复的话，查找回复的那条的评论区id
            Review reviewParent =
                    reviewDtoService.selectReviewByReviewId(reviewParentId);
            review.setReviewAreaId(reviewParent.getReviewAreaId());
        }else {
            // 评论的话，创建评论区id
            review.setReviewAreaId(reviewId);
        }
        reviewDtoService.insertReview(review);

        ResultVO success = ResultVOUtil.success();

        return success;
    }

    @ApiOperation(value = "获取用户信息", notes = "已测试")
    @GetMapping("/authorInfo")
    public ResultVO selectUserByAuthorId(
    ) {
        // 作者用户id
        Long authorId = BlogConstant.AUTHOR_ID;
        UserDto userDto =
                userDtoService.selectUserDto(authorId);
        ResultVO success = ResultVOUtil.success(userDto);
        return success;
    }

    @ApiOperation(value = "获取留言", notes = "已测试")
    @GetMapping("/guestBook")
    public ResultVO selectGuestBookDto() {
        List<GuestBook> guestBooks =
                guestBookDtoService.selectGuestBookDto(BlogConstant.RECORD_VALID, BlogConstant.DESC);
        List<GuestBookDto> guestBookDtos = guestBooks.stream().map(
                guestBook -> {
                    GuestBookDto guestBookDto = new GuestBookDto();
                    BeanUtils.copyProperties(guestBook, guestBookDto);
                    UserDto userDto =
                            userDtoService.selectUserDto(guestBook.getUserId());
                    guestBookDto.setUserName(userDto.getUserName());
                    guestBookDto.setImageUrl(userDto.getImageUrl());
                    guestBookDto.setEmail(userDto.getEmail());
                    return guestBookDto;
                }
        ).collect(Collectors.toList());
        ResultVO success = ResultVOUtil.success(guestBookDtos);
        return success;
    }

    @ApiOperation(value = "创建留言", notes = "已测试")
    @PostMapping("/guestBook")
    public ResultVO insertGuestBookDto(
            @Valid @RequestBody GuestBookForm guestBookForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.info("【添加留言】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        String email = guestBookForm.getEmail();
        if (email != null) {
            email = email.trim();
            if (email.equals("") || !EmailUtil.checkEmaile(email)) {
                log.info("【添加留言】邮箱校验失败，email = {}", email);
                throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                        "邮箱格式错误");
            }
        }
        String userName = guestBookForm.getUserName();
        String guestbookContent = guestBookForm.getGuestbookContent();
        String guestbookUrl = guestBookForm.getGuestbookUrl();

        User user = new User();
        user.setUserName(userName);
        user.setSalt(userName);
        user.setEmail(user.getEmail());
        Image image =
                imageDtoService.selectRandomImage(null);
        Long imageId = image.getImageId();
        user.setImageId(imageId);
        Long userId = userDtoService.insertUser(user);

        GuestBook guestBook = new GuestBook();
        BeanUtils.copyProperties(guestBookForm, guestBook);
        guestBook.setUserId(userId);

        Long guestBookId = guestBookDtoService.insertGuestBook(guestBook);

        GuestBook guestBook1 =
                guestBookDtoService.selectGuestBookDtoByGuestBookId(guestBookId);
        GuestBookDto guestBookDto = new GuestBookDto();
        BeanUtils.copyProperties(guestBook1, guestBookDto);
        guestBookDto.setEmail(email);
        guestBookDto.setImageUrl(BlogConstant.IMAGE_USER_URL + imageId);
        guestBookDto.setUserName(userName);
        ResultVO success = ResultVOUtil.success(guestBookDto);
        return success;
    }
}
