package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mk.blog.entity.ArticleLabel;

import java.util.List;

/**
 * @describe 文章标签中间表mapper类
 * @author MK
 * @date 2021-01-29 14:44:58
 */
public interface ArticleLabelMapper extends BaseMapper<ArticleLabel> {

    /**
     * 通过文章id查找分类
     *
     * @param articleId 文章id
     * @return {@link String }
     * @author MK
     * @date 2021/2/19 11:13
     */
    List<String> selectLabelName(Long articleId);
}
