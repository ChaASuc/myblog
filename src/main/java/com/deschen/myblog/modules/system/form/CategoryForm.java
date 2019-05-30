package com.deschen.myblog.modules.system.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author deschen
 * @Create 2019/5/29
 * @Description
 * @Since 1.0.0
 */
@Data
public class CategoryForm implements Serializable {

    private Long categoryId;

    private String categoryName;

    private Integer state;
}
