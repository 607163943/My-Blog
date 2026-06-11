package com.site.blog.my.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.my.core.mapper.BlogLinkMapper;
import com.site.blog.my.core.pojo.po.BlogLink;
import com.site.blog.my.core.service.LinkService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl extends ServiceImpl<BlogLinkMapper, BlogLink> implements LinkService {

    @Autowired
    private BlogLinkMapper blogLinkMapper;

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<BlogLink> links = blogLinkMapper.findLinkList(pageUtil);
        int total = blogLinkMapper.getTotalLinks(pageUtil);
        return new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public Long getTotalLinks() {
        return lambdaQuery().count();
    }

    @Override
    public Boolean saveLink(BlogLink link) {
        return save(link);
    }

    @Override
    public BlogLink selectById(Integer id) {
        return getById(id);
    }

    @Override
    public Boolean updateLink(BlogLink tempLink) {
        return updateById(tempLink);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return lambdaUpdate()
                .set(BlogLink::getIsDeleted, 1)
                .in(BlogLink::getLinkId, (Object) ids)
                .update();
    }

    @Override
    public Map<Byte, List<BlogLink>> getLinksForLinkPage() {
        //获取所有链接数据
        List<BlogLink> links = blogLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(links)) {
            //根据type进行分组
            return links.stream().collect(Collectors.groupingBy(BlogLink::getLinkType));
        }
        return null;
    }
}
