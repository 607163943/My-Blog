package com.site.blog.my.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.my.core.pojo.po.BlogTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogTagRelationMapper extends BaseMapper<BlogTagRelation> {

    BlogTagRelation selectByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Integer tagId);

    List<Long> selectDistinctTagIds(Integer[] tagIds);


    int batchInsert(@Param("relationList") List<BlogTagRelation> blogTagRelationList);

    int deleteByBlogId(Long blogId);
}