package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpPermission;
import com.deschen.myblog.modules.system.entity.EpPermissionExample;
import java.util.List;
import java.util.Set;

import com.deschen.myblog.modules.system.entity.EpUserDetialsPermission;
import org.apache.ibatis.annotations.Param;

public interface EpPermissionMapper {
    int countByExample(EpPermissionExample example);

    int deleteByExample(EpPermissionExample example);

    int deleteByPrimaryKey(Long permId);

    int insert(EpPermission record);

    int insertSelective(EpPermission record);

    List<EpPermission> selectByExample(EpPermissionExample example);

    EpPermission selectByPrimaryKey(Long permId);

    int updateByExampleSelective(@Param("record") EpPermission record, @Param("example") EpPermissionExample example);

    int updateByExample(@Param("record") EpPermission record, @Param("example") EpPermissionExample example);

    int updateByPrimaryKeySelective(EpPermission record);

    int updateByPrimaryKey(EpPermission record);

    Set<EpUserDetialsPermission> selectByRoleName(String commonRoleName);
}