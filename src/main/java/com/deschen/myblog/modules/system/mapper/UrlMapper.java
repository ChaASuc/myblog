package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.Url;
import com.deschen.myblog.modules.system.entity.UrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrlMapper {
    long countByExample(UrlExample example);

    int deleteByExample(UrlExample example);

    int deleteByPrimaryKey(Long urlId);

    int insert(Url record);

    int insertSelective(Url record);

    List<Url> selectByExample(UrlExample example);

    Url selectByPrimaryKey(Long urlId);

    int updateByExampleSelective(@Param("record") Url record, @Param("example") UrlExample example);

    int updateByExample(@Param("record") Url record, @Param("example") UrlExample example);

    int updateByPrimaryKeySelective(Url record);

    int updateByPrimaryKey(Url record);
}