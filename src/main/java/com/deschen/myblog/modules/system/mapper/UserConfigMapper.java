package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.UserConfig;
import com.deschen.myblog.modules.system.entity.UserConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserConfigMapper {
    long countByExample(UserConfigExample example);

    int deleteByExample(UserConfigExample example);

    int deleteByPrimaryKey(Long configId);

    int insert(UserConfig record);

    int insertSelective(UserConfig record);

    List<UserConfig> selectByExample(UserConfigExample example);

    UserConfig selectByPrimaryKey(Long configId);

    int updateByExampleSelective(@Param("record") UserConfig record, @Param("example") UserConfigExample example);

    int updateByExample(@Param("record") UserConfig record, @Param("example") UserConfigExample example);

    int updateByPrimaryKeySelective(UserConfig record);

    int updateByPrimaryKey(UserConfig record);
}