package com.deschen.myblog.common.security;

import com.alibaba.fastjson.JSON;
import com.deschen.myblog.common.utils.AccessAddressUtil;
import com.deschen.myblog.common.utils.JwtTokenUtil;
import com.deschen.myblog.core.enums.GlobalEnum;
import com.deschen.myblog.core.enums.ResultEnum;
import com.deschen.myblog.core.utils.RedisUtil;
import com.deschen.myblog.core.utils.ResultVOUtil;
import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.modules.system.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: deschen
 * @date: 2018/10/15 16:12
 * @description: 用户登录成功时返回给前端的数据
 */
@Component
@Slf4j
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Value("${token.expirationSeconds}")
    private int expirationSeconds;

    @Value("${token.validTime}")
    private int validTime;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获取请求的ip地址
        String ip = AccessAddressUtil.getIpAddress(httpServletRequest);
        Map<String,Object> map = new HashMap<>();
        map.put("ip",ip);

        EpUserDetails userDetails = (EpUserDetails) authentication.getPrincipal();

        String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), expirationSeconds, map);

        //刷新时间
        Integer expire = validTime*24*60*60*1000;
        //获取请求的ip地址
        String currentIp = AccessAddressUtil.getIpAddress(httpServletRequest);
        redisUtil.setTokenRefresh(jwtToken,userDetails.getUsername(),currentIp);
        log.info("用户{}登录成功，信息已保存至redis",userDetails.getUsername());

        httpServletResponse.setHeader("token", jwtToken);
        httpServletResponse.getWriter().write(JSON.toJSONString(ResultVOUtil.success()));
        System.out.println(jwtToken);
    }
}
