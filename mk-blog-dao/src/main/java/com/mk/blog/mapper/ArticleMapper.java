package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author MK
 * @describe 文章表mapper类
 * @date 2021-01-29 14:44:28
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据条件获取文章列表
     *
     * @param map  参数map
     * @param page 分页参数
     * @return {@link List<ArticleListVo> }
     * @author MK
     * @date 2021/2/19 10:51
     */
    IPage<ArticleListVo> selectAll(@Param("map")Map<String, Object> map, @Param("page") IPage<ArticleListVo> page);
}
