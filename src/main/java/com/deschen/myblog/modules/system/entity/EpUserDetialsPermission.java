package com.deschen.myblog.modules.system.entity;
import lombok.Data;

import java.util.Date;

@Data
public class EpUserDetialsPermission{
    private Long permId;

    private Integer reqMethod;

    private String uri;
}
