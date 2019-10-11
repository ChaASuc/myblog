package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.dto.ArticleDto;
import com.deschen.myblog.modules.system.entity.Category;
import com.deschen.myblog.modules.system.entity.CategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Long categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    // 自定义sql
    int insertCategorysSelective(@Param("categories") List<Category> categories);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Long categoryId);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

}