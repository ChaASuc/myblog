package com.deschen.myblog.common.security;

import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.modules.system.mapper.EpPermissionMapper;
import com.deschen.myblog.modules.system.mapper.EpUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: deschen
 * @date: 2018/10/16 9:54
 * @description: 权限访问控制
 */
@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Autowired
    private EpPermissionMapper permissionMapper;

    @Autowired
    private EpUserMapper userMapper;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission  = false;

        if (userInfo instanceof EpUserDetails) {


            EpUserDetails userDetails = (EpUserDetails) userInfo;

            List<String> urls = permissionMapper.selectByAuthorities(userDetails.getAuthorities())
                    .stream().map(
                            e -> e.getZuulPrefix() + e.getServerMethod()
                    ).collect(Collectors.toList());

            AntPathMatcher antPathMatcher = new AntPathMatcher();

            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }

}
