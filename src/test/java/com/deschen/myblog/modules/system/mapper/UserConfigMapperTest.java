package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.entity.UserConfig;
import com.deschen.myblog.modules.system.entity.UserConfigExample;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class UserConfigMapperTest extends TestUtil {

    @Autowired
    private UserConfigMapper userConfigMapper;

    @Test
    public void test() {
        Date date = new Date();
        Integer sort = BlogConstant.DESC;
        Long userId = 1L;
        List<UserConfig> userConfigs = userConfigMapper.selectUserConfigWeekByDate(userId, date, sort);
        try {
            log.info("【用户配置信息】根据时间和排序规则获取本周，userConfig={}", JsonUtil.obj2string(userConfigs));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(62, userConfigs.size());
        List<UserConfig> userConfigMonths = userConfigMapper.selectUserConfigMonthByDate(userId, date, sort);
        try {
            log.info("【用户配置信息】根据时间{}和排序规则{}获取本月，userConfig={}", JsonUtil.obj2string(userConfigMonths));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(62, userConfigMonths.size());

        List<UserConfig> userConfigYears = userConfigMapper.selectUserConfigYearByDate(userId, date, sort);
        try {
            log.info("【用户配置信息】根据时间{}和排序规则{}获取本月，userConfig={}", JsonUtil.obj2string(userConfigYears));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(63, userConfigYears.size());
    }

}