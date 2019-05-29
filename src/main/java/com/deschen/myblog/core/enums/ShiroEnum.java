package com.deschen.myblog.core.enums;

import lombok.Getter;

/**
 * @Author deschen
 * @Create 2019/5/27
 * @Description 权限相关状态吗
 * @Since 1.0.0
 */
@Getter
public enum ShiroEnum implements IEnum {

    NOT_PERMISSION(201, "没有权限"),



    ;
    private Integer code;

    private String message;

    ShiroEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
