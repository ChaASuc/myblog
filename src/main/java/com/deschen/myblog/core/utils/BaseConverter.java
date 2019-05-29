package com.deschen.myblog.core.utils;

import com.deschen.myblog.modules.system.entity.Article;
import com.deschen.myblog.modules.system.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseConverter
 * @Description 转换器工具
 * @Author chen
 * @Data 2018/12/31 16:13
 * @Version 1.0
 **/
public class BaseConverter<POJO, VO> {
    private static final Logger logger = LoggerFactory.getLogger(BaseConverter.class);

    /**
     * 单个对象转换
     */
    public VO convert(POJO from, Class<VO> clazz) {
        if (from == null) {
            return null;
        }
        VO to = null;
        try {
            to = clazz.newInstance();
        } catch (Exception e) {
            logger.error("初始化{}对象失败。", clazz, e);
        }
        convert(from, to);
        return to;
    }

    /**
     * 批量对象转换
     */
    public List<VO> convert(List<POJO> fromList, Class<VO> clazz) {
        if (CollectionUtils.isEmpty(fromList)) {
            return null;
        }
        List<VO> toList = new ArrayList<VO>();
        for (POJO from : fromList) {
            toList.add(convert(from, clazz));
        }
        return toList;
    }


    /**
     * 属性拷贝方法，有特殊需求时子类覆写此方法
     */
    public void convert(POJO from, VO to) {
        BeanUtils.copyProperties(from, to);
    }

//    public static void main(String[] args) {
//        BaseConverter<Category, Article> categoryArticleBaseConverter = new BaseConverter<>();
//        Category category = new Category();
//        categoryArticleBaseConverter.convert(category, Article.class);
//    }
}
