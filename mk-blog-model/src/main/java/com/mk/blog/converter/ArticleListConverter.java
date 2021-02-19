package com.mk.blog.converter;

import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.ArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
 * @date 2021/2/19
 */
@Mapper
public interface ArticleListConverter {

    ArticleListConverter INSTANCE = Mappers.getMapper(ArticleListConverter.class);

    /**
     * 文章集合转listVo集合
     *
     * @param source 文章集合
     * @return {@link List<ArticleVo> }
     * @author MK
     * @date 2021/2/19 10:44
     */
    List<ArticleListVo> toConvertVO(List<Article> source);
}
