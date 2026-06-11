package com.site.blog.my.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.site.blog.my.core.pojo.po.BlogComment;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

public interface CommentService extends IService<BlogComment> {
    /**
     * 添加评论
     *
     * @param blogComment 评论内容
     * @return 添加结果
     */
    Boolean addComment(BlogComment blogComment);

    /**
     * 后台管理系统中评论分页功能
     *
     * @param pageUtil 分页参数
     * @return 评论列表
     */
    PageResult getCommentsPage(PageQueryUtil pageUtil);

    int getTotalComments();

    /**
     * 批量审核
     *
     * @param ids 评论id数组
     * @return 审核结果
     */
    Boolean checkDone(Integer[] ids);

    /**
     * 批量删除
     *
     * @param ids 评论id数组
     * @return 删除结果
     */
    Boolean deleteBatch(Integer[] ids);

    /**
     * 添加回复
     *
     * @param commentId 评论id
     * @param replyBody 回复内容
      * @return 回复结果
     */
    Boolean reply(Long commentId, String replyBody);

    /**
     * 根据文章id和分页参数获取文章的评论列表
     *
     * @param blogId 文章id
     * @param page 当前页码
     * @return 评论分页数据
     */
    PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page);
}
