package com.deschen.myblog.modules.system.vo;

import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @Author deschen
 * @Create 2019/6/2
 * @Description
 * @Since 1.0.0
 */
@Data
public class ReviewVO {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long reviewId;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long userId;

    private String userName;

    private Date updateTime;

    private String reviewUrl;

    private String imageUrl;
}
