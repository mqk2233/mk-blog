package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IArticleLabelService;
import com.mk.blog.entity.ArticleLabel;
import com.mk.blog.mapper.ArticleLabelMapper;
import org.springframework.stereotype.Service;

/**
 * @describe 文章标签中间表服务实现类
 * @author MK
 * @date 2021-01-29 14:44:58
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements IArticleLabelService {


}
