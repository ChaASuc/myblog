package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.modules.system.entity.Review;
import lombok.Data;

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

    private String imageUrl;

    private String replierName;

    private List<ReviewDto> reviewDtos;
}
