package com.mk.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author MK
 * @describe 标签表实体类
 */
@Data
public class LabelListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "标签名")
    private String labelName;

    @Schema(description = "排序标识")
    private Integer sort;

    @Schema(description = "是否删除(0-未删除， 1-删除)")
    private Integer isDeleted;

}