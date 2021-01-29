package com.mk.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MK
 * @date 2021-01-10 20:13:02
 * @describe 用户表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@Schema(title = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
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

    @Schema(description = "是否启用")
    @TableField("is_prohibit")
    private Integer isProhibit;

    @Schema(description = "更新时间")
    @TableField("modify_time")
    private Date modifyTime;

    @Schema(description = "创建时间")
    @TableField("caeate_time")
    private Date caeateTime;


}