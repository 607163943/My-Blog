package com.site.blog.my.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.my.core.pojo.po.BlogTag;
import com.site.blog.my.core.pojo.po.BlogTagCount;
import com.site.blog.my.core.util.PageQueryUtil;
import java.util.List;

public interface BlogTagMapper extends BaseMapper<BlogTag> {
    int insertSelective(BlogTag record);

    BlogTag selectByTagName(String tagName);

    List<BlogTag> findTagList(PageQueryUtil pageUtil);

    List<BlogTagCount> getTagCount();

    int getTotalTags(PageQueryUtil pageUtil);
}