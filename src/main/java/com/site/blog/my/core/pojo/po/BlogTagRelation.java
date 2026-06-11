package com.site.blog.my.core.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_blog_tag_relation")
@Data
public class BlogTagRelation {
    @TableId
    private Long relationId;

    private Long blogId;

    private Integer tagId;

    private Date createTime;
}