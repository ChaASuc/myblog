package com.deschen.myblog.common.security;

import com.deschen.myblog.config.BlogConfig;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.enums.GlobalEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.modules.system.entity.EpPermission;
import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.modules.system.entity.EpUserDetialsPermission;
import com.deschen.myblog.modules.system.mapper.EpPermissionMapper;
import com.deschen.myblog.modules.system.mapper.EpUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.security.Permission;
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
@Slf4j
@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Autowired
    private EpPermissionMapper permissionMapper;

    @Autowired
    private EpUserMapper userMapper;


    @Autowired
    private BlogConfig blogConfig;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        //请求方式
        Integer method = getRequestMethod(request.getMethod());

        boolean hasPermission  = false;

        Set<EpUserDetialsPermission> permissions = new HashSet<>();

        if (userInfo instanceof EpUserDetails) {
            EpUserDetails userDetails = (EpUserDetails) userInfo;
            permissions = userDetails.getPermissions();
            log.info("【安全框架】权限模块 用户userDetail = {}", userDetails);
        }else {
            permissions = permissionMapper.selectByRoleName(blogConfig.getCommonRoleName());
        }

        AntPathMatcher antPathMatcher = new AntPathMatcher();


        long count = permissions.stream()
                .filter(e -> {
                    if (e.getReqMethod() == 0) {
                         return antPathMatcher.match(e.getUri(), request.getRequestURI());
                    }
                    return e.getReqMethod().equals(method)
                            && antPathMatcher.match(e.getUri(), request.getRequestURI());
                }).count();

        if(count > 0){
            log.info("【安全框架】拦截成功链接 url = {}", request.getRequestURI());
            hasPermission = true;
        }

        return hasPermission;
    }

    public Integer getRequestMethod(String method) {

        if (null != method) {
            if ("GET".equalsIgnoreCase(method)) {
                return 1;
            }else if ("POST".equalsIgnoreCase(method)) {
                return 2;
            }else if ("PUT".equalsIgnoreCase(method)) {
                return 3;
            }else if ("DELETE".equalsIgnoreCase(method)) {
                return 4;
            }else {
                throw new GlobalException(GlobalEnum.PARAMS_ERROR.getCode(), "请求方式");
            }
        }else {
            throw new GlobalException(GlobalEnum.PARAMS_ERROR.getCode(), "请求方式");
        }

    }
}
