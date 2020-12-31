package com.mk.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author MK
 * @date 2020/12/24
 */
@Data
public class ArticleVo {

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
    private Date writeTime;

    @Schema(description = "更新时间")
    private Date modifyTime;

    @Schema(description = "是否启用")
    private Integer isProhibit;
}
