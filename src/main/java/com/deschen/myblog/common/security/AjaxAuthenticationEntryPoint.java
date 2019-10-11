package com.deschen.myblog.common.security;

import com.alibaba.fastjson.JSON;
import com.deschen.myblog.core.enums.GlobalEnum;
import com.deschen.myblog.core.enums.ResultEnum;
import com.deschen.myblog.core.utils.ResultVOUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: deschen
 * @date: 2018/10/15 15:04
 * @description: 用户未登录时返回给前端的数据
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVOUtil.error(GlobalEnum.USER_NEED_AUTHORITIES)));
    }
}
