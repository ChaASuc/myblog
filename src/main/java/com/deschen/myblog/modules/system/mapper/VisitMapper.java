package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.Visit;
import com.deschen.myblog.modules.system.entity.VisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VisitMapper {
    long countByExample(VisitExample example);

    int deleteByExample(VisitExample example);

    int deleteByPrimaryKey(Long visitId);

    int insert(Visit record);

    int insertSelective(Visit record);

    List<Visit> selectByExample(VisitExample example);

    Visit selectByPrimaryKey(Long visitId);

    int updateByExampleSelective(@Param("record") Visit record, @Param("example") VisitExample example);

    int updateByExample(@Param("record") Visit record, @Param("example") VisitExample example);

    int updateByPrimaryKeySelective(Visit record);

    int updateByPrimaryKey(Visit record);
}