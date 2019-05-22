package com.deschen.myblog.core.utils;


import com.deschen.myblog.core.enums.IEnum;

/**
 * @Author deschen
 * @Create 2019/4/25
 * @Description
 * @Since 1.0.0
 */
public class EnumUtil {

    public static <T extends IEnum> T getByCode(Integer code, Class<T> enumClass) {

        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
