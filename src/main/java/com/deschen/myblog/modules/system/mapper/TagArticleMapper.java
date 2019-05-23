package com.deschen.myblog.modules.system.mapper;

import com.deschen.myblog.modules.system.entity.Tag;
import com.deschen.myblog.modules.system.entity.TagArticle;
import com.deschen.myblog.modules.system.entity.TagArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagArticleMapper {
    long countByExample(TagArticleExample example);

    int deleteByExample(TagArticleExample example);

    int deleteByPrimaryKey(Long tagArticleId);

    int insert(TagArticle record);

    int insertSelective(TagArticle record);

    int insertTagArticlesSelective(@Param("tagArticles") List<TagArticle> tagArticles);

    List<TagArticle> selectByExample(TagArticleExample example);

    TagArticle selectByPrimaryKey(Long tagArticleId);

    int updateByExampleSelective(@Param("record") TagArticle record, @Param("example") TagArticleExample example);

    int updateByExample(@Param("record") TagArticle record, @Param("example") TagArticleExample example);

    int updateByPrimaryKeySelective(TagArticle record);

    int updateByPrimaryKey(TagArticle record);

    List<Tag> selectTagsByArticleId(
            @Param("articleId") Long articleId, @Param("state") Integer state);
}