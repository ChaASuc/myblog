package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.modules.system.mapper.EpRoleMapper;
import com.deschen.myblog.modules.system.mapper.EpUserMapper;
import com.deschen.myblog.modules.system.mapper.EpUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: deschen
 * @date: 2018/10/15 16:54
 * @description: 用户认证、权限
 */
@Component
public class EpUserDetailsService implements UserDetailsService {

    @Autowired
    private EpUserMapper userMapper;

    @Autowired
    private EpRoleMapper roleMapper;

    @Autowired
    private EpUserRoleMapper epUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过username查询用户
//        EpUserDetails user = userMapper.selectByUsername(username);
//        if(user == null){
//            //仍需要细化处理
//            throw new UsernameNotFoundException("该用户不存在");
//        }
//
//        return user;
        return null;
    }
}
