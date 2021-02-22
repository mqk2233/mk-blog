package com.mk.blog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author MK
 */
@Data
public class CategoryListVo {

    @Schema(description = "唯一标识")
    @JsonProperty(value = "value")
    private Long id;

    @Schema(description = "父id")
    private Long pid;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "分类名")
    @JsonProperty(value = "title")
    private String categoryName;

    @Schema(description = "排序标识")
    private Integer sort;

    @Schema(description = "是否删除(0-未删除， 1-删除)")
    private Integer isDeleted;

    @Schema(description = "子分类")
    @JsonProperty(value = "children")
    private List<CategoryListVo> categoryList;

    private boolean expand = true;

    private String label;
}
