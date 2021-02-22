package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author MK
 * @describe 文章表mapper类
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据条件获取文章列表
     *
     * @param map  参数map
     * @param page 分页参数
     * @return {@link List<ArticleListVo> }
     * @author MK
     */
    IPage<ArticleListVo> selectAll(@Param("map") Map<String, Object> map, @Param("page") IPage<ArticleListVo> page);

    /**
     * 恢复文章
     *
     * @param id 文章id
     * @return {@link int }
     * @author MK
     */
    int updateIsDeleted(Long id);

    /**
     * 通过月份分组文章
     *
     * @return {@link List<String> }
     * @author MK
     */
    List<String> groupMonth();

    /**
     * 同过月份查找文章
     *
     * @param month 月份
     * @return {@link List<ArticleVo> }
     * @author MK
     */
    List<ArticleVo> selectByMonth(String month);


}
