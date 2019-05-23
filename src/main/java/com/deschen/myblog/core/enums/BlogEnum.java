package com.deschen.myblog.core.enums;

/**
 * @Author deschen
 * @Create 2019/4/25
 * @Description
 * @Since 1.0.0
 */
public enum BlogEnum implements IEnum {

    CATEGORY_NOT_EXIST(100, "种类不存在"),

    TAG_NOT_EXIST(101, "标签不存在"),

    SORT_ERROR(102, "排序字符串错误"),

    ARTICLE_INSERT_ERROR(103, "文章存到数据库失败"),

    ARTICLE_UPDATE_ERROR(104, "文章更新失败");
    ;
    private Integer code;

    private String message;

    BlogEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
