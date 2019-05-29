package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.modules.system.entity.*;
import com.deschen.myblog.modules.system.mapper.ArticleMapper;
import com.deschen.myblog.modules.system.mapper.CommentMapper;
import com.deschen.myblog.modules.system.mapper.ThumbupMapper;
import com.deschen.myblog.modules.system.mapper.VisitMapper;
import com.deschen.myblog.modules.system.service.ArticleModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RunAs;
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
//                        if (thumbup.getThumbupCount() > thumbupCount) {
//                            // redis误删点赞量
//                            redisUtil.set(key, thumbup.getThumbupCount());
//                        }else if(thumbup.getThumbupCount() < thumbupCount){
                            thumbup.setThumbupCount(thumbupCount);
                            int success = thumbupMapper.updateByPrimaryKey(thumbup);
                            if (success == 0) {
                                log.info("【redis定时数据库】更新点赞量失败，articleId = {}",
                                        articleId);
                                throw new GlobalException(BlogEnum.THUMBUP_UPDATE_ERROR);
                            }
//                        }// 点赞量跟redis相同跳过
                    }

                }
        );
    }
}
