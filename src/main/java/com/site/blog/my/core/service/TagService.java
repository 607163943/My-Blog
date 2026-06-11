package com.site.blog.my.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.site.blog.my.core.pojo.po.BlogTag;
import com.site.blog.my.core.pojo.po.BlogTagCount;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;

public interface TagService extends IService<BlogTag> {

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil 查询条件
     * @return 分页数据
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Long getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();
}
