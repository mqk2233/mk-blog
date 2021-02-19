package com.mk.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.Article;
import com.mk.blog.entity.Category;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.TimeAisVo;

import java.util.List;

/**
 * @author MK
 * @describe 文章表服务类
 * @date 2021-01-29 14:44:28
 */
public interface IArticleService extends IService<Article> {

    /**
     * 获取文章列表
     *
     * @param title     文章标题
     * @param isDeleted 是否删除
     * @param status    文章状态
     * @param page      分页参数
     * @return {@link List<Article> }
     * @author MK
     * @date 2021/2/19 10:35
     */
    IPage<ArticleListVo> articleList(String title, Integer isDeleted, Integer status, IPage<ArticleListVo> page);

    /**
     * 获取当前id文章
     *
     * @param id 文章id
     * @return pers.mk.blog.bean.entity.Article
     */
    Article articleById(Long id);

    /**
     * 新增文章
     *
     * @param article 文章实体
     */
    int addArticle(Article article);

    /**
     * 编辑文章
     *
     * @param article 文章实体
     */
    int editArticle(Article article);

    /**
     * 删除文章
     *
     * @param id 文章id
     */
    int delArticle(Long id);

    /**
     * 发布文章
     *
     * @param id 文章id
     * @return int
     */
    int releaseArticle(Long id);

    /**
     * 时光轴
     *
     * @return java.util.List<pers.mk.blog.bean.entity.TimeAis>
     */
    List<TimeAisVo> timeAis();

    /**
     * 递归获取分类树id
     *
     * @param category 分类对象
     * @return java.util.List<java.lang.Long>
     */
    List<String> buildCategoryIds(Category category, List<String> categoryIds);
}
