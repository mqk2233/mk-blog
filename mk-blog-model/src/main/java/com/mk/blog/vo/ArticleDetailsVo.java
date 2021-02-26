package com.mk.blog.vo;

import com.mk.blog.validator.groups.Insert;
import com.mk.blog.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author MK
 */
@Data
public class ArticleDetailsVo {

    @Schema(description = "唯一标识")
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class, message = "文章id不能为空")
    private Long id;

    @Schema(description = "分类id")
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    @Schema(description = "文章标题")
    @NotNull(message = "文章标题不能为空")
    private String title;

    @Schema(description = "文章图片")
    private String img;

    @Schema(description = "文章内容.md")
    @NotNull(message = "文章内容不能为空")
    private String contentMd;

    @Schema(description = "文章内容.html")
    private String contentHtml;

    @Schema(description = "状态（0-保存，1-发布）")
    private Integer status;


    @Schema(description = "是否启用")
    private Integer isDeleted;

    @Schema(description = "标签id集合")
    @NotEmpty(message = "标签id集合不能为空")
    private List<Long> labelIds;

    @Schema(description = "分类id集合")
    private List<Long> categoryIds;

    @Schema(description = "标签名集合")
    private List<String> labelNames;

    @Schema(description = "分类名")
    private String categoryName;


    @Schema(description = "目录树")
    private List<AnchorVo> catalogTree;
}
