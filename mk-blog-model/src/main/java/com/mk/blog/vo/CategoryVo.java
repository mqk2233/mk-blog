package com.mk.blog.vo;

import com.mk.blog.validator.groups.Insert;
import com.mk.blog.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author MK
 */
@Data
public class CategoryVo {

    @Schema(description = "唯一标识")
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class, message = "分类id不能为空")
    private Long id;

    @Schema(description = "父id")
    @NotNull(message = "分类父id不能为空")
    private Long pid;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "分类名")
    @NotBlank(message = "分类名不能为空")
    private String categoryName;

    @Schema(description = "排序标识")
    @NotNull( message = "排序标识不能为空")
    private Integer sort;


    @Schema(description = "是否删除(0-未删除， 1-删除)")
    private Integer isDeleted;
}
