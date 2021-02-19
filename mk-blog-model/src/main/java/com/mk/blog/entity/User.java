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
 * @describe 用户表实体类
 * @date 2021-01-30 17:11:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Schema(title = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "用户名")
    @TableField("user_name")
    private String userName;

    @Schema(description = "密码")
    @TableField("pass_word")
    private String passWord;

    @Schema(description = "备注")
    @TableField("remarks")
    private String remarks;

    @Schema(description = "头像")
    @TableField("img_head")
    private String imgHead;

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