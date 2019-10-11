package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpRole;
import com.deschen.myblog.modules.system.entity.EpRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpRoleMapper {
    long countByExample(EpRoleExample example);

    int deleteByExample(EpRoleExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(EpRole record);

    int insertSelective(EpRole record);

    List<EpRole> selectByExample(EpRoleExample example);

    EpRole selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("record") EpRole record, @Param("example") EpRoleExample example);

    int updateByExample(@Param("record") EpRole record, @Param("example") EpRoleExample example);

    int updateByPrimaryKeySelective(EpRole record);

    int updateByPrimaryKey(EpRole record);
}