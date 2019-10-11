package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpDept;
import com.deschen.myblog.modules.system.entity.EpDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpDeptMapper {
    long countByExample(EpDeptExample example);

    int deleteByExample(EpDeptExample example);

    int deleteByPrimaryKey(Long deptId);

    int insert(EpDept record);

    int insertSelective(EpDept record);

    List<EpDept> selectByExample(EpDeptExample example);

    EpDept selectByPrimaryKey(Long deptId);

    int updateByExampleSelective(@Param("record") EpDept record, @Param("example") EpDeptExample example);

    int updateByExample(@Param("record") EpDept record, @Param("example") EpDeptExample example);

    int updateByPrimaryKeySelective(EpDept record);

    int updateByPrimaryKey(EpDept record);
}