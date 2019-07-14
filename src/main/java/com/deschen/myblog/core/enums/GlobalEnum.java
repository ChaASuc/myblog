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
