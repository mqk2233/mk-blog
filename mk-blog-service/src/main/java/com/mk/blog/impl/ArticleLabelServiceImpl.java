package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IArticleLabelService;
import com.mk.blog.entity.ArticleLabel;
import com.mk.blog.mapper.ArticleLabelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MK
 * @describe 文章标签中间表服务实现类
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements IArticleLabelService {


    @Override
    public List<Long> LabelIdByArticleId(Long articleId) {
        return this.lambdaQuery()
                .eq(ArticleLabel::getArticleId, articleId)
                .list().stream()
                .map(ArticleLabel::getLabelId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delByArticleId(Long articleId) {
        return this.lambdaUpdate()
                .eq(ArticleLabel::getArticleId, articleId)
                .remove();
    }
}
