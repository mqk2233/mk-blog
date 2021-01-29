package com.mk.blog.impl;

import com.mk.blog.entity.Article;
import com.mk.blog.mapper.ArticleMapper;
import com.mk.blog.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @describe 文章表服务实现类
 * @author MK
 * @date 2021-01-29 14:44:28
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
