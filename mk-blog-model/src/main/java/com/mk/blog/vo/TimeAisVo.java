package com.mk.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MK
 */
@Data
public class TimeAisVo {

    private String month;

    private List<ArticleVo> articles;

    public TimeAisVo(String month, List<ArticleVo> articles) {
        this.month = month;
        this.articles = articles;
    }
}
