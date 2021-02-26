package com.mk.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IArticleLabelService;
import com.mk.blog.IArticleService;
import com.mk.blog.converter.ArticleDetailsConverter;
import com.mk.blog.entity.Article;
import com.mk.blog.entity.ArticleLabel;
import com.mk.blog.entity.Category;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.mapper.ArticleLabelMapper;
import com.mk.blog.mapper.ArticleMapper;
import com.mk.blog.mapper.CategoryMapper;
import com.mk.blog.utils.HtmlUtil;
import com.mk.blog.vo.ArticleDetailsVo;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.TimeAisVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MK
 * @describe 文章表服务实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleLabelMapper articleLabelMapper;
    @Resource
    private IArticleLabelService iArticleLabelService;
    @Resource
    private CategoryMapper categoryMapper;


    @Override
    public IPage<ArticleListVo> articleList(String title, Long labelId, Integer status, IPage<ArticleListVo> page) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("title", title);
        map.put("labelId", labelId);
        map.put("status", status);
        page = articleMapper.selectAll(map, page);
        page.setRecords(page.getRecords().stream()
                .peek(item -> item.setLabelNames(articleLabelMapper.selectLabelName(item.getId())))
                .peek(item -> item.setText(HtmlUtil.getText(item.getContentHtml())))
                .collect(Collectors.toList()));
        return page;
    }

    @Override
    public ArticleDetailsVo articleById(Long id) {
        Article article = Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new CustomException(ResponseEnum.PARAM_ERROR));
        ArticleDetailsVo vo = ArticleDetailsConverter.INSTANCE.toConvertVO(article);
        List<Long> categoryIds = new ArrayList<>();
        categoryIds = this.buildCategoryIds(categoryMapper.selectById(article.getCategoryId()), categoryIds);
        Collections.reverse(categoryIds);
        vo.setCategoryIds(categoryIds);
        vo.setLabelIds(iArticleLabelService.LabelIdByArticleId(vo.getId()));
        vo.setCatalogTree(HtmlUtil.getCatalogTree(article.getContentHtml()));
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addArticle(ArticleDetailsVo vo) {
        vo.setTitle(HtmlUtil.getTitle(vo.getContentHtml()));
        Article article = ArticleDetailsConverter.INSTANCE.toConvert(vo);
        this.save(article);
        return iArticleLabelService.saveBatch(vo.getLabelIds().stream()
                .map(item -> ArticleLabel.builder()
                        .articleId(article.getId())
                        .labelId(item)
                        .build()
                )
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editArticle(ArticleDetailsVo vo) {
        vo.setTitle(HtmlUtil.getTitle(vo.getContentHtml()));
        this.updateById(ArticleDetailsConverter.INSTANCE.toConvert(vo));
        iArticleLabelService.delByArticleId(vo.getId());
        return iArticleLabelService.saveBatch(vo.getLabelIds().stream()
                .map(item -> ArticleLabel.builder()
                        .articleId(vo.getId())
                        .labelId(item)
                        .build()
                )
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delArticle(Long id) {
        articleLabelMapper.delete(Wrappers.lambdaQuery(ArticleLabel.class)
                .eq(ArticleLabel::getArticleId, id));
        return this.removeById(id);
    }

    @Override
    public boolean releaseArticle(Long id) {
        return this.lambdaUpdate()
                .eq(Article::getId, id)
                .set(Article::getStatus, 1)
                .update();
    }

    public boolean recoveryArticle(Long id) {
        return articleMapper.updateIsDeleted(id) > 0;
    }

    @Override
    public List<TimeAisVo> timeAis() {
        return articleMapper.groupMonth().stream()
                .map(item -> new TimeAisVo(item, articleMapper.selectByMonth(item)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> buildCategoryIds(Category category, List<Long> categoryIds) {
        if (null != category && null != category.getId()) {
            categoryIds.add(category.getId());
            if (category.getPid() != null) {
                category = categoryMapper.selectById(category.getPid());
                this.buildCategoryIds(category, categoryIds);
            }
        }
        return categoryIds;
    }

}
