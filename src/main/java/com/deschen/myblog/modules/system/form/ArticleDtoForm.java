package com.deschen.myblog.modules.system.form;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description
 * @Since 1.0.0
 */
@Data
public class ArticleDtoForm {

    private List<Integer> states;

    private Integer sort;

    private Integer pageNum;
}
