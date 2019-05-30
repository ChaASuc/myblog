package com.deschen.myblog.modules.system.vo;

import com.deschen.myblog.core.serializer.Date2LongSerializer;
import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description 与前端交互的图片实体类
 * @Since 1.0.0
 */
@Data
public class ImageVO {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long imageId;

    private String imageUrl;

    private Integer state;
}
