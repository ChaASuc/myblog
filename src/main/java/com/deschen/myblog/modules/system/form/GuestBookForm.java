package com.deschen.myblog.modules.system.form;

import com.deschen.myblog.core.serializer.Long2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author deschen
 * @Create 2019/6/4
 * @Description
 * @Since 1.0.0
 */
@Data
public class GuestBookForm {

    @NotBlank(message = "内容不为空")
    private String guestbookContent;

    @NotBlank(message = "用户名不为空")
    private String userName;

    private String email;

    private String guestbookUrl;
}
