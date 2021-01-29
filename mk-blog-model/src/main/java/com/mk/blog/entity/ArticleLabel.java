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
 * @describe 文章标签中间表实体类
 * @author MK
 * @date 2021-01-29 14:44:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article_label")
@Schema(title = "ArticleLabel对象", description = "文章标签中间表")
public class ArticleLabel implements Serializable{

private static final long serialVersionUID=1L;

    @Schema(description = "唯一标识")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
        private Long id;

    @Schema(description = "文章id")
    @TableField("article_id")
    private Long articleId;

    @Schema(description = "标签id")
    @TableField("label_id")
    private Long labelId;

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