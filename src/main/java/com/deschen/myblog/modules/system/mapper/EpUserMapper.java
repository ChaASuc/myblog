package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.EpUser;
import com.deschen.myblog.modules.system.entity.EpUserDetails;
import com.deschen.myblog.modules.system.entity.EpUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EpUserMapper {
    long countByExample(EpUserExample example);

    int deleteByExample(EpUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(EpUser record);

    int insertSelective(EpUser record);

    List<EpUser> selectByExample(EpUserExample example);

    EpUser selectByPrimaryKey(Long userId);

    EpUserDetails selectByUsername(String username);

    int updateByExampleSelective(@Param("record") EpUser record, @Param("example") EpUserExample example);

    int updateByExample(@Param("record") EpUser record, @Param("example") EpUserExample example);

    int updateByPrimaryKeySelective(EpUser record);

    int updateByPrimaryKey(EpUser record);
}