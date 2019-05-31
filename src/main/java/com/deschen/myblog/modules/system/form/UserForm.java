package com.deschen.myblog.modules.system.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author deschen
 * @Create 2019/5/31
 * @Description
 * @Since 1.0.0
 */
@Data
public class UserForm {

    @NotBlank(message = "用户名不为空")
    private String userName;

    @NotBlank(message = "用户密码不为空")
    private String userPassword;

    private String email;
}
