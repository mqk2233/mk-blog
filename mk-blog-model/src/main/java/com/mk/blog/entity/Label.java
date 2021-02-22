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
 * @describe 标签表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("label")
@Schema(title = "Label对象", description = "标签表")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "标签名")
    @TableField("label_name")
    private String labelName;

    @Schema(description = "排序标识")
    @TableField("sort")
    private Integer sort;

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