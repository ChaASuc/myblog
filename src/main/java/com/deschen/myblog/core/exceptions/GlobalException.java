package com.deschen.myblog.core.exceptions;


import com.deschen.myblog.core.enums.IEnum;
import lombok.Getter;

/**
 * @Author deschen
 * @Create 2019/5/14
 * @Description
 * @Since 1.0.0
 */
@Getter
public class GlobalException extends RuntimeException{

    private Integer code;

    public GlobalException(IEnum iEnum) {
        super(iEnum.getMessage());
        this.code = code;
    }

    public GlobalException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
