package com.deschen.myblog.modules.system.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description
 * @Since 1.0.0
 */
@Data
public class ReviewForm implements Serializable {

    private Long reviewId;

    @NotBlank(message = "评论内容不为空")
    private String reviewContent;

    @NotNull(message = "文章id不为空")
    private Long articleId;

    private Long reviewParent;

    @NotBlank(message = "用户名不为空")
    private String userName;

    private String email;

    private String reviewUrl;
}
