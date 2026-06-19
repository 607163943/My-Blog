package com.site.blog.my.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.my.core.mapper.BlogCommentMapper;
import com.site.blog.my.core.pojo.po.BlogComment;
import com.site.blog.my.core.service.CommentService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements CommentService {
    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public Boolean addComment(BlogComment blogComment) {
        return save(blogComment);
    }

    @Override
    public PageResult getCommentsPage(PageQueryUtil pageUtil) {
        List<BlogComment> comments = blogCommentMapper.findBlogCommentList(pageUtil);
        int total = blogCommentMapper.getTotalBlogComments(pageUtil);
        return new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public int getTotalComments() {
        return blogCommentMapper.getTotalBlogComments(null);
    }

    @Override
    public Boolean checkDone(Integer[] ids) {
        return lambdaUpdate()
                .set(BlogComment::getCommentStatus, 1)
                .eq(BlogComment::getCommentStatus,0)
                .in(BlogComment::getCommentId, (Object) ids)
                .update();
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return lambdaUpdate()
                .set(BlogComment::getIsDeleted, 1)
                .in(BlogComment::getCommentId, (Object) ids)
                .update();
    }

    @Override
    public Boolean reply(Long commentId, String replyBody) {
        BlogComment blogComment = getById(commentId);
        //blogComment不为空且状态为已审核，则继续后续操作
        if (blogComment != null && blogComment.getCommentStatus().intValue() == 1) {
            blogComment.setReplyBody(replyBody);
            blogComment.setReplyCreateTime(new Date());
            return updateById(blogComment);
        }
        return false;
    }

    @Override
    public PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page) {
        if (page < 1) {
            return null;
        }
        Map<String,Object> params = new HashMap<>();
        params.put("page", page);
        //每页8条
        params.put("limit", 8);
        params.put("blogId", blogId);
        params.put("commentStatus", 1);//过滤审核通过的数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<BlogComment> comments = blogCommentMapper.findBlogCommentList(pageUtil);
        if (!CollectionUtils.isEmpty(comments)) {
            int total = blogCommentMapper.getTotalBlogComments(pageUtil);
            return new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
        }
        return null;
    }
}
