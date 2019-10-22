//package com.deschen.myblog.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)//激活方法上的PreAuthorize注解
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    //Http安全配置，对每个到达系统的http请求链接进行校验
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //所有请求必须认证通过
//        http.authorizeRequests()
//                //下边的路径放行
//                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
//                        "/swagger-resources","/swagger-resources/configuration/security",
//                        "/swagger-ui.html","/webjars/**").permitAll()
//                .anyRequest().authenticated();
//    }
//}
