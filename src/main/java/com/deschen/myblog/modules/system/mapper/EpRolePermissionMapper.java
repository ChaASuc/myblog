package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpRolePermission;
import com.deschen.myblog.modules.system.entity.EpRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpRolePermissionMapper {
    long countByExample(EpRolePermissionExample example);

    int deleteByExample(EpRolePermissionExample example);

    int deleteByPrimaryKey(Long rPId);

    int insert(EpRolePermission record);

    int insertSelective(EpRolePermission record);

    List<EpRolePermission> selectByExample(EpRolePermissionExample example);

    EpRolePermission selectByPrimaryKey(Long rPId);

    int updateByExampleSelective(@Param("record") EpRolePermission record, @Param("example") EpRolePermissionExample example);

    int updateByExample(@Param("record") EpRolePermission record, @Param("example") EpRolePermissionExample example);

    int updateByPrimaryKeySelective(EpRolePermission record);

    int updateByPrimaryKey(EpRolePermission record);
}