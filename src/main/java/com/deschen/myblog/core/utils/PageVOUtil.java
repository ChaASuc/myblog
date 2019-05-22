package com.deschen.myblog.core.utils;

import com.deschen.myblog.modules.system.vo.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/5/14
 * @Description Page与PageVO转换工具 todo 优化 做出像BaseConverter公用工具
 * @Since 1.0.0
 */
@Slf4j
public class PageVOUtil<POJO, VO>{



    public PageVO<VO> convert(Page<POJO> from, List<VO> toList) {
        PageVO<VO> to = new PageVO<>();
        to.setTotalPages(from.getTotalPages());
        to.setNumber(from.getNumber()+1);
        to.setTotalElements(from.getTotalElements());
        to.setContent(toList);
        return to;
    }

}
