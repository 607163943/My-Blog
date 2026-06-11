package com.site.blog.my.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.site.blog.my.core.pojo.po.BlogCategory;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;

public interface CategoryService extends IService<BlogCategory> {

    /**
     * 查询分类的分页数据
     *
     * @param pageUtil 查询参数
     * @return 分类列表
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    Long getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName 分类名
     * @param categoryIcon 分类图标
     * @return 添加结果
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    List<BlogCategory> getAllCategories();
}
