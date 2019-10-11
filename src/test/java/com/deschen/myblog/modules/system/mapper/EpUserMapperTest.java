package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.utils.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class EpUserMapperTest extends TestUtil {

    @Autowired
    private EpUserMapper epUserMapper;

    @Test
    public void selectByUsernameTest() {
        EpUserDetails string = epUserMapper.selectByUsername("string");
        System.out.println(string.toString());

    }
}