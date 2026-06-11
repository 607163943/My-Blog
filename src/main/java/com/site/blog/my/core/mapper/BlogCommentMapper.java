package com.site.blog.my.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.my.core.pojo.po.BlogComment;
import java.util.List;
import java.util.Map;

public interface BlogCommentMapper extends BaseMapper<BlogComment> {
    int deleteByPrimaryKey(Long commentId);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Long commentId);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);

    List<BlogComment> findBlogCommentList(Map map);

    int getTotalBlogComments(Map map);

    int checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
}