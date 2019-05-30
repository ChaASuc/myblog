package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.entity.Review;
import com.deschen.myblog.modules.system.service.ReviewDtoService;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ReviewServiceImplTest extends TestUtil {

    @Autowired
    private ReviewDtoService reviewService;

    @Test
    public void insertReview() {
        Review review = new Review();
//        review.setArticleId(1133724118303219712L);
//        review.setReviewContent("父节点");
//        review.setUserId(2L);
//        reviewService.insertReview(review);
        review.setArticleId(1133724118303219712L);
        review.setReviewContent("子节点");
        review.setUserId(1L);
        review.setReviewParent(1133759500302434304L);
        reviewService.insertReview(review);
    }

    @Test
    public void updateReview() {
        // 使用回复失效
        Review review = new Review();
        review.setReviewId(1133762090872356864L);
        review.setState(0);
//        reviewService.updateReview(review);
//
//        // 使无回复的评论失效
//        review.setReviewId(1133759986669727744L);
//        reviewService.updateReview(review);

        // 使有回复的评论失效
        review.setReviewId(1133759500302434304L);
        reviewService.updateReview(review);
    }

    @Test
    public void selectReviewDto() throws JsonProcessingException {
        List<ReviewDto> reviewDtos =
                reviewService.selectReviewDto(1133724118303219712L, null, BlogConstant.FLAGADMIN);
        log.info("【评论模块】根据文章id获取评论 articleId={}，reviewDtos={}",
                1133724118303219712L, JsonUtil.obj2string(reviewDtos));
    }
}