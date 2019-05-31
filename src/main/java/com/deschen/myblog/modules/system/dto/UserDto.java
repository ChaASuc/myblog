package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author deschen
 * @Create 2019/5/31
 * @Description
 * @Since 1.0.0
 */
@Data
public class UserDto implements Serializable {

    @JsonSerialize(using = Long2StringSerializer.class)
    private Long userId;

    private String userName;


    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    //@JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private String imageUrl;

    private String email;

    private Integer visitSum;

    private Integer thumbupSum;

    private Integer commentSum;

    private String userSignature;
}
