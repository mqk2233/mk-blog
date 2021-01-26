package com.mk.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleVo;

import java.util.List;

/**
 * @author MK
 * @date 2021-01-25 18:00:56
 * @describe 文章表服务类
 */
public interface IArticleService extends IService<Article> {

    List<ArticleVo> selectAll();
}
