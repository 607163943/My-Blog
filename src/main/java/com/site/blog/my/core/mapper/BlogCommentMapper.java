package com.site.blog.my.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.my.core.pojo.po.BlogComment;

import java.util.List;
import java.util.Map;

public interface BlogCommentMapper extends BaseMapper<BlogComment> {
    List<BlogComment> findBlogCommentList(Map<String, Object> map);

    int getTotalBlogComments(Map<String, Object> map);
}