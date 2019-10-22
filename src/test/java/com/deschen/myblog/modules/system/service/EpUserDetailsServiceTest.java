package com.deschen.myblog.modules.system.service;

import com.alibaba.fastjson.JSON;
import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;

public class EpUserDetailsServiceTest extends TestUtil {

    @Autowired
    private EpUserDetailsService epUserDetailsService;

    @Test
    public void loadUserByUsername() throws JsonProcessingException {
        UserDetails deschen = epUserDetailsService.loadUserByUsername("deschen");
        System.out.println(JsonUtil.obj2string(deschen));
    }
}