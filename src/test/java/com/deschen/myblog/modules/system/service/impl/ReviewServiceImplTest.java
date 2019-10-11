package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.dto.ReviewDto;
import com.deschen.myblog.modules.system.entity.Review;
import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.service.ReviewDtoService;
import com.deschen.myblog.modules.system.vo.ResultVO;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                reviewService.selectReviewDto(1133724118303219712L, null, BlogConstant.FLAGADMIN, 0);
        log.info("【评论模块】根据文章id获取评论 articleId={}，reviewDtos={}",
                1133724118303219712L, JsonUtil.obj2string(reviewDtos));
    }

    @Test
    public void Test() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "nihao1");
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(100);
        resultVO.setMsg("你好");
        resultVO.setData(map);
        System.out.println(JsonUtil.obj2string(resultVO));
        System.out.println("-------------");
        resultVO.setData(new Tag());
        System.out.println(JsonUtil.obj2string(resultVO));

    }

    @Test
    public void JDK8ToMapTest() throws JsonProcessingException {
        List<TestExample> testExamples = new ArrayList<>();
        testExamples.add(new TestExample("1", "nihao1-1"));
        testExamples.add(new TestExample("1", "nihao1-2"));
        testExamples.add(new TestExample("2", "nihao2-1"));
        Map<String, String> collect = testExamples.stream().collect(Collectors.toMap(
                TestExample::getName, TestExample::getMessage, (t1, t2) -> t1.concat(",").concat(t2)
        ));
        System.out.println(JsonUtil.obj2string(collect));
        System.out.println("-----------------");
        String collect1 = testExamples.stream().map(
                t -> {
                    String concat = t.getName().concat(",").concat(t.getMessage());
                    return concat;
                }
        ).collect(Collectors.joining(","));
        System.out.println(collect1);
    }
}

@Data
class TestExample {

    private String name;

    private String message;

    public TestExample() {
    }

    public TestExample(String name, String message) {
        this.name = name;
        this.message = message;
    }
}