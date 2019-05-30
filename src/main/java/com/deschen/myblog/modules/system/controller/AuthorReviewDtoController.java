package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.EmailUtil;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.entity.Image;
import com.deschen.myblog.modules.system.entity.Review;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.form.ReviewForm;
import com.deschen.myblog.modules.system.mapper.UserMapper;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.ReviewDtoService;
import com.deschen.myblog.modules.system.service.UserService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description
 * @Since 1.0.0
 */
@Data
@RestController
@Slf4j
@Api(description = "评论模块")
@RequestMapping("/author/review")
public class AuthorReviewDtoController {

    @Autowired
    private ReviewDtoService reviewDtoService;

    @Autowired
    private ImageDtoService imageDtoService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加评论或回复", notes = "已测试")
    @PostMapping("")
    public ResultVO insertReview(
            @Valid @RequestBody ReviewForm reviewForm,
            BindingResult bindingResult
            ) {
        if (bindingResult.hasErrors()) {
            log.info("【添加评论】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        if (reviewForm.getEmail() != null && !EmailUtil.checkEmaile(reviewForm.getEmail())) {
            log.info("【添加评论】邮箱校验失败，email = {}", reviewForm.getEmail());
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "邮箱格式错误");
        }

        //文章id或评论父节点id不能同时为空
        if (reviewForm.getArticleId() == null && reviewForm.getReviewParent() == null) {
            if (reviewForm.getArticleId() == null) {
                log.info("【添加评论】文章id为空");
                throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                        "文章id不为空");
            }
            if (reviewForm.getReviewParent() == null) {
                log.info("【添加评论】回复该评论的id为空");
                throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                        "回复该评论的id不为空");
            }
        }
        // 创建用户
        User user = new User();
        BeanUtils.copyProperties(reviewForm, user);
        long userId = new IdWorker().nextId();
        user.setUserId(userId);
        // 获取用户头像的随机图片
        Image image =
                imageDtoService.selectRandomImage(null);
        user.setImageId(image.getImageId());
        userService.insertUser(user);

        Review review = new Review();
        BeanUtils.copyProperties(reviewForm, review);
        long reviewId = new IdWorker().nextId();
        review.setReviewId(reviewId);
        review.setUserId(userId);
        reviewDtoService.insertReview(review);

        ResultVO success = ResultVOUtil.success();

        return success;
    }

    @ApiOperation(value = "更新评论", notes = "已测试")
    @PutMapping("/{reviewId}")
    public ResultVO updateReview(
            @PathVariable Long reviewId,
            @RequestParam Integer state
    ) {
        Review review = new Review();
        review.setReviewId(reviewId);
        review.setState(state);
        reviewDtoService.updateReview(review);
        ResultVO success = ResultVOUtil.success();
        return success;
    }

    @ApiOperation(value = "根据文章id获取评论", notes = "已测试")
    @GetMapping("/{articleId}")
    public ResultVO selectReviewDtos(
            @PathVariable Long articleId,
            @RequestParam(required = false) Integer state
    ) {
        List<ReviewDto> reviewDtos =
                reviewDtoService.selectReviewDto(articleId, state, BlogConstant.FLAGADMIN);
        ResultVO success = ResultVOUtil.success(reviewDtos);
        return success;
    }
}
