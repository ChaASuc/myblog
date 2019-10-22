package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.core.utils.JsonUtil;
import com.deschen.myblog.modules.system.entity.EpPermission;
import com.deschen.myblog.modules.system.entity.EpUserDetialsPermission;
import com.deschen.myblog.utils.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Slf4j
public class EpPermissionMapperTest extends TestUtil {

    @Autowired
    private EpUserMapper userMapper;

    @Autowired
    private EpPermissionMapper permissionMapper;

//    @Test
//    public void selectByAuthoritiesTest() throws JsonProcessingException {
//        EpUserDetails userDetails =
//                userMapper.selectByUsername("string");
//        System.out.println(JsonUtil.obj2string(userDetails));
//        Set<EpPermission> epPermissions =
//                permissionMapper.selectByAuthorities(userDetails.getAuthorities());
//        System.out.println(JsonUtil.obj2string(epPermissions));
//    }

    @Test
    public void selectByRoleNameTest() throws JsonProcessingException {
        Set<EpUserDetialsPermission> role_admin =
                permissionMapper.selectByRoleName("ROLE_ADMIN");
        System.out.println(JsonUtil.obj2string(role_admin));
    }

    @Test
    public void selectByExampleTest() throws JsonProcessingException {
        List<EpPermission> epPermissions =
                permissionMapper.selectByExample(null);
        System.out.println(JsonUtil.obj2string(epPermissions));
    }
}