package com.mk.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IArticleService;
import com.mk.blog.entity.Article;
import com.mk.blog.entity.Category;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.mapper.ArticleLabelMapper;
import com.mk.blog.mapper.ArticleMapper;
import com.mk.blog.utils.HtmlUtil;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.TimeAisVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @describe 文章表服务实现类
 * @author MK
 * @date 2021-01-29 14:44:28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleLabelMapper articleLabelMapper;

    @Override
    public IPage<ArticleListVo> articleList(String title, Integer isDeleted, Integer status, IPage<ArticleListVo> page) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("title", title);
        map.put("isDeleted", isDeleted);
        map.put("status", status);
        page =  articleMapper.selectAll(map, page);
        page.setRecords(page.getRecords().stream()
                .peek(item -> item.setLabelNames(articleLabelMapper.selectLabelName(item.getId())))
                .peek(item -> item.setText(HtmlUtil.getText(item.getContentHtml())))
                .collect(Collectors.toList()));
        return page;
    }

    @Override
    public Article articleById(Long id) {
        Article article = Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new CustomException(ResponseEnum.PARAM_ERROR));

        return null;
    }

    @Override
    public int addArticle(Article article) {
        return 0;
    }

    @Override
    public int editArticle(Article article) {
        return 0;
    }

    @Override
    public int delArticle(Long id) {
        return 0;
    }

    @Override
    public int releaseArticle(Long id) {
        return 0;
    }

    @Override
    public List<TimeAisVo> timeAis() {
        return null;
    }

    @Override
    public List<String> buildCategoryIds(Category category, List<String> categoryIds) {
        return null;
    }

}
