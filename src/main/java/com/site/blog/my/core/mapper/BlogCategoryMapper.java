package com.site.blog.my.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.blog.my.core.pojo.po.BlogCategory;
import com.site.blog.my.core.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {
    BlogCategory selectByCategoryName(String categoryName);

    List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

    List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    int getTotalCategories(PageQueryUtil pageUtil);
}