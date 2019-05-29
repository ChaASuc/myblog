package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.RedisConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.modules.system.entity.*;
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
                    Long articleId = Long.valueOf(key.substring(key.lastIndexOf("_") + 1));
                    VisitExample visitExample = new VisitExample();
                    visitExample.createCriteria().andArticleIdEqualTo(articleId);
                    Integer visitCount = (Integer) redisUtil.get(key);
                    Visit visit = new Visit();
                    visit.setVisitCount(visitCount);
                    int success = visitMapper.updateByExampleSelective(visit, visitExample);
                    if (success == 0) {
                        log.info("【redis定时数据库】更新访问量失败，visitId = {}",
                                visit.getVisitId());
                        throw new GlobalException(BlogEnum.VISIT_UPDATE_ERROR);
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
                    Long articleId = Long.valueOf(key.substring(key.lastIndexOf("_") + 1));
                    Integer thumbupCount = (Integer)redisUtil.get(key);
                    Thumbup thumbup = new Thumbup();
                    thumbup.setThumbupCount(thumbupCount);
                    ThumbupExample thumbupExample = new ThumbupExample();
                    thumbupExample.createCriteria().andArticleIdEqualTo(articleId);
                    int success = thumbupMapper.updateByExampleSelective(thumbup, thumbupExample);
                    if (success == 0) {
                        log.info("【redis定时数据库】更新点赞量失败，articleId = {}",
                                articleId);
                        throw new GlobalException(BlogEnum.THUMBUP_UPDATE_ERROR);
                    }
                }
        );
    }
}
