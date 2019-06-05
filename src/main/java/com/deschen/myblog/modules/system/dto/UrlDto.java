package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.modules.system.entity.Url;
import lombok.Data;

/**
 * @Author deschen
 * @Create 2019/6/5
 * @Description
 * @Since 1.0.0
 */
@Data
public class UrlDto extends Url {

    private String imageUrl;
}
