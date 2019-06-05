package com.deschen.myblog.modules.system.service;

import com.deschen.myblog.modules.system.entity.Url;

import java.util.List;

/**
 * @Author deschen
 * @Create 2019/6/5
 * @Description
 * @Since 1.0.0
 */
public interface UrlDtoService {

    Long insertUrl(Url url);

    void updateUrl(List<Url> urls);

    List<Url> selectUrlDto(Integer state, Integer sort);

    Url selectUrlById(Long urlId);
}
