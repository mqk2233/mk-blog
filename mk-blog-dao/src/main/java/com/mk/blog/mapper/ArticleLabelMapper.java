package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mk.blog.entity.ArticleLabel;

import java.util.List;

/**
 * @author MK
 * @describe 文章标签中间表mapper类
 */
public interface ArticleLabelMapper extends BaseMapper<ArticleLabel> {

    /**
     * 通过文章id查找分类
     *
     * @param articleId 文章id
     * @return {@link String }
     * @author MK
     */
    List<String> selectLabelName(Long articleId);
}
