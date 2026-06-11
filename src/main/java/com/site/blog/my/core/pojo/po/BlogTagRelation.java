package com.site.blog.my.core.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class BlogTagRelation {
    private Long relationId;

    private Long blogId;

    private Integer tagId;

    private Date createTime;
}