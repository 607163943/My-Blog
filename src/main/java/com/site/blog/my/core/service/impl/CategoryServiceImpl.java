package com.site.blog.my.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.site.blog.my.core.mapper.BlogCategoryMapper;
import com.site.blog.my.core.pojo.po.Blog;
import com.site.blog.my.core.pojo.po.BlogCategory;
import com.site.blog.my.core.service.CategoryService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements CategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<BlogCategory> categoryList = blogCategoryMapper.findCategoryList(pageUtil);
        int total = blogCategoryMapper.getTotalCategories(pageUtil);
        return new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public Long getTotalCategories() {
        return lambdaQuery().count();
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        BlogCategory category = lambdaQuery()
                .eq(BlogCategory::getCategoryName, categoryName)
                .one();
        if (category == null) {
            BlogCategory blogCategory = new BlogCategory();
            blogCategory.setCategoryName(categoryName);
            blogCategory.setCategoryIcon(categoryIcon);
            return save(blogCategory);
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory blogCategory = getById(categoryId);
        if (blogCategory == null) {
            return false;
        }

        blogCategory.setCategoryIcon(categoryIcon);
        blogCategory.setCategoryName(categoryName);
        //修改分类实体
        Db.lambdaUpdate(Blog.class)
                .set(Blog::getBlogCategoryName, categoryName)
                .eq(Blog::getBlogCategoryId, categoryId)
                .update();
        return updateById(blogCategory);
    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        Db.lambdaUpdate(Blog.class)
                .set(Blog::getBlogCategoryName, "默认分类")
                .set(Blog::getBlogCategoryId, 0)
                .in(Blog::getBlogCategoryId, (Object[]) ids)
                .update();
        //删除分类数据
        return lambdaUpdate().set(BlogCategory::getIsDeleted, 1)
                .in(BlogCategory::getCategoryId, (Object[]) ids)
                .update();
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return lambdaQuery()
                .orderByDesc(BlogCategory::getCategoryRank)
                .orderByDesc(BlogCategory::getCreateTime)
                .list();
    }

}
