package com.site.blog.my.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.my.core.pojo.po.BlogLink;
import com.site.blog.my.core.util.PageQueryUtil;

import java.util.List;

public interface BlogLinkMapper extends BaseMapper<BlogLink> {
    List<BlogLink> findLinkList(PageQueryUtil pageUtil);

    int getTotalLinks(PageQueryUtil pageUtil);
}