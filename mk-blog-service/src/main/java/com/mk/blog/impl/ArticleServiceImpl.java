package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IArticleService;
import com.mk.blog.converter.ArticleConverter;
import com.mk.blog.entity.Article;
import com.mk.blog.mapper.ArticleMapper;
import com.mk.blog.vo.ArticleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MK
 * @date 2021-01-25 18:00:56
 * @describe 文章表服务实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleVo> selectAll() {
        List<Article> list = articleMapper.selectList(null);
        return ArticleConverter.INSTANCE.toConvertVOList(list);
    }
}
