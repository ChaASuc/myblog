package com.deschen.myblog.core.quartz;


import com.deschen.myblog.modules.system.service.ArticleDtoService;
import com.deschen.myblog.modules.system.service.ArticleModuleService;
import com.deschen.myblog.modules.system.service.CategoryDtoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 点赞的定时任务
 */
@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    CategoryDtoService categoryDtoService;

    @Autowired
    ArticleModuleService articleModuleService;
//
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("LikeTask-------- {}", sdf.format(new Date()));

        //将 Redis 里的点赞信息同步到数据库里
        categoryDtoService.transHotFromRedisDB();
        articleModuleService.transVisitCountFromRedisDB();
        articleModuleService.transThumbupCountFromRedisDB();

    }
}
