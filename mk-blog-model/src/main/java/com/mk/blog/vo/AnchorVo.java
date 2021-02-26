package com.mk.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MK
 * @date 2021/2/24
 */
@Data
public class AnchorVo {

    private String id;

    private String title;

    private Integer grade;

    private Integer sort;

    List<AnchorVo> childList;
}
