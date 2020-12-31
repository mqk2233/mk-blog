package com.mk.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mk.blog.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author MK
 * @date 2020-12-24 01:46:10
 * @describe 文章表mapper类
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectAll();
}
