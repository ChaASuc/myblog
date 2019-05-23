package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.entity.TagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {
    long countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(Long tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    int insertTagsSelective(@Param("tags") List<Tag> tags);

    List<Tag> selectByExample(TagExample example);

    Tag selectByPrimaryKey(Long tagId);

    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}