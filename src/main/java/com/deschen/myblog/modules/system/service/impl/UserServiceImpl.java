package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.mapper.UserMapper;
import com.deschen.myblog.modules.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        int success = userMapper.insertSelective(user);
        if (success == 0) {
            throw new GlobalException(BlogEnum.USER_INSERT_ERROR);
        }
    }
}
