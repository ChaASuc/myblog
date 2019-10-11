package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpPermission;
import com.deschen.myblog.modules.system.entity.EpPermissionExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface EpPermissionMapper {
    long countByExample(EpPermissionExample example);

    int deleteByExample(EpPermissionExample example);

    int deleteByPrimaryKey(Long permId);

    int insert(EpPermission record);

    int insertSelective(EpPermission record);

    List<EpPermission> selectByExample(EpPermissionExample example);

    EpPermission selectByPrimaryKey(Long permId);

    Set<EpPermission> selectByAuthorities(Set<SimpleGrantedAuthority> authorities);

    int updateByExampleSelective(@Param("record") EpPermission record, @Param("example") EpPermissionExample example);

    int updateByExample(@Param("record") EpPermission record, @Param("example") EpPermissionExample example);

    int updateByPrimaryKeySelective(EpPermission record);

    int updateByPrimaryKey(EpPermission record);
}