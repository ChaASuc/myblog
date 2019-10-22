package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.entity.EpUser;
import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

@Slf4j
public class EpUserMapperTest extends TestUtil {

    @Autowired
    private EpUserMapper epUserMapper;

    @Test
    public void selectByUsernameTest() throws JsonProcessingException {
        EpUserDetails string = epUserMapper.selectByUsername("string");
        System.out.println(JsonUtil.obj2string(string));
        EpUserDetails string2 = epUserMapper.selectByUsername("zhangsan");
        System.out.println(JsonUtil.obj2string(string2));
        EpUserDetails string3 = epUserMapper.selectByUsername("xxx");
        System.out.println(JsonUtil.obj2string(string3));

        jsonTest(string, string2, string3);
    }

    private void jsonTest(Object string, Object string2, Object string3) throws JsonProcessingException {

            EpUserDetails string11 = (EpUserDetails) string;
            EpUserDetails string21 = (EpUserDetails) string2;
            EpUserDetails string31 = (EpUserDetails) string3;
            System.out.println(JsonUtil.obj2string(string11));
            System.out.println(JsonUtil.obj2string(string21));
            System.out.println(JsonUtil.obj2string(string31));

    }

    @Test
    public void insertUser() throws JsonProcessingException {
        //因为只是简单注册，故只是对密码加密保存，其他就不添加进来了
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String EncryptedPassword = bCryptPasswordEncoder.encode("nlfdzccq9;,.");
        EpUser epUser = new EpUser();
        epUser.setUserId(1182968089347710973L);
        epUser.setUserNickname("deschen");
        epUser.setUserName("deschen");
        epUser.setUserPwd(EncryptedPassword);
        int i = epUserMapper.insertSelective(epUser);


    }

}