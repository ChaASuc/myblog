package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.modules.system.entity.*;
import com.deschen.myblog.modules.system.mapper.*;
import com.deschen.myblog.modules.system.service.ArticleModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RunAs;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author deschen
 * @Create 2019/5/28
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class ArticleModuleServiceImpl implements ArticleModuleService {

    @Autowired
    private VisitMapper visitMapper;

    @Autowired
    private ThumbupMapper thumbupMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserConfigMapper userConfigMapper;



    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void insertArticleModule(Long articleId) {

        // 创建访问，点赞，评论量实体类
        Visit visit = new Visit();
        Long visitId = new IdWorker().nextId();
        visit.setVisitId(visitId);
        visit.setArticleId(articleId);
        int successVisit = visitMapper.insertSelective(visit);
        if (successVisit == 0) {
            throw new GlobalException(BlogEnum.VISIT_INSERT_ERROR);
        }

        Thumbup thumbup = new Thumbup();
        long thumbupId = new IdWorker().nextId();
        thumbup.setThumbupId(thumbupId);
        thumbup.setArticleId(articleId);
        int successThumbup = thumbupMapper.insertSelective(thumbup);
        if (successThumbup == 0) {
            throw new GlobalException(BlogEnum.THUMBUP_INSERT_ERROR);
        }

        Comment comment = new Comment();
        long commentId = new IdWorker().nextId();
        comment.setCommentId(commentId);
        comment.setArticleId(articleId);
        int successComment = commentMapper.insertSelective(comment);
        if (successComment == 0) {
            throw new GlobalException(BlogEnum.COMMENT_INSERT_ERROR);
        }
    }

    @Override
    /**
     * @Param: []
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/28 20:29
     * @Description: redis访问次数更新到visit中
     */
    @Transactional
    public void transVisitCountFromRedisDB() {
        String visitPrefix = String.format(RedisConstant.VISIT_PREFIX, "*");
        Set<String> keys = redisUtil.keys(visitPrefix);
        keys.stream().forEach(
                key -> {
                    // 获取文章id
                    Long articleId = Long.valueOf(key.substring(key.lastIndexOf("_") + 1));
                    // 获取文章id所属的浏览表
                    VisitExample visitExample = new VisitExample();
                    visitExample.createCriteria().andArticleIdEqualTo(articleId);
                    List<Visit> visits = visitMapper.selectByExample(visitExample);
                    // 如果不存在则跳过
                    if (visits.size() != 0) {
                        // 存在
                        Integer visitCount = (Integer) redisUtil.get(key);
                        Visit visit = visits.get(0);
                        if (visit.getVisitCount() > visitCount) {
                            // reids缓存误删，更新缓存
                            redisUtil.set(key, visit.getVisitCount());
                        } else if(visit.getVisitCount() < visitCount){
                            // 更新浏览表的缓存
                            visit.setVisitCount(visitCount);
                            int success = visitMapper.updateByPrimaryKey(visit);
                            if (success == 0) {
                                log.info("【redis定时数据库】更新访问量失败，visitId = {}",
                                        visit.getVisitId());
                                throw new GlobalException(BlogEnum.VISIT_UPDATE_ERROR);
                            }
                        }// 浏览量跟redis相同则跳过
                    }


                }
        );
    }

    @Override

    /**
     * @Param: []
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/29 12:40
     * @Description: redis点赞次数更新到thumbup中
     */
    @Transactional
    public void transThumbupCountFromRedisDB() {
        String thumbupPrefix = String.format(RedisConstant.THUMBUP_PREFIX, "*");
        Set<String> keys = redisUtil.keys(thumbupPrefix);
        keys.stream().forEach(
                key -> {
                    // 获取文章id
                    Long articleId = Long.valueOf(key.substring(key.lastIndexOf("_") + 1));

                    // 获取文章id所属的点赞表
                    ThumbupExample thumbupExample = new ThumbupExample();
                    thumbupExample.createCriteria().andArticleIdEqualTo(articleId);
                    List<Thumbup> thumbups = thumbupMapper.selectByExample(thumbupExample);
                    // 判断是否存在
                    if (thumbups.size() != 0) {
                        Integer thumbupCount = (Integer) redisUtil.get(key);
                        Thumbup thumbup = thumbups.get(0);
                        // 点赞会减少，不能用只增不减的操作
                        if (thumbupCount == 0) {
                            // redis误删点赞量
                            redisUtil.set(key, thumbup.getThumbupCount());
                        }else {
                            thumbup.setThumbupCount(thumbupCount);
                            int success = thumbupMapper.updateByPrimaryKey(thumbup);
                            if (success == 0) {
                                log.info("【redis定时数据库】更新点赞量失败，articleId = {}",
                                        articleId);
                                throw new GlobalException(BlogEnum.THUMBUP_UPDATE_ERROR);
                            }
                        }
                    }

                }
        );
    }

    @Override

    /**
     * @Param: []
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/30 22:22
     * @Description: 从评论表中的数量更新到评论量表中
     */
    @Transactional
    public void transCommentCountFromRedisDB() {
        List<Comment> comments =
                commentMapper.selectByExample(new CommentExample());
        comments.stream().forEach(
                comment -> {
                    // 获取评论量表中的文章id
                    Long articleId = comment.getArticleId();
                    // 根据文章id和有效状态获取评论集合
                    ReviewExample reviewExample = new ReviewExample();
                    reviewExample.createCriteria()
                            .andStateEqualTo(BlogConstant.RECORD_VALID)
                            .andArticleIdEqualTo(articleId);
                    List<Review> reviews = reviewMapper.selectByExample(reviewExample);
                    // 评论集合判空
                    if (reviews == null) {
                        comment.setCommentCount(0);
                    } else {
                        comment.setCommentCount(reviews.size());
                    }
                    // 更新评论
                    int success = commentMapper.updateByPrimaryKeySelective(comment);
                    if (success == 0) {
                        log.info("【定时数据库】 评论量更新失败，commentId={}", comment.getCommentId());
                        throw new GlobalException(BlogEnum.COMMENT_UPDATE_ERROR);
                    }
                }
        );
    }

    @Override

    /**
     * @Param: []
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/31 19:08
     * @Description: 从浏览量表，评论量表， 点赞量表中的数量累加更新到用户配置表中
     */
    public void transUserConfigSumFromRedisDB() {
        // 创建用户配置信息，用于记录每天的文章总数，浏览量总数，点赞量总数，评论量总数
        UserConfig userConfig = new UserConfig();
        long userConfigId = new IdWorker().nextId();
        userConfig.setConfigId(userConfigId);
        userConfig.setUserId(BlogConstant.AUTHOR_ID);
        // 获取有效的文章
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andStateEqualTo(BlogConstant.RECORD_VALID);
        List<Article> articles =
                articleMapper.selectByExample(articleExample);
        // 判断是否为空
        if (articles == null) {
            userConfig.setArticleSum(0);
        }else {
            userConfig.setArticleSum(articles.size());
        }

        // 获取所有文章的评论量
        List<Comment> comments =
                commentMapper.selectByExample(new CommentExample());
        Integer commentSum =
                comments.stream().map(Comment::getCommentCount).reduce(0, Integer::sum);
        userConfig.setCommentSum(commentSum);

        // 获取所有文章的点赞数
        List<Thumbup> thumbups =
                thumbupMapper.selectByExample(new ThumbupExample());
        Integer thumbupSum =
                thumbups.stream().map(Thumbup::getThumbupCount).reduce(0, Integer::sum);
        userConfig.setThumbupSum(thumbupSum);

        // 获取文章的浏览量
        List<Visit> visits =
                visitMapper.selectByExample(new VisitExample());
        Integer visitSum =
                visits.stream().map(Visit::getVisitCount).reduce(0, Integer::sum);
        userConfig.setVisitSum(visitSum);

        // 创建今天的文章总量，点赞总量，访问总量，浏览总量的记录
        int success = userConfigMapper.insertSelective(userConfig);
        if (success == 0) {
            throw new GlobalException(BlogEnum.USERCONFIG_INSERT_ERROR);
        }
    }

//    public static void main(String[] args) {
//        ArrayList<Comment> comments = new ArrayList<>();
//        Comment comment = new Comment();
//        comment.setCommentCount(1);
//        Comment comment1 = new Comment();
//        comment1.setCommentCount(2);
//        comments.add(comment);
//        comments.add(comment1);
//        Integer reduce = comments.stream().map(Comment::getCommentCount).reduce(0, Integer::sum);
//        System.out.println(reduce);
//    }
}
