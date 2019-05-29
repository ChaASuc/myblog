package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.Dir;
import com.deschen.myblog.modules.system.entity.DirExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirMapper {
    long countByExample(DirExample example);

    int deleteByExample(DirExample example);

    int deleteByPrimaryKey(Long dirId);

    int insert(Dir record);

    int insertSelective(Dir record);

    List<Dir> selectByExample(DirExample example);

    Dir selectByPrimaryKey(Long dirId);

    int updateByExampleSelective(@Param("record") Dir record, @Param("example") DirExample example);

    int updateByExample(@Param("record") Dir record, @Param("example") DirExample example);

    int updateByPrimaryKeySelective(Dir record);

    int updateByPrimaryKey(Dir record);
}