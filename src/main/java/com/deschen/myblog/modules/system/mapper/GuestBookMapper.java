package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.GuestBook;
import com.deschen.myblog.modules.system.entity.GuestBookExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GuestBookMapper {
    long countByExample(GuestBookExample example);

    int deleteByExample(GuestBookExample example);

    int deleteByPrimaryKey(Long guestbookId);

    int insert(GuestBook record);

    int insertSelective(GuestBook record);

    List<GuestBook> selectByExample(GuestBookExample example);

    GuestBook selectByPrimaryKey(Long guestbookId);

    int updateByExampleSelective(@Param("record") GuestBook record, @Param("example") GuestBookExample example);

    int updateByExample(@Param("record") GuestBook record, @Param("example") GuestBookExample example);

    int updateByPrimaryKeySelective(GuestBook record);

    int updateByPrimaryKey(GuestBook record);
}