package com.site.blog.my.core.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_config")
@Data
public class BlogConfig {
    @TableId
    private String configName;

    private String configValue;

    private Date createTime;

    private Date updateTime;
}