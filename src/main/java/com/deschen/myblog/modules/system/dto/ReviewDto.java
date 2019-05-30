package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.core.serializer.Date2LongSerializer;
import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.deschen.myblog.modules.system.entity.Review;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.io.Serializable;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
@Data
public class ReviewDto extends Review implements Serializable {

    private String userName;

    private String email;

    private String reviewUrl;

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long imageId;

    private List<ReviewDto> reviewDtos;
}
