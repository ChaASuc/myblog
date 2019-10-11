package com.deschen.myblog.core.enums;

/**
 * @Author deschen
 * @Create 2019/7/14
 * @Description  全局状态码模板
 * @Since 1.0.0
 */
public enum GlobalEnum implements IEnum{

    /*
     * https://blog.csdn.net/q1056843325/article/details/53147180
     * Http状态码
     * */
    PARAMS_ERROR(1000, "参数错误: %s"),

    SERVICE_ERROR(1001, "系统异常：%s"),

    USER_NEED_AUTHORITIES(201,"用户未登录"),
    USER_LOGIN_FAILED(202,"用户账号或密码错误"),
    USER_LOGIN_SUCCESS(203,"用户登录成功"),
    USER_NO_ACCESS(204,"用户无权访问"),
    USER_LOGOUT_SUCCESS(205,"用户登出成功"),
    TOKEN_IS_BLACKLIST(206,"此token为黑名单"),
    LOGIN_IS_OVERDUE(207,"登录已失效"),
    ;

    private Integer code;

    private String message;

    GlobalEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public GlobalEnum setGlobalMessage(String arg) {
        this.message = String.format(this.message, arg);
        return this;
    }
}
