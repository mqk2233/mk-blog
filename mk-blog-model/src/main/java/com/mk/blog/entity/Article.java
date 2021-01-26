package com.mk.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author MK
 * @date 2021-01-25 18:00:56
 * @describe 文章表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
@Schema(title = "Article对象", description = "文章表")
public class Article implements Serializable{

private static final long serialVersionUID=1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
        private Long id;

    @Schema(description = "分类id")
    @TableField("category_id")
    private Long categoryId;

    @Schema(description = "文章标题")
    @TableField("title")
    private String title;

    @Schema(description = "文章图片")
    @TableField("img")
    private String img;

    @Schema(description = "文章内容.md")
    @TableField("content_md")
    private String contentMd;

    @Schema(description = "文章内容.html")
    @TableField("content_html")
    private String contentHtml;

    @Schema(description = "状态（0-保存，1-发布）")
    @TableField("status")
    private Integer status;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

    @Schema(description = "是否删除(0-未删除， 1-删除)")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}