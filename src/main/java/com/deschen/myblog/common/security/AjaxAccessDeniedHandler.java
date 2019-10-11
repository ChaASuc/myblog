package com.deschen.myblog.common.security;

import com.alibaba.fastjson.JSON;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.enums.GlobalEnum;
import com.deschen.myblog.core.enums.ResultEnum;
import com.deschen.myblog.core.utils.ResultVOUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: deschen
 * @date: 2018/10/15 16:43
 * @description: 无权访问
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVOUtil.error(GlobalEnum.USER_NO_ACCESS)));
    }
}
