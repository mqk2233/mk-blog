package com.mk.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.Article;
import com.mk.blog.entity.Category;
import com.mk.blog.vo.ArticleDetailsVo;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.TimeAisVo;

import java.util.List;

/**
 * @author MK
 * @describe 文章表服务类
 */
public interface IArticleService extends IService<Article> {

    /**
     * 获取文章列表
     *
     * @param title   文章标题
     * @param labelId 文章分类id
     * @param status  文章状态
     * @param page    分页参数
     * @return {@link List<Article> }
     * @author MK
     */
    IPage<ArticleListVo> articleList(String title, Long labelId, Integer status, IPage<ArticleListVo> page);

    /**
     * 获取当前id文章
     *
     * @param id 文章id
     * @return {@link ArticleDetailsVo }
     * @author MK
     */
    ArticleDetailsVo articleById(Long id);

    /**
     * 新增文章
     *
     * @param article 文章对象
     * @return {@link boolean }
     * @author MK
     */
    boolean addArticle(ArticleDetailsVo article);

    /**
     * 编辑文章
     *
     * @param article 文章对象
     * @return {@link boolean }
     * @author MK
     */
    boolean editArticle(ArticleDetailsVo article);

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return {@link boolean }
     * @author MK
     */
    boolean delArticle(Long id);

    /**
     * 发布文章
     *
     * @param id 文章id
     * @return {@link boolean }
     * @author MK
     */
    boolean releaseArticle(Long id);

    /**
     * 恢复文章
     *
     * @param id 文章id
     * @return {@link boolean }
     * @author MK
     */
    boolean recoveryArticle(Long id);

    /**
     * 时光轴
     *
     * @return {@link boolean }
     * @author MK
     */
    List<TimeAisVo> timeAis();

    /**
     * 递归获取分类树id
     *
     * @param category 分类对象
     * @return java.util.List<java.lang.Long>
     * @author MK
     */
    List<Long> buildCategoryIds(Category category, List<Long> categoryIds);
}
