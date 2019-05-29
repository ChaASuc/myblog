package com.deschen.myblog.core.utils;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import org.springframework.stereotype.Component;

/**
 * @Author deschen
 * @Create 2019/5/26
 * @Description 获取排序字段
 * @Since 1.0.0
 */
@Component
public class SortUtil {

    public String getSort(Integer sort) {
        if (sort == null || sort.equals(BlogConstant.HOT)) {
            return "hot";
        } else if (sort.equals(BlogConstant.NEWEST)) {
            return "newest";
        } else if (sort.equals(BlogConstant.COMMENT)) {
            return "comment";
        }else {
            throw new GlobalException(BlogEnum.SORT_ERROR);
        }
    }
}
