package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.modules.system.entity.GuestBook;
import lombok.Data;
import org.springframework.context.annotation.Primary;

/**
 * @Author deschen
 * @Create 2019/6/3
 * @Description 留言板dto实体类
 * @Since 1.0.0
 */
@Data
public class GuestBookDto extends GuestBook {

    private String userName;

    private String imageUrl;

    private String email;

}
