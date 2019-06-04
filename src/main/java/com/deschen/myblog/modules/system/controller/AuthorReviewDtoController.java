package com.deschen.myblog.modules.system.controller;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.Review;
import com.deschen.myblog.modules.system.service.ImageDtoService;
import com.deschen.myblog.modules.system.service.ReviewDtoService;
import com.deschen.myblog.modules.system.service.UserDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.deschen.myblog.modules.system.vo.ReviewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    private UserDtoService userDtoService;


    @ApiOperation(value = "添加评论或回复", notes = "已测试")
    @PostMapping("")
    public ResultVO insertReview(
            @RequestBody Review review,
            BindingResult bindingResult
            ) {
        if (bindingResult.hasErrors()) {
            log.info("【添加评论】 参数错误");
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        long reviewId = new IdWorker().nextId();
        review.setReviewId(reviewId);
        // 是回复还是评论
        Long reviewParentId = review.getReviewParent();
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

        Review review1 =
                reviewDtoService.selectReviewByReviewId(reviewId);
        UserDto userDto
                = userDtoService.selectUserDto(review1.getUserId());
        ReviewVO reviewVO = new ReviewVO();
        BeanUtils.copyProperties(review1, reviewVO);
        reviewVO.setUserId(userDto.getUserId());
        reviewVO.setUserName(userDto.getUserName());
        reviewVO.setImageUrl(userDto.getImageUrl());

        ResultVO success = ResultVOUtil.success(reviewVO);

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
            @RequestParam(required = false) Integer state,
            @RequestParam(required = false) Integer sort
    ) {
        if (sort == null) {
            sort = 0;  // 最新
        } else if (sort < 0 || sort > 1) {
            throw new GlobalException(BlogEnum.PARROR_EMPTY_ERROR.getCode(),
                    "sort字段小于0，大于1");
        }
        List<ReviewDto> reviewDtos =
                reviewDtoService.selectReviewDto(articleId, state, BlogConstant.FLAGADMIN, sort);
        ResultVO success = ResultVOUtil.success(reviewDtos);
        return success;
    }
}
