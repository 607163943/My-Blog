package com.site.blog.my.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.site.blog.my.core.pojo.po.BlogLink;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

import java.util.List;
import java.util.Map;

public interface LinkService extends IService<BlogLink> {
    /**
     * 查询友链的分页数据
     *
     * @param pageUtil 分页参数
     * @return 分页数据
     */
    PageResult getBlogLinkPage(PageQueryUtil pageUtil);

    Long getTotalLinks();

    Boolean saveLink(BlogLink link);

    BlogLink selectById(Integer id);

    Boolean updateLink(BlogLink tempLink);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回友链页面所需的所有数据
     *
     * @return 友链数据Map集合
     */
    Map<Byte, List<BlogLink>> getLinksForLinkPage();
}
