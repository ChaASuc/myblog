package com.deschen.myblog.core.enums;

/**
 * @Author deschen
 * @Create 2019/4/28
 * @Description
 * @Since 1.0.0
 */
public enum ResultEnum implements IEnum{

    SUCCESS(0, "成功"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }}
