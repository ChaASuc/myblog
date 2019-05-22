package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.Thumbup;
import com.deschen.myblog.modules.system.entity.ThumbupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThumbupMapper {
    long countByExample(ThumbupExample example);

    int deleteByExample(ThumbupExample example);

    int deleteByPrimaryKey(Long thumbupId);

    int insert(Thumbup record);

    int insertSelective(Thumbup record);

    List<Thumbup> selectByExample(ThumbupExample example);

    Thumbup selectByPrimaryKey(Long thumbupId);

    int updateByExampleSelective(@Param("record") Thumbup record, @Param("example") ThumbupExample example);

    int updateByExample(@Param("record") Thumbup record, @Param("example") ThumbupExample example);

    int updateByPrimaryKeySelective(Thumbup record);

    int updateByPrimaryKey(Thumbup record);
}