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

    ARTICLE_UPDATE_ERROR(104, "文章更新失败"),

    TAG_INSERT_ERROR(105, "标签存到数据库失败"),

    CATEGORY_INSERT_ERROR(106, "种类保存到数据库失败"),

    TAG_ARTICLE_INSERT_ERROR(107, "文章及其标签关系保存到数据库失败"),

    TAG_ARTICLE_UPDATE_ERROR(108, "文章及其标签关系更新到数据库失败"),

    CATEGORY_UPDATE_ERROR(109, "种类更新失败"),

    TAG_UPDATE_ERROR(110, "标签更新失败"),

    PARROR_EMPTY_ERROR(111, "参数不为空"),

    VISIT_ERROR(112, "文章的访问量异常"),

    VISIT_UPDATE_ERROR(113, "文章的访问量更新失败"),

    VISIT_INSERT_ERROR(114, "文章浏览量创建失败"),

    THUMBUP_INSERT_ERROR(115, "文章点赞量创建失败"),

    COMMENT_INSERT_ERROR(116, "文章评论量创建失败"),

    DIR_INSERT_ERROR(117, "文件夹创建失败"),

    IMAGE_INSERT_ERROR(118, "图片创建失败"),

    IMAGE_UPDATE_ERROR(119, "图片更新失败"),

    DIR_UPDATE_ERROR(119, "文件更新失败"),

    FILE_NOT_EXIST(120, "文件不存在"),

    IMAGE_UPLOAD_ERROR(121, "图片上传失败"),

    DIR_NOT_EXIST(122, "文件夹不存在"),

    IMAGE_NOT_EXIST(123, "图片不存在"),

    THUMBUP_UPDATE_ERROR(124, "点赞量更新失败"),

    CATEGORY_TAG_NOT_EXIST(125, "种类及其标签不存在"),

    ARTICLE_NOT_EXIST(126, "文章不存在"),

    REVIEW_INSERT_ERROR(127, "评论保存失败"),

    USER_INSERT_ERROR(128, "用户保存失败"),

    COMMENT_UPDATE_ERROR(129, "评论量更新失败"),

    USER_CONFIG_INSERT_ERROR(130, "用户配置信息保存失败"),

    USER_CONFIG_UPDATE_ERROR(131, "用户配置信息更新失败"),

    USER_NOT_EXIST(132, "用户不存在"),

    USERCONFIG_INSERT_ERROR(133, "用户配置信息保存失败"),


    USER_CONFIG_NOT_EXIST(134, "用户配置信息不存在"),


    REVIEW_UPDATE_ERROR(135, "评论更新失败"),


    GUESTBOOK_INSERT_ERROR(136, "留言保存失败"),

    GUESTBOOK_UPDATE_ERROR(137, "留言更新失败"),

    URL_INSERT_ERROR(138, "链接保存失败"),

    URL_UPDATE_ERROR(139, "链接更新失败"),

    FILE_UPLOAD_ERROR(140, "文件上传失败")

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
