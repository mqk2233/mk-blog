package com.mk.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author MK
 * @date 2021/2/19
 */
@Data
public class ArticleListVo {
    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "分类id")
    private Long categoryId;

    @Schema(description = "文章标题")
    private String title;

    @Schema(description = "文章图片")
    private String img;

    @Schema(description = "文章内容.md")
    private String contentMd;

    @Schema(description = "文章内容.html")
    private String contentHtml;

    @Schema(description = "状态（0-保存，1-发布）")
    private Integer status;

    @Schema(description = "写作时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date modifyTime;

    @Schema(description = "是否启用")
    private Integer isDeleted;

    private String text;

    @Schema(description = "标签id集合")
    private List<Long> labelIds;

    @Schema(description = "分类id集合")
    private List<String> categoryIds;

    @Schema(description = "标签名集合")
    private List<String> labelNames;

    @Schema(description = "分类名")
    private String categoryName;

    private String catalog;
}
