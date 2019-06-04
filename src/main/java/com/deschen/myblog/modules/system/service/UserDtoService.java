package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.entity.UserConfig;
import org.apache.catalina.LifecycleState;

import java.util.Date;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description 用户信息事务层
 * @Since 1.0.0
 */
public interface UserDtoService {

    Long insertUser(User user);

    void insertUserConfig(Long userId);

    void updateUser(User user);

    UserDto selectUserDto(Long userId);

    void checkUserByUserNameAndUserPasswordAndSalt(
            String userName, String userPassword, String salt
    );


    List<UserConfig> selectUserConfigs(Long userId, Date date, Integer sort, Integer condition);

}
