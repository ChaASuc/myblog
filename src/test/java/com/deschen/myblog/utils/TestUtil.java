package com.deschen.myblog.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.omg.CORBA.Current;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description
 * @Since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUtil {

    private Long time;

    @Before
    public void before() {
        time = System.currentTimeMillis();
        log.info("【测试】开始");
    }

    @After
    public void after() {
        time = System.currentTimeMillis() - time;
        log.info("【测试】结束，time={}", time);
    }

}
