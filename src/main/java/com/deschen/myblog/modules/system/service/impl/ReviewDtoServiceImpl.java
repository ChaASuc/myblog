package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.entity.Review;
import com.deschen.myblog.modules.system.entity.ReviewExample;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.mapper.ReviewMapper;
import com.deschen.myblog.modules.system.mapper.UserMapper;
import com.deschen.myblog.modules.system.service.ReviewDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class ReviewDtoServiceImpl implements ReviewDtoService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserMapper userMapper;

    @Override

    /**
     * @Param: [review]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 22:59
     * @Description: 保存评论
     */
    @Transactional
    public void insertReview(Review review) {
        int success = reviewMapper.insertSelective(review);
        if (success == 0) {
            throw new GlobalException(BlogEnum.REVIEW_INSERT_ERROR);
        }
    }

    @Override

    /**
     * @Param: [review]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 23:00
     * @Description: 更新评论 state = 0 代表删除
     */
    @Transactional
    public void updateReview(Review review) {
        int success = reviewMapper.updateByPrimaryKeySelective(review);
        if (success == 0) {
            throw new GlobalException(BlogEnum.REVIEW_INSERT_ERROR);
        }
        if (review.getReviewParent() == null && review.getState() == BlogConstant.RECORD_VOID) {
            Review review1 = new Review();
            review1.setState(BlogConstant.RECORD_VOID);
            ReviewExample reviewExample = new ReviewExample();
            reviewExample.createCriteria().andReviewParentEqualTo(review.getReviewId());
            int cSuccess = reviewMapper.updateByExampleSelective(review1, reviewExample);
        }
    }

    @Override

    /**
     * @Param: [articleId, state, flag]
     * @Return:java.util.List<com.deschen.myblog.modules.system.dto.ReviewDto>
     * @Author: deschen
     * @Date: 2019/5/29 23:05
     * @Description: 获取文章id下的相应的评论和回复，根据flag有误显示email（无 代表用户， 有代表博主有）
     */
    public List<ReviewDto> selectReviewDto(Long articleId, Integer state, Integer flag) {
        ReviewExample reviewExample = new ReviewExample();
        ReviewExample.Criteria criteria =
                reviewExample.createCriteria().andArticleIdEqualTo(articleId);
        // 无代表所有状态
        if (state != null) {
            criteria.andStateEqualTo(state);
        }
        // 评论
        List<Review> reviews = reviewMapper.selectByExample(reviewExample);

        List<ReviewDto> reviewDtos = reviews.stream().map(
                review -> {
                    ReviewDto pReviewDto = getReviewDto(review, flag);
                    // 获取该评论下的回复
                    Long reviewId = pReviewDto.getReviewId();
                    ReviewExample cReviewExample = new ReviewExample();
                    ReviewExample.Criteria cCriteria = cReviewExample.createCriteria()
                            .andReviewParentEqualTo(reviewId);
                    if (state != null) {
                        cCriteria.andStateEqualTo(state);
                    }
                    List<Review> cReviews = reviewMapper.selectByExample(cReviewExample);
                    List<ReviewDto> cReviewDtos = cReviews.stream().map(
                            cReview -> {
                                ReviewDto cReviewDto = getReviewDto(cReview, flag);
                                return cReviewDto;
                            }
                    ).collect(Collectors.toList());
                    pReviewDto.setReviewDtos(cReviewDtos);
                    return pReviewDto;
                }
        ).collect(Collectors.toList());
        return reviewDtos;
    }

    private ReviewDto getReviewDto(Review parentReview, Integer flag) {
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(parentReview, reviewDto);
        if (flag == null) {
            reviewDto.setEmail(null);
        }
        User user = userMapper.selectByPrimaryKey(parentReview.getUserId());
        reviewDto.setUserName(user.getUserName());
        reviewDto.setImageId(user.getImageId());
        return reviewDto;
    }
}
