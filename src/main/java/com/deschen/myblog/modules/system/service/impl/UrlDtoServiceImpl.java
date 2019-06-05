package com.deschen.myblog.modules.system.service.impl;

import com.deschen.myblog.core.constants.BlogConstant;
import com.deschen.myblog.core.enums.BlogEnum;
import com.deschen.myblog.core.exceptions.GlobalException;
import com.deschen.myblog.core.utils.IdWorker;
import com.deschen.myblog.modules.system.entity.Url;
import com.deschen.myblog.modules.system.entity.UrlExample;
import com.deschen.myblog.modules.system.mapper.UrlMapper;
import com.deschen.myblog.modules.system.service.UrlDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/6/5
 * @Description
 * @Since 1.0.0
 */
@Service
@Slf4j
public class UrlDtoServiceImpl implements UrlDtoService {

    @Autowired
    private UrlMapper urlMapper;

    @Override

    /**
     * @Param: [url]
     * @Return:java.lang.Long
     * @Author: deschen
     * @Date: 2019/6/5 0:59
     * @Description: 创建链接
     */
    @Transactional
    public Long insertUrl(Url url) {
        long urlId = new IdWorker().nextId();
        url.setUrlId(urlId);
        int success = urlMapper.insertSelective(url);
        if (success == 0) {
            throw new GlobalException(BlogEnum.URL_INSERT_ERROR);
        }
        return urlId;
    }

    @Override

    /**
     * @Param: [urls]
     * @Return:void
     * @Author: deschen
     * @Date: 2019/6/5 0:59
     * @Description: 批量更新链接
     */
    @Transactional
    public void updateUrl(List<Url> urls) {
        urls.stream().forEach(
                url -> {
                    int success = urlMapper.updateByPrimaryKeySelective(url);
                    if (success == 0) {
                        throw new GlobalException(BlogEnum.URL_UPDATE_ERROR);
                    }
                }
        );
    }

    @Override
    public List<Url> selectUrlDto(Integer state, Integer sort) {
        UrlExample urlExample = new UrlExample();
        if (sort == null || sort.equals(BlogConstant.DESC)) {
            urlExample.setOrderByClause("UPDATE_TIME DESC");
        }else {
            urlExample.setOrderByClause("UPDATE_TIME ASC");
        }

        if (state != null) {
            urlExample.createCriteria().andStateEqualTo(state);
        }

        List<Url> urls = urlMapper.selectByExample(urlExample);
        return urls;
    }

    @Override
    public Url selectUrlById(Long urlId) {
        Url url = urlMapper.selectByPrimaryKey(urlId);
        return url;
    }
}
