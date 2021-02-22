package com.mk.blog.converter;

import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.ArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
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
     */
    List<ArticleListVo> toConvertVO(List<Article> source);

    /**
     * 文章转Vo
     *
     * @param source 文章
     * @return {@link ArticleVo }
     * @author MK
     */
    ArticleListVo toConvertVO(Article source);
}
