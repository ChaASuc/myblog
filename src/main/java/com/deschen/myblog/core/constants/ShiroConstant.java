package com.deschen.myblog.core.constants;

/**
 * @Author deschen
 * @Create 2019/4/9
 * @Description shiro相关的常量配置
 * @Since 1.0.0
 */
public interface ShiroConstant {

    /*缓存*/
    String SHIRO_SESSION_PREFIX = "shiro-session";

    String SHIRO_CACHE_PREFIX = "shiro-cache";


    /*角色*/
    Integer USER = 0;

    Integer ADMIN = 1;

}
