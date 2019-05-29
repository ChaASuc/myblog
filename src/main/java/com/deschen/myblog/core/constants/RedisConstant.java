package com.deschen.myblog.core.constants;

/**
 * @Author deschen
 * @Create 2019/4/26
 * @Description
 * @Since 1.0.0
 */
public interface RedisConstant {

    String CATEGORY_PREFIX = "category_%s";

    String TAG_PREFIX = "tag_%s";

    String VISIT_PREFIX = "visit_%s";

    String THUMBUP_PREFIX = "thumbup_%s";

    String THUMBUPIP_PREFIX = "thumbupip_%s";

    String VISITIP_PREFIX = "visitip_%s";

    String CATEGORY_TAG_PREFIX = "category_%s_tag_%s";

    String ARTICLE_PREFIX = "category_%s_tag_%s_sort_%s_article";

    String ARTICLE_CONTENT_PREFIX = "article_%s_tag_%s";



}
