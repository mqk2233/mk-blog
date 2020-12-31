package com.mk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MK
 * @date 2020-12-24 01:46:10
 * @describe 文章表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article")
@Schema(title = "Article对象", description = "文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
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

    @Schema(description = "写作时间")
    @TableField("write_time")
    private Date writeTime;

    @Schema(description = "更新时间")
    @TableField("modify_time")
    private Date modifyTime;

    @Schema(description = "是否启用")
    @TableField("is_prohibit")
    private Integer isProhibit;


}