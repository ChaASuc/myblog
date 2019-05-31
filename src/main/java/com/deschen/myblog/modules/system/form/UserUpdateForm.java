package com.deschen.myblog.modules.system.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author deschen
 * @Create 2019/5/31
 * @Description
 * @Since 1.0.0
 */
@Data
public class UserUpdateForm {

    private Long userId;

    private String userName;

    private String userPassword;

    private Long imageId;

    private String email;

    private String userSignature;

    private Integer state;

}
