package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpUserRole;
import com.deschen.myblog.modules.system.entity.EpUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpUserRoleMapper {
    long countByExample(EpUserRoleExample example);

    int deleteByExample(EpUserRoleExample example);

    int deleteByPrimaryKey(Long uRId);

    int insert(EpUserRole record);

    int insertSelective(EpUserRole record);

    List<EpUserRole> selectByExample(EpUserRoleExample example);

    EpUserRole selectByPrimaryKey(Long uRId);

    int updateByExampleSelective(@Param("record") EpUserRole record, @Param("example") EpUserRoleExample example);

    int updateByExample(@Param("record") EpUserRole record, @Param("example") EpUserRoleExample example);

    int updateByPrimaryKeySelective(EpUserRole record);

    int updateByPrimaryKey(EpUserRole record);
}