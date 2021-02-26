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
public class LabelVo {

    @Schema(description = "唯一标识")
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class, message = "标签id不能为空")
    private Long id;

    @Schema(description = "标签名")
    @NotBlank(message = "标签名不能为空")
    private String labelName;

    @Schema(description = "排序标识")
    @NotNull(message = "排序标识不能为空")
    private Integer sort;

    @Schema(description = "是否删除(0-未删除， 1-删除)")
    private Integer isDeleted;
}
