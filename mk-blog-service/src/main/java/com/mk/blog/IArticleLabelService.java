package com.mk.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.ArticleLabel;

import java.util.List;

/**
 * @author MK
 * @describe 文章标签中间表服务类
 */
public interface IArticleLabelService extends IService<ArticleLabel> {

    /**
     * 通过文章id获取标签
     *
     * @param articleId 文章id
     * @return {@link List<Long> }
     * @author MK
     */
    List<Long> LabelIdByArticleId(Long articleId);
}
