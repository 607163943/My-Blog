package com.site.blog.my.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.site.blog.my.core.mapper.BlogTagMapper;
import com.site.blog.my.core.pojo.po.BlogTag;
import com.site.blog.my.core.pojo.po.BlogTagCount;
import com.site.blog.my.core.pojo.po.BlogTagRelation;
import com.site.blog.my.core.service.TagService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements TagService {

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<BlogTag> tags = blogTagMapper.findTagList(pageUtil);
        int total = blogTagMapper.getTotalTags(pageUtil);
        return new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public Long getTotalTags() {
        return lambdaQuery().count();
    }

    @Override
    public Boolean saveTag(String tagName) {
        BlogTag tag = lambdaQuery()
                .eq(BlogTag::getTagName, tagName)
                .one();
        if (tag != null) {
            return false;
        }

        BlogTag blogTag = new BlogTag();
        blogTag.setTagName(tagName);
        return blogTagMapper.insertSelective(blogTag) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        //已存在关联关系不删除
        boolean exists = Db.lambdaQuery(BlogTagRelation.class)
                .in(BlogTagRelation::getTagId, (Object[]) ids)
                .exists();
        if (exists) {
            return false;
        }
        //删除tag
        return lambdaUpdate()
                .set(BlogTag::getIsDeleted, 1)
                .in(BlogTag::getTagId, (Object[]) ids)
                .update();
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return blogTagMapper.getTagCount();
    }
}
