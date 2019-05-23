package com.deschen.myblog.modules.system.dto;

import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.Tag;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/23
 * @Description 种类及标签
 * @Since 1.0.0
 */
@Data
public class CategoryDto extends Category implements Serializable {

    private List<Tag> tags;
}
