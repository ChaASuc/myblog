package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.entity.Review;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description 评论层
 * @Since 1.0.0
 */
public interface ReviewDtoService {

    void insertReview(Review review);

    void updateReview(Review review);

    List<ReviewDto> selectReviewDto(Long articleId, Integer state, Integer flag, Integer sort);

    Review selectReviewByReviewId(Long reviewParentId);

}
