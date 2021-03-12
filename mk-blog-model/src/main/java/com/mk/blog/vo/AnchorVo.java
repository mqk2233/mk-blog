package com.mk.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MK
 */
@Data
public class AnchorVo {

    private String id;

    private String title;

    private Integer grade;

    private Integer sort;

    List<AnchorVo> childList;
}
