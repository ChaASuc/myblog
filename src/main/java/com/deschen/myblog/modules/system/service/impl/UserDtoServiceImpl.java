package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.dto.UserDto;
import com.deschen.myblog.modules.system.entity.User;
import com.deschen.myblog.modules.system.entity.UserConfig;
import com.deschen.myblog.modules.system.entity.UserConfigExample;
import com.deschen.myblog.modules.system.entity.UserExample;
import com.deschen.myblog.modules.system.mapper.UserConfigMapper;
import com.deschen.myblog.modules.system.mapper.UserMapper;
import com.deschen.myblog.modules.system.service.UserDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class UserDtoServiceImpl implements UserDtoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConfigMapper userConfigMapper;



    @Override

    /**
     * @Param: [user]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/31 8:36
     * @Description: 添加用户
     */
    public void insertUser(User user) {
        int success = userMapper.insertSelective(user);
        if (success == 0) {
            throw new GlobalException(BlogEnum.USER_INSERT_ERROR);
        }
    }


    @Override

    /**
     * @Param: [userId]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/31 8:40
     * @Description: 添加用户配置信息
     */
    public void insertUserConfig(Long userId) {
        long userConfigId = new IdWorker().nextId();
        UserConfig userConfig = new UserConfig();
        userConfig.setConfigId(userConfigId);
        userConfig.setUserId(userId);
        int success = userConfigMapper.insertSelective(userConfig);
        if (success == 0) {
            throw new GlobalException(BlogEnum.USER_CONFIG_INSERT_ERROR);
        }
    }

    @Override

    /**
     * @Param: [user]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/5/31 8:43
     * @Description: 更新用户信息
     */
    public void updateUser(User user) {
        int success = userMapper.updateByPrimaryKeySelective(user);
        if (success == 0) {
            throw new GlobalException(BlogEnum.USER_CONFIG_UPDATE_ERROR);
        }
    }

    @Override

    /**
     * @Param: [userId]
     * @Return:com.deschen.myblog.modules.system.dto.UserDto
     * @Author: deschen
     * @Date: 2019/5/31 8:45
     * @Description: 获取用户信息 flag为空，用户查看，密码为空， flag有值，密码不为空，给管理员看
     */
    public UserDto selectUserDto(Long userId) {
        UserDto userDto = new UserDto();
        User user = userMapper.selectByPrimaryKey(userId);
        BeanUtils.copyProperties(user, userDto);

        UserConfigExample userConfigExample = new UserConfigExample();
        // 根据最新排序
        userConfigExample.setOrderByClause("UPDATE_TIME DESC");
        userConfigExample.createCriteria().andUserIdEqualTo(user.getUserId());
        List<UserConfig> userConfigs = userConfigMapper.selectByExample(userConfigExample);
        if (userConfigs != null && userConfigs.size() != 0) {
            // 获取最新的用户配置信息
            UserConfig userConfig = userConfigs.get(0);
            userDto.setCommentSum(userConfig.getCommentSum());
            userDto.setThumbupSum(userConfig.getThumbupSum());
            userDto.setVisitSum(userConfig.getVisitSum());
        }
        String imageUrl = BlogConstant.IMAGE_USER_URL + user.getImageId();
        userDto.setImageUrl(imageUrl);

        return userDto;
    }

    @Override

    /**
     * @Param: [userName, userPassword, salt]
     * @Return:boolean
     * @Author: deschen
     * @Date: 2019/5/31 8:55
     * @Description: 校验用户是否存在
     */
    public void checkUserByUserNameAndUserPasswordAndSalt(String userName, String userPassword, String salt) {

        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName)
                .andUserPasswordEqualTo(userPassword).andStateEqualTo(BlogConstant.RECORD_VALID);
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null || users.size() == 0) {
            throw new GlobalException(BlogEnum.USER_NOT_EXIST);
        }
    }

}