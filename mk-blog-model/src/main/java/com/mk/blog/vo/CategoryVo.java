package com.mk.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author MK
 */
@Data
public class CategoryVo {

    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "父id")
    private Long pid;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "分类名")
    private String categoryName;

    @Schema(description = "排序标识")
    private Integer sort;


    @Schema(description = "是否删除(0-未删除， 1-删除)")
    private Integer isDeleted;
}
