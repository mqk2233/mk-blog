package com.mk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @describe 角色表实体类
 * @author MK
 * @date 2021-01-29 14:45:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role")
@Schema(title = "Role对象", description = "角色表")
public class Role implements Serializable{

private static final long serialVersionUID=1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
        private Long id;

    @Schema(description = "角色名")
    @TableField("role_name")
    private String roleName;

    @Schema(description = "备注")
    @TableField("remarks")
    private String remarks;

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