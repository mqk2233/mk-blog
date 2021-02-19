package com.mk.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author MK
 * @date 2021/2/19
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
