package com.deschen.myblog.modules.system.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Author deschen
 * @Create 2019/6/1
 * @Description 博主配置信息筛选条件
 * @Since 1.0.0
 */
@Data
public class AuthorInfoForm {

    @NotNull(message = "用户id不为空")
    private Long userId;

    private Date date;

    private Integer sort;

    private Integer condition;
}
